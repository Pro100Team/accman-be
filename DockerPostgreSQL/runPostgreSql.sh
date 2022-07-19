set -x 

docker pull postgres

#remove if needed
#docker rm postgres-db

cd ./POSTGRESQL-13.7
docker-compose up -d