## petprofies-service
[![Build Status](https://travis-ci.com/jferrater/petclinic-app.svg?branch=master)](https://travis-ci.com/jferrater/petclinic-app) [![SonarCloud Quality Gate](https://sonarcloud.io/api/project_badges/measure?project=jferrater_petprofiles-service&metric=alert_status)](https://sonarcloud.io/dashboard?id=jferrater_petprofiles-service)
<br>
A microservice which provides REST API for managing pet profiles.

### Prerequisite
- docker-compose

### Quick Start
1. ``git clone https://github.com/jferrater/petclinic-app.git``
2. ``cd petclinic-app/petprofiles-service``
3. ``./gradlew bootJar``
4. ``docker-compose up``

### Swagger UI
http://localhost:8080/petprofiles-service/swagger-ui.html

### Open API 3.0 JSON
http://localhost:8080/petprofiles-service/v3/api-docs

### Database Migration
Database migration is managed by Flyway scripts in the classpath:db/migration.