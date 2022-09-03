from re import M
from flask import Flask, request, jsonify, render_template
import spacy
from spacy import displacy
from tqdm import tqdm
from spacy.tokens import DocBin
import json

app = Flask(__name__)


@app.route("/")
def get_root():
    print("sending swagger docs")
    return render_template("swaggerui.html")


@app.route("/nlp/")
def get_nlp():
    return "Finco-NLP is up."


@app.route("/nlp/predict")
def predict():
    nlp_ner= spacy.load("output/model-best")
    dockBin = DocBin()  # create a object of dockbin
    dockBin = nlp_ner('''get me account details 12345 Nucleus''')
    return displacy.render(dockBin,style='ent')


@app.route("/nlp/train")
def train_nlp_model():
    nlp = spacy.blank("en")  # load a new spacy model
    dockBin = DocBin()  # create a object of dockbin
    print("inside")
    file = open("finco-nlp/train_data/annotations.json")
    train_data = json.load(file)
    for text, annot in tqdm(train_data["annotations"]):
        doc = nlp.make_doc(text)
        entities = []
        for start, end, label in annot["entities"]:
            span = doc.char_span(start, end, label=label, alignment_mode="contract")
            if span is None:
                print("Skipping the Entity")
            else:
                entities.append(span)
        doc.ents = entities
        dockBin.add(doc)
    # copying spacy file om desk
    dockBin.to_disk("finco-nlp/train_data/train_data.spacy")
    return "hii"


app.run(use_reloader=True, debug=False)
