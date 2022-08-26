# simple cloud app

Local docker compose configuration files.

## docker-compose.yml

Full app.

### services

#### mysql

- `jdbc:mysql://localhost:3306/customer_service`
- `jdbc:mysql://localhost:3306/reservation_service`
- `jdbc:mysql://localhost:3306/room_service`
- user/password: `app`/`app`

#### endpoints

- [/api/doc](http://localhost/api/doc/)
- [/api/v1/customers](http://localhost/api/v1/customers)
- [/api/v1/reservations](http://localhost/api/v1/reservations)
- [/api/v1/rooms](http://localhost/api/v1/rooms)
- [/api/v1/room-reservations](http://localhost/api/v1/room-reservations)

### start

```shell
docker compose up
```

### stop

```shell
docker compose down
```

## docker-compose-local-dev.yaml

Useful for local development.

### services

#### mysql

- `jdbc:mysql://localhost:3306/customer_service`
- `jdbc:mysql://localhost:3306/reservation_service`
- `jdbc:mysql://localhost:3306/room_service`
- user/password: `app`/`app`

### start

```shell
docker compose -f docker-compose-local-dev.yaml up
```

### stop

```shell
docker compose -f docker-compose-local-dev.yaml down
```

## docker-compose-no-proxy.yaml

Like `docker-compose.yaml` without _traefik_ and _swagger-ui_.
