FROM openjdk:17-jdk
WORKDIR /home/ubuntu/
COPY sulasang-api/build/libs/*.jar sulasang-api.jar
COPY sulasang-crawler/build/libs/*.jar sulasang-crawler.jar
EXPOSE 8083

ARG JASYPT_ENCRYPTOR_PASSWORD

ENV JASYPT_ENCRYPTOR_PASSWORD=$JASYPT_ENCRYPTOR_PASSWORD

ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=prod", "sulasang-api.jar", "--jasypt.encryptor.password=${JASYPT_ENCRYPTOR_PASSWORD}"]