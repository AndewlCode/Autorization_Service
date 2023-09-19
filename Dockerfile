FROM amazoncorretto:17

COPY target/Authorization_Service-0.0.1-SNAPSHOT.jar authorization.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "authorization.jar"]