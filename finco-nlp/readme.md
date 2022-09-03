# Steps for running Finco-NLP

## About
 - Finco NLP takes unstructed text and detects pattern in text using Named Entity Recognition.

## Requirements
- Python
- PIP
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
python3 -m spacy train config.cfg --output ./output/ --paths.train finco-nlp/train_data/train_data.spacy  --paths.dev finco-nlp/train_data/train_data.spacy 
```