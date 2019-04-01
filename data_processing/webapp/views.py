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
