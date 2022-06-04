from flask import Flask, request
from flask_restful import Resource, Api
from flask_cors import CORS
import numpy as np
import keras_preprocessing
from keras_preprocessing import image
from keras_preprocessing.image import ImageDataGenerator
from tensorflow.keras.applications.xception import Xception
from tensorflow.keras.preprocessing import image
from tensorflow.keras.models import Model
from tensorflow.keras.layers import Dropout, Dense, Flatten
from tensorflow.keras.optimizers import Adam

print("Tensorflow Version ",tensorflow.__version__)
print("Tensorflow Keras version ",tensorflow.keras.__version__)
print("Keras Version ",keras_lib.__version__)

app = flask.Flask(__name__)

@app.before_first_request
def load_model_keras_model():
    global model
    model = load_model('../herbs-model.py')

global CATEGORIES
CATEGORIES = ['JAMBU BIJI', 
              'KARI',
              'KEMANGI',
              'KUNYIT',
              'MINT',
              'PEPAYA',
              'SIRIH',
              'LIDAH BUAYA',
              'TEH HIJAU']

# Maximum Image Uploading size
app.config['MAX_CONTENT_LENGTH'] = 2 * 1024 * 1024

# Image extension
ALLOWED_EXTENSIONS = set(['png', 'jpg', 'jpeg'])

def allowed_file(filename):
    return '.' in filename and filename.rsplit('.', 1)[1].lower() in ALLOWED_EXTENSIONS


print("Loading keras model")
load_model_keras_model()


## membuat ping server request
@app.route('/pingserver',methods=['GET'])
def pingServer():

    pingResp = {'response_message': "Pong from server", 'response_code': "200", "response_root": "Success"}

    return jsonify(pingResp)

## For multiple files upload..
@app.route('/predict', methods=['POST'])
def predict():

    responseFileList = []

    if 'file' not in request.files:
        fileResp = {'response_message': "No file part in the request", 'response_code': "400", "response_root": "Error"}
        responseFileList.append(fileResp)

    uploaded_files = request.files.getlist("file")

    for file in uploaded_files:

        try:

            if file.filename == '':
                fileResp = {'response_message': "No file part in the request", 'response_code': "400",
                            "response_root": "Error"}
                fileResp = {'file': filename}
                responseFileList.append(fileResp)

            elif file and allowed_file(file.filename):
                filename = secure_filename(file.filename)

                class_lable = predict_label(file)

                if (class_lable == ''):

                    fileResp = {'response_message': "Something went wrong", 'response_code': "500",
                                "response_root": "Error", 'imagePath': filename, 'memeStatus': "Not Defined"}
                    responseFileList.append(fileResp)
                else:

                	## Success Response
                    fileResp = {'response_message': "Valid File", 'response_code': "200",
                                "response_root": "Success", 'imagePath': filename, 'memeStatus': class_lable}
                    responseFileList.append(fileResp)

            else:
                fileResp = {'response_message': "incompatible file extension part in the request",
                            'response_code': "400",
                            "response_root": "Error"}
                responseFileList.append(fileResp)

        except Exception as e:  # keeping the output clean
            filename = secure_filename(file.filename)
            fileResp = {'response_message': "File not uploaded", 'response_code': "500",
                        "response_root": "Error", 'file': filename}
            responseFileList.append(fileResp)
            print(e)
        pass

    return jsonify(responseFileList)

