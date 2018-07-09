from flask import Flask
from flask_restful import Resource, Api, reqparse

class CmdInfo(Resource):

    name = 'info'

    def __init__(self):
        parser = reqparse.RequestParser()
        self.parser = parser

    def get(self):
        args = self.parser.parse_args()
        args.update({'cmd': type(self).__name__})
        return args


class MyApp:

    all_commands = [CmdInfo]

    def __init__(self):
        app = Flask('swarm-api')
        api = Api(app)
        for cmd in self.all_commands:
            api.add_resource(cmd, '/'+cmd.name)
        self.app = app
        self.api = api

    def run(self):
        self.app.run(host='0.0.0.0', port=5007)

if __name__ == '__main__':
    MyApp().run()
