from flask import Flask, request, jsonify, render_template

app = Flask(__name__)

@app.route('/')
def get_root():
    print('sending swagger docs')
    return render_template('swaggerui.html')

@app.route('/nlp/')
def get_nlp():
    return "Finco-NLP is up."

app.run(use_reloader=True, debug=False)