FROM adoptopenjdk/openjdk8

WORKDIR /home

COPY target/eurika-server-0.0.1.jar /home/system.jar

EXPOSE 8761

CMD ["java","-jar","system.jar"]

