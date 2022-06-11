from flask import Flask, jsonify, request
import numpy as np
import tensorflow as tf
from PIL import Image
import numpy as np
import os
os.environ['TF_CPP_MIN_LOG_LEVEL'] = '2'
import io as BytesIO

app = Flask(__name__)

# preparing image post request
ALLOWED_EXTENSIONS = {'JPG','JPEG','PNG'}
model = tf.keras.models.load_model('model/Leafy.h5')

def allowed_file(filename):
    return '.' in filename and \
           filename.rsplit('.', 1)[1].lower() in ALLOWED_EXTENSIONS

def prepare(img):
    img = Image.open(BytesIO(img))
    img = img.resize((224, 224), Image.ANTIALIAS)
    img = np.array(img)
    img = np.expand_dims(img, axis=0)
    return img

def img_process(model, img):
    label_class = [
        'JAMBU BIJI',
        'KARI',
        'KUNYIT',
        'PEPAYA',
        'SIRIH',
        'SIRSAK',
        'LIDAH BUAYA'
    ]
    prediction = model.predict(img)
    predict_class = label_class[np.argmax(prediction[0])]
    confidence = round(100 * (np.max(prediction[0])), 2)
    return predict_class, confidence

@app.route('/')
def index():
    return {"Hello!": "This is a Leafy backend API :D"}, 200

@app.route('/predict', methods=['POST'])
def predict():
    if request.method == 'POST':
        file = request.files.get('file')
        if file and allowed_file(file.filename):
            img = prepare(file)
            predict_class, confidence = img_process(model, img)
            return jsonify({"class": predict_class, "confidence": confidence})
        else:
            return jsonify({"error": "Invalid file type"})
    else:
        return jsonify({"error": "Invalid request"})

    #     if 'file' not in request.files:
    #         return jsonify({"Error": "No file part"}), 400
    #     file = request.files['file']
    #     if file.filename == '':
    #         return jsonify({"Error": "No file selected"}), 400
    #     if file and allowed_file(file.filename):
    #         img = prepare(file)
    #         predict_class, confidence = img_process(model, img)
    #         return jsonify({"Class": predict_class, "Confidence": confidence}), 200
    #     else:
    #         return jsonify({"Error": "File type not allowed"}), 400
    # else:
    #     return jsonify({"Error": "Method not allowed"}), 400

#  eksekusi
if __name__ == '__main__':
    app.run(debug=True, host="0.0.0.0", port=int(os.environ.get("PORT", 8080)))
    # app.run(debug=True)