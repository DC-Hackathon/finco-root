from re import M
from flask import Flask, request, jsonify, render_template
import spacy
from spacy import displacy
from tqdm import tqdm
from spacy.tokens import DocBin
import json
import uuid

app = Flask(__name__)


@app.route("/")
def get_root():
    print("sending swagger docs")
    return render_template("swaggerui.html")

@app.route("/nlp/predict", methods=["POST"])
def predict():
    print("Predicting....")
    dict = {}  # Initializing empty dictionary
    test_data = request.args.get("text")  # getting request string from Request
    print("Creating NER for :", test_data)
    nlp_ner = spacy.load("output/model-best") # loading trained model
    dockBin = DocBin()  # create a object of dockbin
    dockBin = nlp_ner(test_data) # predicting...
    for ent in dockBin.ents: # storing result in dictionary
        dict[str(ent.label_)] = ent.text
    dict["span"] = displacy.render(dockBin, style="ent") # creating a html span
    dict["nlpResponseId"] = uuid.uuid1() #generating random uid
    print("Here is what i generated", dict)
    return dict # returning dictionary

@app.route("/nlp/train") # one time for only training model
def train_nlp_model():
    nlp = spacy.blank("en")  # load a new spacy model
    dockBin = DocBin()  # create a object of dockbin
    file = open("finco-nlp/train_data/annotations.json") # opening file 
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
    dockBin.to_disk("finco-nlp/train_data/train_data.spacy")
    return '<h2>Trained Model</h1>'

app.run(use_reloader=True, debug=False)
