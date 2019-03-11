FROM python:3.7-alpine

RUN apk add --update --no-cache --virtual .run-deps \
        libpq \
    && rm -rf /var/cache/apk/*

ADD ./requirements.txt /tmp/requiremets.txt

RUN apk add --update --no-cache --virtual .build-deps \
        gcc \
        libffi-dev \
        musl-dev \
        linux-headers \
        git \
    && rm -rf /var/cache/apk/* \
    && pip install --upgrade pip==19.0.2 \
    && pip install --no-cache-dir -r /tmp/requiremets.txt  \
    && apk del .build-deps

ADD ./webapp /opt/webapp/
WORKDIR /opt/webapp/

RUN adduser -D myuser
USER myuser

CMD gunicorn --bind 0.0.0.0:$PORT app