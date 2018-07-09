#!/usr/bin/env bash
sudo docker service create --name swarm-api --publish published=5007,target=5007,mode=host --detach --mount type=bind,source=/var/run/docker.sock,target=/var/run/docker.sock 192.168.1.106:443/swarm-api
