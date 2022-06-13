## How it works
**Step 1.** Create Flask app that contains Modules that included in requirements to load the .h5 model in main.py

**Step 2.** Create Dockerfile (im using reference from Google Cloud Docs)
  ```Dockerfile
     ...
     FROM python:3.9.0
     # Copy local code to the container image
     COPY . /app

     # Sets the working directory
     WORKDIR /app

     # Upgrade PIP
     RUN pip install --upgrade pip

     #Install python libraries from requirements.txt
     RUN pip install --no-cache-dir -r requirements.txt

     # Set $PORT environment variable
     ENV PORT 8080

     # Run the web service on container startup
     CMD exec gunicorn --bind :$PORT --workers 1 --threads 8 --timeout 0 main:app
     ```
   ```
 create docker image and push to docker registry
 ```
docker build -t main
docker run main
docker push fractalxv/leafy_api:deploy
```
**Step 3.** Install and init Google Cloud SDK for our project repo
**Step 4.** Deploy with Cloud Run (build and deploy)
```
gcloud builds submit --tag gcr.io/leafy-351805/main
gcloud run deploy --image gcr.io/leafy-351805/main --managed
```
**Step 5.** Testing via Postman
services are deployed in [here](https://linktr.ee/leafy_app)

