import os
import tarfile
import urllib.request as urllib_request


def download_model(download_base, model_file):

    opener = urllib_request.urlopen(download_base + model_file)
    model_file = opener.read()
    # opener.retrieve(download_base + model_file, model_file)
    tar_file = tarfile.open(model_file)
    for file in tar_file.getmembers():
        file_name = os.path.basename(file.name)
        if 'frozen_inference_graph.pb' in file_name:
            tar_file.extract(file, os.getcwd())


MODEL_NAME = 'ssd_mobilenet_v1_coco_2017_11_17'
MODEL_FILE = MODEL_NAME + '.tar.gz'
DOWNLOAD_BASE = 'http://download.tensorflow.org/models/object_detection/'

download_model(DOWNLOAD_BASE, MODEL_FILE)