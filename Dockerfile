FROM openjdk:17-jdk
WORKDIR /home/ubuntu/
COPY sulasang-api/build/libs/*.jar sulasang-api.jar
COPY sulasang-crawler/build/libs/*.jar sulasang-crawler.jar
EXPOSE 8083
ENTRYPOINT ["java","-jar","-Dspring.profiles.active=prod","sulasang-api.jar", "--jasypt.encryptor.password=${secrets.JASYPT_ENCRYPTOR_PASSWORD}"]
ENTRYPOINT ["java","-jar","-Dspring.profiles.active=prod","sulasang-crawler.jar", "--jasypt.encryptor.password=${secrets.JASYPT_ENCRYPTOR_PASSWORD}"]

sudo java -jar -Dspring.profiles.active=prod sulasang-api-0.0.1-SNAPSHOT.jar  > /home/ubuntu/nohup.out 2>&1 &