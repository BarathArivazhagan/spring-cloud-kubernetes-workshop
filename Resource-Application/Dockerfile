FROM java:8
MAINTAINER BARATH
VOLUME /tmp
EXPOSE 8082
COPY target/Resource-Application-0.0.1-SNAPSHOT.war    resource.war
ENTRYPOINT ["java","-jar","resource.war"]
