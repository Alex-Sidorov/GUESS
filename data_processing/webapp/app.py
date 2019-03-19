import falcon

from config import configuration
configuration()
from views import RecognitionView

api = application = falcon.API()

recognition_view = RecognitionView()
api.add_route('/api/v1/recognition', recognition_view)
