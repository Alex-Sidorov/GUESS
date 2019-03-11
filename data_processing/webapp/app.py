import falcon

from views import RecognitionView


api = application = falcon.API()

recognition_view = RecognitionView()
api.add_route('/api/v1/recognition', recognition_view)
