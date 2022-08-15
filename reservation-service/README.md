# Reservation Service

- package structure with _api_, _dal_ packages
- [Flyway](https://flywaydb.org) as db migrations tool
- [Testcontainers](https://www.testcontainers.org) - MySQL
    - [Controller test](./src/test/java/sk/janobono/sca/reservationservice/api/controller/ReservationControllerIT.java)
- [Exception handling](./src/main/java/sk/janobono/sca/reservationservice/config/ControllerAdvisor.java)
- Layer tools to separate app code from dependencies - [Dockerfile](./Dockerfile) and [pom.xml](./pom.xml)

## build

```shell
docker build -t sk.janobono/sca-reservation-service .
```
