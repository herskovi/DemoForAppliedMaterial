sudo docker build -f Dockerfile.simple-flask -t hackathon2018:simple-flask .
sudo docker service create --name simple-flask --publish published=5000,target=5000,mode=host --detach hackathon2018:simple-flask
sudo docker build -f Dockerfile.simple-flask -t hackathon2018:simple-flask-2 .
sudo docker service update simple-flask --image hackathon2018:simple-flask-2
