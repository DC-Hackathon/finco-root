from re import M
from textblob import TextBlob
from flask import Flask, request, jsonify, render_template
import spacy
from spacy import displacy
from tqdm import tqdm
from spacy.tokens import DocBin
import json
import uuid
from rasa_nlu.training_data  import load_data
from rasa_nlu.config import RasaNLUModelConfig
from rasa_nlu.model import Trainer
from rasa_nlu import config
from rasa_nlu.model import Metadata, Interpreter
import locale
import warnings


app = Flask(__name__)

@app.route("/")
def get_root():
    print("sending swagger docs")
    return render_template("swaggerui.html")

@app.route("/nlp/predict", methods=["POST"])
def predict():
    dict = {}  # Initializing empty dictionary
    test_data = cleanData(request.args.get("text")) # getting request string from Request and cleaning
    print("Creating NER for :", test_data)
    intent_dict=predict_intent(test_data)
    nlp_ner = spacy.load("output/model-best") # loading trained model
    dockBin = DocBin()  # create a object of dockbin
    dockBin = nlp_ner(test_data) # predicting...
    for ent in dockBin.ents: # storing result in dictionary
        if(ent.text.__contains__(',')):
                locale.setlocale( locale.LC_ALL, 'en_US.UTF-8' )
                dict[str(ent.label_)]=locale.atoi(ent.text)
        else:
            dict[str(ent.label_)] = ent.text
       
    dict["span"] = displacy.render(dockBin, style="ent") # creating a html span
    dict["nlpResponseId"] = uuid.uuid1() #generating random uid
    dict["intent"]= intent_dict["intent"]
    dict["confidence"] = intent_dict["confidence"]
    return dict # returning dictionary

def cleanData(data):
    new_doc = TextBlob(data)
    newd =str(new_doc.correct())
    return newd;


@app.route("/nlp/train") # one time for only training model
def train_nlp_model():
    nlp = spacy.blank("en")  # load a new spacy model
    dockBin = DocBin()  # create a object of dockbin
    file = open("train_data/nlp.json") # opening file 
    train_data = json.load(file)
    for text, annot in tqdm(train_data["annotations"]):
        doc = nlp.make_doc(text)
        entities = []
        for start, end, label in annot["entities"]:
            span = doc.char_span(start, end, label=label, alignment_mode="contract")
            if span is None:
                print("skipping the Entity...")
            else:
                entities.append(span)
        doc.ents = entities
        dockBin.add(doc)
    # copying spacy file om desk
    dockBin.to_disk("train_data/nlp.spacy")
    return '<h2>Trained Model</h1>'

@app.route("/intent/predict",methods=["POST"])
def predict_intent(test_data="test"):
    intent_dict = {}
    if(test_data=="test"):
        test_data= cleanData(request.args.get("text"))
        print("classifying intent for: ",test_data)
    interpreter = Interpreter.load('models/current/nlu')
    intent_data=interpreter.parse(test_data)
    intent_dict["intent"] = intent_data.get('intent')['name']
    intent_dict["confidence"] = intent_data.get('intent')['confidence']
    return intent_dict

@app.route("/intent/train")
def train_intent_data():
    spacy.load("en_core_web_sm")
    train_data = load_data('train_data\intent_data.md')
    trainer = Trainer(config.load("config_spacy.yaml"))
    trainer.train(train_data)
    trainer.persist('output')
    return "<h1>Trained Model</h1>"

warnings.filterwarnings("ignore", category=DeprecationWarning)
app.run(use_reloader=True, debug=False)
