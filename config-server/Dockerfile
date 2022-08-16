FROM eclipse-temurin:17-alpine as builder
WORKDIR app
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
RUN java -Djarmode=layertools -jar app.jar extract

FROM eclipse-temurin:17-alpine as production

RUN apk update && apk add git

WORKDIR /app

RUN addgroup --gid 1000 app; \
    adduser --disabled-password --gecos "" --home "$(pwd)" --ingroup app --no-create-home --uid 1000 app

RUN chown -R app:app /app

USER app

EXPOSE 9000

COPY --from=builder app/dependencies/ ./
COPY --from=builder app/spring-boot-loader/ ./
COPY --from=builder app/snapshot-dependencies/ ./
COPY --from=builder app/application/ ./

ENTRYPOINT ["java","org.springframework.boot.loader.JarLauncher"]
