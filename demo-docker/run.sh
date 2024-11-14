#!/bin/bash
set -e  # Exit immediately if a command exits with a non-zero status

docker-compose down

docker rmi demo-docker:0.0.1 || true

cd /Users/oscarlo/github/bc2405p/demo-docker  # Update to your project path

mvn clean install -DskipTests

docker-compose build

docker-compose up -d
