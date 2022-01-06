#
# Build stage
#
FROM maven:3.6.0-jdk-11-slim AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package -DskipTests

#
# Package stage
#
FROM openjdk:11-jre-slim as finalApp
COPY --from=build /home/app/target/coin-calculator.jar /usr/local/lib/coin-calculator.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/usr/local/lib/coin-calculator.jar"]
