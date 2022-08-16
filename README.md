# Simple Cloud App

Simple Cloud Application based on Spring Cloud.

## Build

- [jdk17](https://adoptium.net/)
- [maven](https://maven.apache.org/)

```shell
mvn clean install
```

## Local dev

- [Docker](https://docs.docker.com/get-docker/)
- [Docker Compose](https://docs.docker.com/get-docker/)
- To start local services run in _simple-cloud-app_ dir

```shell
docker-compose -f docker-compose-local-dev.yaml up -d
```

- To stop local services run in _simple-cloud-app_ dir

```shell
docker-compose -f docker-compose-local-dev.yaml down
```

## Data services

- [customer-service](./customer-service/README.md)
- [reservation-service](./reservation-service/README.md)
- [room-service](./room-service/README.md)

## Config server

- [config-server](./config-server/README.md)

## Eureka server

- [eureka-server](./eureka-server/README.md)

## Business services

- [room-reservation-service](./room-reservation-service/README.md)
