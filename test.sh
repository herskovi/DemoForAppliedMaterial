# Initial tests
#######################
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
docker service create \
    --name registry \
    --secret domain.crt \
    --secret domain.key \
    --constraint 'node.labels.registry==true' \
    -e REGISTRY_HTTP_ADDR=0.0.0.0:443 \
    -e REGISTRY_HTTP_TLS_CERTIFICATE=/run/secrets/domain.crt \
    -e REGISTRY_HTTP_TLS_KEY=/run/secrets/domain.key \
    --publish published=443,target=443 \
    --replicas 1 \
    registry:2 
sudo docker tag hackathon2018:simple-flask-2 127.0.0.1:443/hackathon2018:simple-flask-2
sudo docker push 127.0.0.1:443/hackathon2018:simple-flask-2

# Adding raspbery-pi
########################
nmap -A -T4 192.168.1.0/24
ssh pi@192.168.1.105 docker swarm join --token SWMTKN-1-32bkpc0nrku0d6q0inf78my4004a0nen43tdp1rw92od49942n-cbija0dilspka0robszjj50lx 192.168.1.104:2377
