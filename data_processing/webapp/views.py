import json

from recognition.object_detector import recognize


class RecognitionView:

    def on_post(self, req, resp):

        image = req.bounded_stream

        response = recognize(image)

        resp.body = json.dumps(response, ensure_ascii=True)
