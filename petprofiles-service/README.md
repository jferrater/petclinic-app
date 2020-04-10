## petprofiles-service
[![Build Status](https://travis-ci.com/jferrater/petclinic-app.svg?branch=master)](https://travis-ci.com/jferrater/petclinic-app) [![SonarCloud Quality Gate](https://sonarcloud.io/api/project_badges/measure?project=jferrater_petprofiles-service&metric=alert_status)](https://sonarcloud.io/dashboard?id=jferrater_petprofiles-service)
<br>
A microservice which provides REST API for managing pet profiles.

### Pre-requisites
- java 11
- docker
- docker-compose

### Quick Start
``docker run -dit -p 8080:8080 --name petprofiles-service jmferrater/petprofiles-service:0.0.2``<br>
#### REST API Documentation
- UI: http://localhost:8080/petprofiles-service/swagger-ui.html
- JSON: http://localhost:8080/petprofiles-service/v3/api-docs

### Supported Databases
- H2
- MariaDB
- PostgreSQL

The application uses H2 database as a default. 

#### Overriding default database
```shell script
docker run -dit -p 8080:8080 \
    -e SPRING_DATASOURCE_DRIVER_CLASS_NAME: org.mariadb.jdbc.Driver \
    -e SPRING_DATASOURCE_URL: jdbc:mariadb://maria-database:3306/petprofiles_service \
    -e SPRING_DATASOURCE_USERNAME: admin \
    -e SPRING_DATASOURCE_PASSWORD: MangaonTaNiny0! \
    -e SPRING_JPA_PROPERTIES_HIBERNATE_DIALECT: org.hibernate.dialect.MariaDBDialect \
    --name petprofiles-service jmferrater/petprofiles-service:0.0.2
```

### Development
#### Building
1. ``git clone https://github.com/jferrater/petclinic-app.git``
2. ``cd petclinic-app/petprofiles-service``
3. ``./gradlew clean build``

#### Running the service with H2 Database
``./gradlew bootRun``

#### Running the service using MariaDB with Docker Compose
1. ``./gradlew bootJar``
2. ``docker-compose up --build``

#### Database Migration
Database schema and migration  are managed by Flyway scripts in the classpath:db/migration.
