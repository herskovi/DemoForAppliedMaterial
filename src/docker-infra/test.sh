# Initial tests
#######################
sudo docker build -f Dockerfile.flask -t python-flask:3.6-alpine
sudo docker build -f Dockerfile.simple-flask -t hackathon2018:simple-flask .
sudo docker service create --name simple-flask --publish published=5000,target=5000,mode=host --detach hackathon2018:simple-flask
sudo docker build -f Dockerfile.simple-flask -t hackathon2018:simple-flask-2 .
#image hackathon2018:simple-flask-2 could not be accessed on a registry to record
#its digest. Each node will access hackathon2018:simple-flask-2 independently,
#possibly leading to different nodes running different
#versions of the image.
sudo docker service update simple-flask --image hackathon2018:simple-flask-2

# Creating registry
#######################
openssl req -newkey rsa:4096 -nodes -sha256 -keyout domain.key -x509 -days 365 -out domain.crt
sudo docker secret create domain.key domain.key
sudo docker secret create domain.crt domain.crt
docker node update --label-add registry=true 9hwhxey57fgk2f51u6kzgrm64
docker service create --name registry --secret domain.crt --secret domain.key --constraint 'node.labels.registry==true' -e REGISTRY_HTTP_ADDR=0.0.0.0:443 -e REGISTRY_HTTP_TLS_CERTIFICATE=/run/secrets/domain.crt -e REGISTRY_HTTP_TLS_KEY=/run/secrets/domain.key --publish published=443,target=443 --replicas 1 registry:2 
sudo docker tag hackathon2018:simple-flask-2 127.0.0.1:443/hackathon2018:simple-flask-2
sudo docker push 127.0.0.1:443/hackathon2018:simple-flask-2

# fixing registry
#  866  openssl req -new -key domain.key -out domain.csr -config openssl.cnf
#  867  openssl x509 -req -sha256 -signkey domain.key -days 365 -out domain.crt -extfile openssl.cnf -extensions v3_req -in domain.csr
#  868  sudo docker service rm registry
#  869  sudo docker secret rm domain.crt domain1.crt domain.key domain1.key
#  870  sudo docker secret create domain.key domain.key
#  871  sudo docker secret create domain.crt domain.crt
#  872  sudo docker service create --name registry --secret domain.crt --secret domain.key --constraint 'node.labels.registry==true' -e REGISTRY_HTTP_ADDR=0.0.0.0:443 -e REGISTRY_HTTP_TLS_CERTIFICATE=/run/secrets/domain.crt -e REGISTRY_HTTP_TLS_KEY=/run/secrets/domain.key --publish published=443,target=443 --replicas 1 registry:2 
#  873  sudo docker push 192.168.1.106:443/simple-flask:rpi
#The push refers to repository [192.168.1.106:443/simple-flask]                                                  
#Get https://192.168.1.106:443/v2/: x509: certificate signed by unknown authority                                

# Registry solution
# add { "insecure-registries": ["192.168.1.0/24"] } to /etc/docker/daemon.json



# Adding raspbery-pi
########################
nmap -A -T4 192.168.1.0/24
ssh pi@192.168.1.105 docker swarm join --token SWMTKN-1-32bkpc0nrku0d6q0inf78my4004a0nen43tdp1rw92od49942n-cbija0dilspka0robszjj50lx 192.168.1.104:2377
sudo docker node update kn533knu7n4bfydjnl7ovmaud --label-add arch=rpi

sudo docker build -f Dockerfile.flask-rpi -t python-flask:3.6-alpine-rpi .
sudo docker build -f Dockerfile.simple-flask-rpi -t simple-flask:rpi .
sudo docker tag simple-flask:rpi 127.0.0.1:443/simple-flask:rpi
sudo docker push 127.0.0.1:443/simple-flask:rpi

sudo docker service create --name simple-flask-rpi --publish published=5005,target=5005,mode=host --detach --constraint 'node.labels.arch==rpi' 127.0.0.1:443/simple-flask:rpi                                              
# node  dead ... - because of master node ip address change
# fix master node ip address change
ip addr add 192.168.1.106/24 dev wlp2s0 label wlp2s0:1

# then: unsupported platform
# Fix architecture ("unsupported platform" error)
sudo mkdir /root/.docker
echo '{"experimental": "enabled"}' | sudo tee /root/.docker/config.json
sudo docker manifest create 127.0.0.1:443/simple-flask:rpi 127.0.0.1:443/simple-flask:rpi                 
sudo docker manifest annotate 127.0.0.1:443/simple-flask:rpi 127.0.0.1:443/simple-flask:rpi --os linux --arch arm
sudo docker manifest push 127.0.0.1:443/simple-flask:rpi
#failed to put manifest 127.0.0.1:443/simple-flask:rpi: Put http://127.0.0.1:443/v2/simple-flask/manifests/rpi: net/http: HTTP/1.x transport connection broken: malformed HTTP response "\x15\x03\x01\x00\x02\x02"

#########################################
#   HACKATHON                           !
########################################
docker service create --name registry --constraint 'node.labels.registry==true' -e REGISTRY_HTTP_ADDR=0.0.0.0:443 --publish published=443,target=443 --replicas 1 registry:2 
sudo docker node update dellstudio-manjaro --label-add 'registry=true'
sudo docker build -f Dockerfile.flask-docker -t python-flask-docker:3.6-alpine .
sudo docker build -f Dockerfile.swarm-api -t swarm-api .
sudo docker node update foximur-files --label-add 'swarm-api=true'
SUDO docker service create --name swarm-api --publish published=5005,target=5007,mode=host --detach --mount type=bind,source=/var/run/docker.sock,target=/var/run/docker.sock --constraint 'node.labels.swarm-api==true' 192.168.1.70:443/swarm-api
