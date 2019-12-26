FROM openjdk:11.0.5-jre
COPY /build/libs/zoo-service-1.0.0-SNAPSHOT-all.jar /zoo-service-1.0.0.jar
CMD ["/usr/local/openjdk-11/bin/java", "-jar", "/zoo-service-1.0.0.jar"]
EXPOSE 8000
