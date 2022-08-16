# Room Service

- plain package structure
- [Flyway](https://flywaydb.org) as db migrations tool
- [Testcontainers](https://www.testcontainers.org) - MySQL
    - [Controller test](./src/test/java/sk/janobono/sca/roomservice/controller/RoomControllerIT.java)
- [Exception handling](./src/main/java/sk/janobono/sca/roomservice/config/ControllerAdvisor.java)
- Layer tools to separate app code from dependencies - [Dockerfile](./Dockerfile) and [pom.xml](./pom.xml)
