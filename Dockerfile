FROM openjdk:17-jdk
WORKDIR /home/ubuntu/
COPY sulasang-api/build/libs/*.jar buzzing-server.jar
COPY sulasang-crawler/build/libs/*.jar buzzing-server.jar
EXPOSE 8083
ENTRYPOINT ["java","-jar","-Dspring.profiles.active=prod","sulasang-api-0.0.1-SNAPSHOT.jar"]
ENTRYPOINT ["java","-jar","-Dspring.profiles.active=prod","sulasang-crawler-0.0.1-SNAPSHOT.jar"]