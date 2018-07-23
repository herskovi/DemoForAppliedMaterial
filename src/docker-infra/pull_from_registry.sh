#!/usr/bin/env bash
REPO='192.168.1.3:443'
IMAGE_NAME=$1
IMAGE_NAME_IN_REPO="$REPO/$IMAGE_NAME"
docker pull $IMAGE_NAME_IN_REPO
docker tag $IMAGE_NAME_IN_REPO $IMAGE_NAME
echo 'Available as' $IMAGE_NAME

