# firstone Project

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .


## creating db container

The application can be packaged using:
```shell script
docker run -d --name postgres-container -e POSTGRES_USER=developer -e POSTGRES_PASSWORD=p4SSW0rd -e POSTGRES_DB=demo  -p 5432:5432 postgres:13
```
Then go to application properties and set ip address to the address of the container
we run this command to get the ip address
```shell script
docker exec postgres-container hostname -i
```
## database config
first we copy the scripts to create database into the container
```shell script
docker cp src\main\resources\db\migration\initdb.sql postgres-container:/dbscripts/init
docker cp src\main\resources\db\migration\insert.sql  demo-postgres:/dbscripts/insert
```
Then we need to execute these scripts
```shell script 
docker exec postgres-container psql -h localhost -p 5432 -d demo  -U developer -f /dbscripts/init
docker exec postgres-container psql -h localhost -p 5432 -d demo  -U developer -f /dbscripts/insert
```


## Packaging and running the application

The application can be packaged using:
```shell script
mvn package
```

## create docker image 
```shell script
docker build -f src/main/docker/Dockerfile.jvm -t quarkus/firstone-jvm .
```

## create container
from the image we created we run this command to create container
```shell script
docker run -i --rm -p 8080:8080 quarkus/firstone-jvm
```

