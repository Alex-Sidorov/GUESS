FROM ubuntu:16.04


RUN apt-get update \
  && apt-get install -y python3-pip python3-dev \
  && cd /usr/local/bin \
  && ln -s /usr/bin/python3 python \
  && pip3 install --upgrade pip

ADD ./requirements.txt /tmp/requiremets.txt

RUN pip3 install --upgrade pip==19.0.2 \
  && pip3 install --no-cache-dir -r /tmp/requiremets.txt

RUN apt-get install -y protobuf-compiler \
        python-pil \
        python-lxml \
        python-tk \
        wget \
        unzip

ADD ./webapp /opt/webapp

WORKDIR /opt/webapp/research

RUN wget -O protobuf.zip https://github.com/google/protobuf/releases/download/v3.0.0/protoc-3.0.0-linux-x86_64.zip
RUN unzip protobuf.zip
RUN ./bin/protoc object_detection/protos/*.proto --python_out=.

ENV PYTHONPATH "${PYTHONPATH}:."
ENV PYTHONPATH "${PYTHONPATH}:./slim"

RUN python3 object_detection/builders/model_builder_test.py


WORKDIR /opt/webapp/

#CMD ["gunicorn", "-b", "0.0.0.0:8000", "app"]
CMD gunicorn --bind 0.0.0.0:$PORT app