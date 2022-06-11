FROM python:3.9.0-slim-buster

COPY . /app

WORKDIR /app
RUN pip install --upgrade pip
RUN pip install --upgrade setuptools
RUN pip install -r requirements.txt
COPY . ./
ENV FLASK_APP app.py
ENV FLASK_ENV dev
ENV PORT 8080

CMD exec gunicorn --bind :$PORT --workers 1 --threads 8 --timeout 0 app:app
