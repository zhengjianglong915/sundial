FROM openjdk:8-jdk-alpine
MAINTAINER zjl_915@126.com
RUN addgroup -S admin && adduser -S admin -G admin
USER admin:admin

ARG JAR_FILE=../sundial-scheduler/sundial-scheduler-bootstrap/target/*.jar
COPY docker/start.sh  /home/admin/ccbin/
COPY ${JAR_FILE} /home/admin/sundial-scheduler.jar

ENTRYPOINT ["sh", "/home/admin/ccbin/start.sh"]