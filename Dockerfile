FROM openjdk:8-jdk-alpine
VOLUME /tmp
COPY ./target/sddm_parent.jar sddm_parent.jar
ENTRYPOINT ["java","-jar","/sddm_parent.jar", "&"]