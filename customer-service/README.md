# Customer Service

- package structure with _api_, _dal_ packages
- [Flyway](https://flywaydb.org) as db migrations tool
- [Testcontainers](https://www.testcontainers.org) - MySQL
    - [Controller test](./src/test/java/sk/janobono/sca/customerservice/api/controller/CustomerControllerIT.java)
- [Exception handling](./src/main/java/sk/janobono/sca/customerservice/config/ControllerAdvisor.java)
- Layer tools to separate app code from dependencies - [Dockerfile](./Dockerfile) and [pom.xml](./pom.xml)
