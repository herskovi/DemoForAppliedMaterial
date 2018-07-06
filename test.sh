sudo docker build -f Dockerfile.simple-flask -t hackathon2018:simple-flask .
sudo docker service create --name simple-flask --publish published=5000,target=5000,mode=host --detach hackathon2018:simple-flask
sudo docker build -f Dockerfile.simple-flask -t hackathon2018:simple-flask-2 .
#image hackathon2018:simple-flask-2 could not be accessed on a registry to record
#its digest. Each node will access hackathon2018:simple-flask-2 independently,
#possibly leading to different nodes running different
#versions of the image.
sudo docker service update simple-flask --image hackathon2018:simple-flask-2
