FROM openjdk:alpine
EXPOSE 8080
ADD /target/java-app-demo-*.jar /opt/api.jar
ENTRYPOINT ["java","-jar","/opt/api.jar"]
