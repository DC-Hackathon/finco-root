# FINCO-NLP

Finco NLP takes unstructed text and detects pattern in text using Named Entity Recognition and predict its intent using Intent Classifier.


## Requirements
#### Python 3.7.9 : https://www.python.org/downloads/release/python-379/
#### Virtual Environment 
```
python -m venv venv
venv\Scripts\activate 
```
#### To Ignore Package from Cache
```
pip install package_name --no-cache-dir
```
#### Install Flask
```
pip install flask
```
#### Install Spacy
```
pip install spacy
```
#### Install Sklearn CRF
```
pip install sklearn_crfsuite
```
#### Install English Model From Spacy
```
python -m spacy download en_core_web_md
```
#### Install Rasa NLU
```
pip install rasa_nlu
```
#### Creating a Config File For Spacy
```
python3 -m spacy init config config.cfg --lang en --pipeline ner --optimize efficiency
```
#### training  spacy the model with CPU
```
python -m spacy train config.cfg --output output/ --paths.train train_data\nlp.spacy  --paths.dev train_data\nlp.spacy
```
#### training  rasa nlu the model
```
python -m rasa_nlu.train -c config_spacy.yaml --data train_data\intent_data.md -o models --fixed_model_name nlu --project current --verbose
```

Finally Run :
```
python app.py
```