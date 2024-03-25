FROM openjdk:21-jdk

ARG JAR_FILE=./build/libs/*.jar

EXPOSE 0

COPY ${JAR_FILE} app.jar

#ENTRYPOINT ["java", "-jar", "/app.jar"]
#ENTRYPOINT ["java", "-Dserver.port=$PORT","-jar", "/app.jar"]
ENTRYPOINT ["sh","-c", "java -Dserver.port=$PORT -jar /app.jar"]

# docker build -t aaanm13/helpdesk-user-service-api:1.0.0 .
# docker push aaanm13/helpdesk-user-service-api:1.0.0
# docker container run -d -p 0:0 --name helpdesk-user-service-api -e CONFIG_SERVER_URI=http://localhost:8761 aaanm13/helpdesk-user-service-api:1.0.0
# docker container ls
# docker container stop $(docker ps -a -q)
# docker container rm $(docker ps -a -q)