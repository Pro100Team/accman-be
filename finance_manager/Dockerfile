FROM maven:3.8.4-openjdk-17-slim AS build
EXPOSE 8080
ARG JAR_FILE=target/finance_manager-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java","-Dserver.port=$PORT", "$JAVA_OPTS", "-Xmx250m", "-XX:MaxRAM=450m","-jar","/app.jar"]


