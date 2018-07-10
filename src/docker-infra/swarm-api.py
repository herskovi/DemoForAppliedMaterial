import flask
from flask_restful import Resource, Api, reqparse
import docker
docker_client = docker.from_env()

class CmdInfo(Resource):

    name = 'info'

    def __init__(self):
        parser = reqparse.RequestParser()
        self.parser = parser

    def get(self):
        args = self.parser.parse_args()
        info = docker_client.info()
        return flask.jsonify(info)


class MyApp:

    all_commands = [CmdInfo]

    def __init__(self):
        app = flask.Flask('swarm-api')
        api = Api(app)
        for cmd in self.all_commands:
            api.add_resource(cmd, '/'+cmd.name)
        self.app = app
        self.api = api

    def run(self):
        self.app.run(host='0.0.0.0', port=5007)

if __name__ == '__main__':
    MyApp().run()
