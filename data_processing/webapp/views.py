import json

from falcon.errors import HTTPBadRequest
from recognition.object_detector import recognize


def serializer(raw_response):
    return json.dumps([{'name': res[0], 'confidence': res[1]} for res in raw_response], ensure_ascii=True)


class RecognitionView:

    def on_post(self, req, resp):

        file = req.get_param('file')
        try:
            image = file.file
        except AttributeError:
            raise HTTPBadRequest('Bad multipart form', 'File is not valid')
        raw_response = recognize(image)
        response = serializer(raw_response)

        resp.body = response


class RecognitionOldView:

    def on_post(self, req, resp):

        image = req.bounded_stream
        raw_response = recognize(image)
        response = serializer(raw_response)

        resp.body = response


class LabelsView:
    PATH_TO_LABELS = 'recognition/v2_labels.pbtxt'

    @staticmethod
    def labels_serializer(raw_response):
        resp = list(map(lambda x: {'name': x}, raw_response))
        return json.dumps(resp)

    def on_get(self, req, resp):

        labels = []
        with open(self.PATH_TO_LABELS) as f:
            for line in f:
                if line.startswith('  name'):
                    labels.append(line.rstrip()[9: -1])  # getting labels from .pbtxt format
        response = self.labels_serializer(labels)

        resp.body = response
