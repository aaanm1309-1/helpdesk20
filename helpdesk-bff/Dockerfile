FROM openjdk:21-jdk

ARG JAR_FILE=./build/libs/*.jar

EXPOSE 8081

COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java", "-jar", "/app.jar"]

# docker build -t aaanm13/helpdesk-helpdesk-bff:1.0.0 .
# docker push aaanm13/helpdesk-helpdesk-bff:1.0.0
# docker container run -d -p 8888:8888 --name helpdesk-helpdesk-bff -e CONFIG_SERVER_URI=http://github.com/aaanm1309-1/helpdesk20-config -e CONFIG_SERVER_USERNAME=aaanm1309-1 -e CONFIG_SERVER_PASSWORD=  aaanm13/helpdesk-config-server:1.0.0
# docker container ls
# docker container stop $(docker ps -a -q)
# docker container rm $(docker ps -a -q)
