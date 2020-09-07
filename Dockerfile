# latest Java 11 OpenJDK official image
FROM openjdk:11.0.8-jdk-slim

# spring needs /tmp to exist
VOLUME /tmp

# change the working directory - will be created if needed
WORKDIR /usr/app

# deploy the app artifacts
COPY ./bike-clinic-data/target/bike-clinic-data-0.0.3-SNAPSHOT.jar ./bike-clinic-data.jar
COPY ./bike-clinic-web/target/bike-clinic-web-0.0.3-SNAPSHOT.jar ./bike-clinic-web.jar

# touch the JAR files to make sure any changes like static resources are picked up
RUN sh -c 'touch ./bike-clinic-data.jar'
RUN sh -c 'touch ./bike-clinic-web.jar'

# default command to start the app.
# Note on urandom command arg: https://stackoverflow.com/a/59097932/798642
CMD ["java","-Djava.security.egd=file:/dev/./urandom","-jar","./bike-clinic-web.jar"]

