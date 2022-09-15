# Steps for running Finco-NLP

## About
 - Finco NLP takes unstructed text and detects pattern in text using Named Entity Recognition.

## Requirements
- Python
```
```
- PIP
```
```
- Spacy
``` 
pip3 intsall spacy
```
- Virtual Env 
```
python3 -m venv venv
. venv/bin/activate
```
- Intall requirement.txt
## creating a config file
```
python3 -m spacy init config config.cfg --lang en --pipeline ner --optimize efficiency
```
## training the model with CPU
```
python -m spacy train config.cfg --output ./output/ --paths.train train_data\nlp.spacy  --paths.dev train_data\nlp.spacy
```

python -m rasa_nlu.train -c config_spacy.yaml --data train_data\intent_data.md -o models --fixed_model_name nlu --project current --verbose