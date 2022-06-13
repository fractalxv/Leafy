![Bangkit](https://lh3.googleusercontent.com/J2QI0L3vJwv63Sm3isI90ctxuxznz67dAtJQN2vu7wnUuwt9Wc-WI7VuIhwvr0yVrDPfc7kBN5usZz75nDW_k96pCfcZBxnfNzvVS0g=w600)
 
## Bangkit-C22-PS221-Leafy

<p align="center">
<img src=https://cdn.discordapp.com/attachments/740649734104154175/985219175633006652/LogoLeafy.png width='220dp'>
<h3 align="center">Leafy</h3>
<h3 align="center">An Application to classify and recognize various types of herbs in real time</h3>

## What is this?🍀
### About Our Project
The popularity of herbal medicine has increased worldwide. According to pom.go.id, Indonesia has millions varieties of medicinal plants that have the potential to be developed to add value to the herbal medicine industry which is greater than other countries, in their research. Therefore, we decided to choose our idea to optimize the use of medicinal plants in Indonesia. Starting from introducing it to people

Our Project is a simple android application that can identify and recognize the types of herbs automatically with their use using CNN with mobilenet architecture and 500 images for 11 types of leaves of herbal plants. Based on the test results on 30 new images obtained an accuracy rate of 86.7%, alongside with details about how the herbs work and how to process it (in a simple way)

<h2 align="center">Tech Stack that we use for this project</h2>
<p align="center">
<img src=https://cdn.discordapp.com/attachments/740649734104154175/985716893115641916/unknown.png width='570'>

## Machine Learning Docs📝
### Data Preprocessing
Dataset : [link dataset](https://drive.google.com/file/d/1dEWuf9-8r_3FqjLAlqt_Uqw-J0wLj78D/view?usp=sharing).

We use image data generator with data augmentation for reduce overfitting. We also split dataset into training and validation sets with 80:20 split.

### Training Model
Our model will be consisting CNN, Dropout, and Flatten Layers.
Our model is compiled woth adam optimizer and for loss using categorical crossentrophy

### Predicting the model
Our model have achieved accuracy for training : 98.75℅ and validation accuracy : 99.29℅

### Saving Model
We save our model with .h5 and.tflite. 
Link: https://drive.google.com/drive/folders/19938C6lFmV9CYn9JlC22N_hSjFozUFdQ?usp=sharing
 
## Mobile Development Docs📝

| Splash Screen  | Main Screen | Start Screen | Process Screen |
| ------------- | ------------- | ------------- | ------------- |
|<img src=https://cdn.discordapp.com/attachments/740649734104154175/985613347347132436/Splashscreen.jpg width='230'>|<img src=https://cdn.discordapp.com/attachments/740649734104154175/985613347900751932/MainScreen.jpg width='230'>|<img src=https://cdn.discordapp.com/attachments/740649734104154175/985613347003187230/StartScreen.jpg width='230'>|<img src=https://cdn.discordapp.com/attachments/740649734104154175/985613347653296181/ProcessScreen.jpg width='230'>|

| Result Screen  | Developer Screen | 
| ------------- | ------------- |
|<img src=https://cdn.discordapp.com/attachments/740649734104154175/985615739786518568/ResultScreen.jpg width='230'>|<img src=https://cdn.discordapp.com/attachments/740649734104154175/985613348190183425/CapstoneGroupScreen.jpg width='230'>|

## Cloud Computing Docs📝
For more detailed process about deployment, you can access it here [here](https://github.com/fractalxv/Leafy/blob/master/CC/README.md)


## The Gang🍀

Name | Bangkit ID | Learning Path
:---|:---:|---:
Muhamad Reza Anggana Putra | M2299F2584 | Machine Learning
Fadhilah Akhbar| M2279F2403 | Machine Learning
Rival Swandy Irawan | A2299F2578 | Mobile Development
Gilang Zhanuardy Pamungkas |  A2299F2585 | Mobile Development
Adji Latifah Mahsa Hakim | C2449K3054 | Cloud Computing
Myra T Karimah | C7012F1201 | Cloud Computing

<!-- ### Web Setup (*WIP*)
1. Activate the virtualenv **venv** by inserting the following:
    ```
    $ source venv/bin/activate
    ```
2. Execute Streamlit locally by inserting the following:
    ```
    $ streamlit run main.py -->
