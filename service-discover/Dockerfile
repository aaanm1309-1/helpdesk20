FROM openjdk:21-jdk

ARG JAR_FILE=./build/libs/*.jar

EXPOSE 8761

COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java", "-jar", "/app.jar"]

# docker build -t aaanm13/helpdesk-service-discover:1.0.0 .
# docker push aaanm13/helpdesk-service-discover:1.0.0
# docker container run -d -p 8761:8761 --name helpdesk-service-discover -e CONFIG_SERVER_URI=http://localhost:8888 aaanm13/helpdesk-service-discover:1.0.0
# docker container ls
# docker container stop $(docker ps -a -q)
# docker container rm $(docker ps -a -q)
