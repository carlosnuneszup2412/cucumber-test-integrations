###  Docker

Start
```shell
docker-compose up -d
```
API
```shell
./gradlew bootRun
```

###  Criar cliente
```shell
curl -v --request POST \
--url http://localhost:8080/v1/clientes \
--header 'Content-Type: application/json' \
--data '{
    "cpf": "980981200912",
    "nome": "Carlos",
    "email": "carlos@test.com",
    "conta": {
      "status": "ATIVA",
      "tipo": "CORRENTE"
    }
}'
```

###  Buscar cliente
```shell
curl --request GET --url http://localhost:8080/v1/clientes/980981200912
```

Stop
```shell
docker-compose down
./gradlew -stop
```

## pgAdmin

http://localhost:5050/

```properties
admin@admin.com
password
```

Object Explorer > Servers > Register > Server ...

General
```properties
Name: stackspot
```
Connection
```properties
HostName: db
database: stackspot
username: postgres
password: password
```
and Save


## OpenAPI
http://localhost:8080/swagger-ui.html 

# Cucumber

```shell
./gradlew cucumber
```

Relatório em 
[./build/reports/tests/test/index.html](./build/reports/tests/test/index.html)

### Insomnia
[cucumber-test.json](insomnia/cucumber-test.json)
