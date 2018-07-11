#!/usr/bin/env bash
SUDO docker service create --name swarm-api --publish published=5005,target=5007,mode=host --detach --mount type=bind,source=/var/run/docker.sock,target=/var/run/docker.sock --constraint 'node.labels.swarm-api==true' 192.168.1.70:443/swarm-api
echo 'External port:' $EXT_PORT
