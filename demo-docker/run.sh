docker-compose down

docker rmi demo-docker:demo-docker-0.0.1

cd demo-docker
mvn clean install -DskipTests

docker build -t demo-docker:0.0.1 -f Dockerfile .

docker-compose up -d