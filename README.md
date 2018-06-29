# Adidas Code Challenge

This project contains the development made by Fernando to solve the challenge of the Adidas code. To complete this challenge, two microservices have been programmed using spring boot and java 8. To manage these services, the Netflix Spring cloud library has been used, adding the Eureka automatic discovery component and the Zuul gateway service. As a database, MongoDB has been chosen and docker-compose has been used to deploy the Docker containers.


The data model provided in the challenge requirements has been translated to this JSON structure:
```
{
   “city”: String,
   “destination”: String,
   “departureTime”: LocalTime(“hh:mm:ss”),
   “arrivalTime”: LocalTime(“hh:mm:ss”)
}
```

Connections service offers the API to manage this data model.

Using this model the problem can be solved with a graph approach, where “city” and “destination” are nodes and “departureTime” and “arrivalTime” can be used for calculating the weight of the edges. To obtain the itineraries, I selected the Dijkstra algorithm, using the weight obtained with departure and arrival times for faster path, and changing this weights to 1 for find the most direct path. This operations are performed by the Itineraries service. The Dijkstra algorithm implementation is based in this article: http://www.baeldung.com/java-dijkstra

### Content of the repository

In this repository you can find the following objects:
```
\
 |- connections: This folder contains the connections service.
 |- docker-mongo: Contains the Dockerfile to build the database image.
 |- docker-service: Contains the Dockerfile to build the services.
 |- eureka-server: Contains the autodiscovery service.
 |- itineraries: The itineraries service.
 |- zuul: The API Gateway service. Security validation will be performed here.
 |- adidas.postman_collection.json: Postman collection for test purposes.
 |- build.gradle: File that contains the task to deploy artifacts in docker folder.
 |- docker-compose.yml: Docker compose file to build and run docker containers.
 |= swagger-*.yml: Files with swagger documentation.
```
### Development considerations
Spring Security has been used to implement security. The corresponding validations are made in the gateway service (Zuul). An ID provider system has not been implemented, instead a basic authentication (basic auth) has been implemented, with username and password.

There are two valids roles (user and admin). Admin role can use the connections API to performs CRUD operation over connections model. Users can query connections by city and perform queries over the itineraries service. The validation of username and password are performed in memory and there are the following valids pairs:

```
U: admin 
P: admin 
Roles: User, Admin

U: adidas
P: adidas
Roles: User
```


## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites

To run this projects you must to install “gradle”, “docker” and “docker-compose” tools.
You can check if this tools are in your system using:
```
$ gradle --version
$ docker -v
$ docker-compose -v
```

## Running the tests

Unitary tests running automatically when projects are built. If you want to run the tests without build execute the gradle task:

```
$ gradle test
```

### Deployment

To helps to deploy, a gradle task has been developed on root folder. This task build and copies the necessary files to corresponding docker folder.

```
$ gradle deployJarFiles
```

Then, only needs to run the following commands to build the necessaries docker containers and run it. 
**Note**: _Please, ensure that you have available the port 8880, because is the port assigned to the gateway service. If you need to change this port, please edit the file docker-compose.yml. To build properly the docker images, you need internet connection, because the mongo image needs download the database engine from mongo repository._

```
$ docker-compose build
$ docker-compose up -d
```

When this step are complete, the system can be accessed through the port 8880 using this schema: _http://{deployment-address}/{service}/{path}_
```
Connections service (“/connections”):
GET /connections/v1/
GET /connections/v1/{id}
POST /connections/v1/
PUT /connections/v1/{id}
DELETE /connections/v1/{id}
GET /cities/v1/{city}

Itineraries service (“/itineraries”):

GET /itineraries/v1/{source}?destination={destination}
```

You can find the swagger documentation for this API’s in files _swagger-connections-api.yml_ and _swagger-itineraries-api.yml_

In order to help in tests and validation tasks one postman collection with API calls has been add to project.


## Authors

* **Fernando de los Ríos Fernández**