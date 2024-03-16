FROM openjdk:8
EXPOSE 8080
ADD target/springtest-devops.jar springtest-devops.jar
ENTRYPOINT ["java","-jar","/springtest-devops.jar"]