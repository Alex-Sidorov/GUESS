import json


class RecognitionView:

    def on_post(self, req, resp):

        response = {'message': 'okey'}

        resp.body = json.dumps(response, ensure_ascii=True)
