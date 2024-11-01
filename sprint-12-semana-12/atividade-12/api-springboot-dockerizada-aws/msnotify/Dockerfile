FROM openjdk:21
WORKDIR /msnotify
COPY ./target/msnotify-0.0.1-SNAPSHOT.jar msnotify.jar
EXPOSE 8082
ENTRYPOINT java -jar msnotify.jar