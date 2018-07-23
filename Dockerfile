FROM hypriot/rpi-java
# Add Maintainer Info
LABEL maintainer="herskovi77@gmail.com"

# Add a volume pointing to /tmp
VOLUME /tmp

# Make port 8080 available to the world outside this container
EXPOSE 8080

# Add the application's jar to the container
ADD target/iotDemo4Amat-0.0.1-SNAPSHOT.jar hackathon-demo.jar

RUN chmod 777 hackathon-demo.jar

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","hackathon-demo.jar"]


# Run the jar file
