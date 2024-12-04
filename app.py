import numpy as np
from keras.models import load_model
from PIL import Image
from flask import Flask, request, jsonify
from flask_cors import CORS
from pyngrok import ngrok

# Initialize Flask app
app = Flask(__name__)
CORS(app)  # Enable CORS for cross-origin requests

# Define an inference endpoint
@app.route('/predict', methods=['POST'])
def predict():
    if 'file' not in request.files:
        return jsonify({'error': 'No file uploaded'}), 400

    # Retrieve the inputted image file
    file = request.files['file']

    if file.filename == '':
        return jsonify({'error': 'No selected file'}), 400

    # Open the image file
    input_image = Image.open(file.stream).convert('RGB')

    # "prediction" is a Boolean value True or False
    prediction = predict_external_image(input_image)

    return jsonify({'prediction': prediction})

if __name__ == '__main__':
    app.run(port = 10000)