from flask import Flask
from flask_restful import Resource, Api, reqparse

class CmdDocker(Resource):
    def __init__(self):
        parser = reqparse.RequestParser()
        parser.add_argument('par1')
        self.parser = parser

    def get(self):
        args = self.parser.parse_args()
        args.update({'cmd': type(self).__name__})
        return args

class MyApp:
    def __init__(self):
        app = Flask('swarm-api')
        api = Api(app)
        api.add_resource(CmdDocker, '/docker')
        self.app = app
        self.api = api
    def run(self):
        self.app.run(host='0.0.0.0', port=5007)

if __name__ == '__main__':
    MyApp().run()
