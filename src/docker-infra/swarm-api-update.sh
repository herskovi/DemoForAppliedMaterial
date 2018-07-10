#!/usr/bin/env bash
REPO='192.168.1.106:443'
DOCKERFILE='Dockerfile.swarm-api'
IMAGE_NAME='swarm-api'
CONTEXT_DIR='.'
SERVICE_NAME='swarm-api'
sudo docker build -f $DOCKERFILE -t $IMAGE_NAME -t $REPO/$IMAGE_NAME $CONTEXT_DIR
sudo docker push $REPO/$IMAGE_NAME
sudo docker service update $SERVICE_NAME --image $REPO/$IMAGE_NAME

curl localhost:5007/info
