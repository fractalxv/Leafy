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


