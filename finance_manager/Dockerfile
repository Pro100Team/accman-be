FROM maven:3.8.4-openjdk-17-slim AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package

FROM openjdk:17
COPY --from=build /home/app/target/finance_manager-0.0.1-SNAPSHOT.jar /usr/local/lib/exadel.jar
CMD java -Dserver.port=$PORT $JAVA_OPTS -Xmx250m -XX:MaxRAM=450m -jar /usr/local/lib/exadel.jar
