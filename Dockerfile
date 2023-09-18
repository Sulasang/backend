FROM openjdk:17-jdk
WORKDIR /home/ubuntu/
COPY sulasang-api/build/libs/*.jar sulasang-api.jar
COPY sulasang-crawler/build/libs/*.jar sulasang-crawler.jar
EXPOSE 8083
ENTRYPOINT ["java","-jar","-Dspring.profiles.active=prod","sulasang-api.jar", "--jasypt.encryptor.password=${ jasypt_encryptor_password }"]