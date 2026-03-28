# Build stage
#FROM maven:3.9.6-eclipse-temurin-17 AS build
#WORKDIR /app
#COPY pom.xml .
#COPY src ./src
#RUN mvn clean package -DskipTests

# Run stage
#FROM eclipse-temurin:17-jre-jammy
#WORKDIR /app
#COPY --from=build /app/target/portfolio-0.0.1-SNAPSHOT.jar portfolio.jar
#ENV PORT=8080
#EXPOSE 8080
#ENTRYPOINT ["java", "-Xmx512m", "-Dserver.port=${PORT}", "-jar", "portfolio.jar"]
# Build stage
# ... (Build stage remains the same) ...


FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /app

# Copy your pom.xml and source code to build the project
COPY pom.xml .
COPY src ./src

# Run the maven build to create the target/portfolio-0.0.1-SNAPSHOT.jar
RUN mvn clean package -DskipTests
FROM eclipse-temurin:17-jre-jammy
WORKDIR /app
COPY --from=build /app/target/portfolio-0.0.1-SNAPSHOT.jar portfolio.jar

# Update to 8081
ENV PORT=8081
EXPOSE 8081

ENTRYPOINT ["java", "-Xmx512m", "-Dserver.port=8081", "-jar", "portfolio.jar"]