from flask import Flask
from flask_restful import Resource, Api, reqparse

app = Flask('simple-flask')
api = Api(app)

parser = reqparse.RequestParser()
parser.add_argument('par1')

parser2 = reqparse.RequestParser()
parser2.add_argument('par1')
parser2.add_argument('par2')

class Command1(Resource):
    def get(self):
        args = parser.parse_args()
        args.update({'cmd': type(self).__name__})
        return args

class Command2(Resource):
    def get(self):
        args = parser2.parse_args()
        args.update({'cmd': type(self).__name__})
        return args

api.add_resource(Command1, '/cmd1')
api.add_resource(Command2, '/cmd2')

if __name__ == '__main__':
    app.run(host='0.0.0.0')

