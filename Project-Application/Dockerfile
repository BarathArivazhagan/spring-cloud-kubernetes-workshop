FROM java:8
MAINTAINER BARATH
VOLUME /tmp
EXPOSE 8081
COPY target/Project-Application-0.0.1-SNAPSHOT.war   project.war
ENTRYPOINT ["java","-jar","project.war"]
