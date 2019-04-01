import falcon
from falcon_multipart.middleware import MultipartMiddleware

from config import configuration
configuration()
from views import RecognitionView

api = application = falcon.API(middleware=[MultipartMiddleware()])

recognition_view = RecognitionView()
api.add_route('/api/v1/recognition', recognition_view)
