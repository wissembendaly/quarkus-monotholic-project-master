# running a quarkus app in docker then in minikube service

First we will run our application in container Then we will use minikube to run it in a single-node Kubernetes cluster using minikube


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

## Browsing our app 
now our project works just fine. The link:
http://localhost:8080/api/q/swagger-ui/


## Using Kubernetes:
first we start minikube service
```shell script
minikube start
```
Then we create the POD containing our bd
```shell script
kubectl run postgres-pod --image=wissembendaly/postgres-for-quarkus-app:latest --port=5432
```

Then we create the POD containing our app
```shell script
kubectl run quarkus-first-app --image=wissembendaly/quarkus-app-test --port=8080
```

if we didnt expose the port we can run this command to bind the first port on our local machine to the second one on the POD:
```shell script
kubectl port-forward quarkus-first-app 8080:8080
```
 finally we can navigate in our app on:
 http://localhost:8080/api/q/swagger-ui/
