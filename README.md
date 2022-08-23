# Simple Cloud App

Simple Cloud Application based on Spring Cloud.

## Build

- [jdk17](https://adoptium.net/)
- [maven](https://maven.apache.org/)
- [Docker](https://docs.docker.com/get-docker/)

```shell
mvn clean install
```

## Local dev

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

## Business services

- [room-reservation-service](./room-reservation-service/README.md)
