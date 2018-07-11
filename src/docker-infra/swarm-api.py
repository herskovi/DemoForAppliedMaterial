import flask
from flask_restful import Resource, Api, reqparse
import docker
docker_client = docker.from_env()
import json

def output_html(data, code, headers=None):
    data = data.replace('\n', '<br/>\n').replace(' ', '&nbsp;')
    resp = flask.Response(data, mimetype='text/html', headers=headers)
    resp.status_code = code
    return resp

class CmdInfo(Resource):

    name = 'info'

    def __init__(self):
        parser = reqparse.RequestParser()
        self.parser = parser

    def get(self):
        args = self.parser.parse_args()
        info = docker_client.info()
        return output_html(json.dumps(info, indent=4), 200)

class CmdTest(Resource):

    name = 'test'

    def __init__(self):
        parser = reqparse.RequestParser()
        self.parser = parser

    def get(self):
        args = self.parser.parse_args()
        s = 'test1\ntest2\n'
        return output_html(s, 200)

class MyApp:

    all_commands = [CmdInfo, CmdTest]

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
