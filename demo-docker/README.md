# Demo Docker Project Setup
<h5>This README provides detailed instructions on how to set up and run the Demo Docker Project, which utilizes Spring Boot, PostgreSQL, and Redis within Docker containers orchestrated by Docker Compose.</h5>

#### Table of Contents
- Project Overview
- Prerequisites
- Setup Instructions
  1. Clone the Repository
  2. Build the Maven Project
  3. Build and Run Docker Containers
    - Using run.sh Script
    - Manually Executing Commands
  4. Verify Containers are Running
  5. Access the Application
- Configuration Details
  - application.yml
  - docker-compose.yml
  - Dockerfile
  - run.sh
- Important Points and Reminders
- Conclusion
### Project Overview
The Demo Docker Project is a sample application demonstrating how to containerize a Spring Boot application with PostgreSQL and Redis using Docker and Docker Compose. The application is configured to connect to PostgreSQL as its primary database and Redis for caching purposes.

##### Technologies Used:

- ##### Java 17
- ##### Spring Boot
- ##### PostgreSQL 15
- ##### Redis 7
- ##### Docker & Docker Compose
- ##### Maven
### Prerequisites
Before you begin, ensure you have the following installed on your system:

- ##### Docker (version 20.10 or higher)
- ##### Docker Compose (version 1.29 or higher)
- ##### Maven (version 3.6 or higher)
- ##### Java 17 (JDK 17)
### Setup Instructions
Follow the steps below to set up and run the project:

#### 1. Clone the Repository
```bash
git clone https://github.com/yourusername/demo-docker.git
cd demo-docker
```
#### 2. Build the Maven Project
Navigate to the project directory and build the project using Maven:
```bash
mvn clean install -DskipTests
```
<b>Note:</b> Skipping tests (-DskipTests) is optional. Remove it if you wish to run tests during the build.

#### 3. Build and Run Docker Containers
You can use the provided run.sh script or execute the commands manually.

##### Using run.sh Script
Ensure the script has execute permissions:

```bash
chmod +x run.sh
```
Run the script:
```bash
./run.sh
```
##### Manually Executing Commands
Stop and remove any existing containers and images:

```bash
docker-compose down
docker rmi demo-docker:0.0.1 || true
```
Build Docker images:

```bash
docker-compose build
```
Start Docker containers in detached mode:

```bash
docker-compose up -d
```
#### 4. Verify Containers are Running
Check the status of the containers:

```bash
docker-compose ps
```
You should see three services running: ==demo-docker==, ==postgres==, and ==redis==.

#### 5. Access the Application
The application should now be running and accessible at http://localhost:8190.

### Configuration Details
Understanding the configuration files is crucial for customization and troubleshooting.

##### application.yml
This is the Spring Boot application configuration file.

```yaml
server:
  port: 8080  # Application runs on port 8080 inside the container

spring:
  datasource:
    url: ${SPRING_DATASOURCE_URL:jdbc:postgresql://postgres:5432/bc2405p-demo-docker}
    username: ${SPRING_DATASOURCE_USERNAME:postgres}
    password: ${SPRING_DATASOURCE_PASSWORD:admin1234}
    driverClassName: ${SPRING_DATASOURCE_DRIVER_CLASS_NAME:org.postgresql.Driver}

  jpa:
    show-sql: true
    hibernate:
      ddl-auto: create-drop
    properties:
      hibernate:
        format_sql: true
        default_schema: public
        jdbc:
          lob:
            non_contextual_creation: true

  data:
    redis:
      host: ${SPRING_REDIS_HOST:redis}
      port: ${SPRING_REDIS_PORT:6379}
      database: 1
      lettuce:
        pool:
          max-wait: -1ms
          max-active: 8
          max-idle: 8
          min-idle: 0
      timeout: 10000ms
      cache:
        type: redis

database:
  db-name: "bc2405p-demo-docker"
```
##### Key Points:

- <b>Database Configuration:</b> Connects to PostgreSQL running in a Docker container named postgres.
- <b>Redis Configuration:</b> Connects to Redis running in a Docker container named redis.
- <b>Port Configuration:</b> The application runs on port 8080 inside the container.
##### docker-compose.yml
Defines the Docker services and how they interact.

```yaml
version: "3.8"
services:
  demo-docker:
    build:
      context: .
      dockerfile: Dockerfile
    image: demo-docker:0.0.1
    networks:
      - app-network
    ports:
      - "8190:8080"  # Maps local port 8190 to container port 8080
    depends_on:
      - postgres
      - redis
    environment:
      SPRING_DATASOURCE_URL: jdbc:postgresql://postgres:5432/bc2405p-demo-docker
      SPRING_DATASOURCE_USERNAME: postgres
      SPRING_DATASOURCE_PASSWORD: admin1234
      SPRING_DATASOURCE_DRIVER_CLASS_NAME: org.postgresql.Driver
      SPRING_REDIS_HOST: redis
      SPRING_REDIS_PORT: 6379

  postgres:
    image: postgres:15
    environment:
      POSTGRES_USER: postgres
      POSTGRES_PASSWORD: admin1234
      POSTGRES_DB: bc2405p-demo-docker
      PGDATA: /var/lib/postgresql/data/pgdata15
    networks:
      - app-network
    volumes:
      - pgdata:/var/lib/postgresql/data/pgdata

  redis:
    image: redis:7
    networks:
      - app-network
    ports:
      - "6379:6379"
    volumes:
      - redis_data:/data

volumes:
  redis_data:
  pgdata:

networks:
  app-network:
    driver: bridge
```
##### Key Points:

- <b>Services Defined: </b>demo-docker, postgres, and redis.
- <b>Networks:</b> All services are connected via app-network.
- <b>Volumes:</b>Data persistence for PostgreSQL and Redis using Docker volumes.
- <b>Port Mapping:</b> Application accessible on localhost:8190, PostgreSQL and Redis are not exposed externally.
#### Dockerfile
Defines how the Docker image for the application is built.

```dockerfile
FROM openjdk:17

WORKDIR /app

COPY /target/demo-docker-0.0.1-SNAPSHOT.jar demo-docker-0.0.1-SNAPSHOT.jar

CMD ["sh", "-c", "java -jar demo-docker-0.0.1-SNAPSHOT.jar"]

EXPOSE 8190 8080
```
##### Key Points:

- <b>Base Image: </b>Uses OpenJDK 17.
- <b>Working Directory:</b> ==/app== inside the container.
- <b>Application JAR: </b>Copies the built JAR file from the ==target== directory.
- <b>Command:</b> Runs the application using ==java -jar==.
- <b>Ports Exposed: </b>Declares ports ==8190== and ==8080== (though only ==8080== is used inside the container).
#### run.sh
A shell script to automate the build and run process.

```bash
#!/bin/bash
set -e  # Exit immediately if a command exits with a non-zero status

docker-compose down

docker rmi demo-docker:0.0.1 || true

cd /Users/oscarlo/github/bc2405p/demo-docker  # Update to your project path

mvn clean install -DskipTests

docker-compose build

docker-compose up -d
```
##### Key Points:

- <b>Stops Existing Containers:</b> Ensures a clean state.
- <b>Removes Existing Image:</b> Forces rebuild of the application image.
- <b>Builds Maven Project:</b> Compiles the application.
- <b>Builds and Starts Containers:</b> Uses Docker Compose to build images and run containers.
### Important Points and Reminders
- <b>Update Project Path in run.sh:</b> Ensure the cd command in run.sh points to your actual project directory.

```bash
cd /path/to/your/project  # Update this line
```
- <b>Port Configuration:</b>

  - The application runs on port ==8080== inside the container.
  - It is mapped to ==localhost:8190== on the host machine (=="8190:8080"== in docker-compose.yml).
  - Ensure these ports are not in use by other applications.
- <b>Data Persistence:</b>

  - PostgreSQL data is stored in the Docker volume ==pgdata==.
  - Redis data is stored in the Docker volume ==redis_data==.
  - Data persists across container restarts but will be removed if you delete the volumes.
- <b>Environment Variables:</b>

  - Database credentials and connection details are specified in ==docker-compose.yml== under ==environment==.
  - Ensure these match the settings in ==application.yml==.
- <b>Common Issues:</b>

  - Port Conflicts: If ports ==8190== or ==6379== are in use, modify the ==ports== section in docker-compose.yml.
  - Docker Permissions: On Linux, you may need to run Docker commands with sudo or add your user to the ==docker== group.
  - Maven Build Failures: Ensure Java 17 and Maven are correctly installed and configured.
- <b>Logging and Monitoring:</b>

  - View application logs with ==docker-compose logs -f demo-docker==.
  - Access PostgreSQL logs with ==docker-compose logs -f postgres==.
  - Access Redis logs with ==docker-compose logs -f redis==.
- <b>Stopping the Application:</b>

  - To stop all containers, run:

```bash

docker-compose down
```
- <b>Cleaning Up Resources:</b>

To remove all containers, images, networks, and volumes associated with the project:

```bash
docker-compose down --rmi all --volumes --remove-orphans
```
### Conclusion
You have now set up and run the Demo Docker Project, which demonstrates a Spring Boot application integrated with PostgreSQL and Redis within a Dockerized environment. Feel free to explore the application further, modify configurations, and extend its functionality.