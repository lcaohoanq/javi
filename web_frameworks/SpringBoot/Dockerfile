# Build stage
FROM maven:3.9.9-eclipse-temurin-17-alpine AS build
WORKDIR /app

# Copy only the pom.xml and download dependencies (caching this layer)
COPY ../pom.xml ../src ./
RUN mvn dependency:go-offline

# Now copy the rest of the source code and build
COPY ../ ./
RUN mvn clean package -DskipTests

# Runtime stage
FROM openjdk:17-slim
WORKDIR /app

# Copy the jar from the build stage
COPY --from=build /app/target/springboot-0.0.1-SNAPSHOT.jar app.jar

# Add wait-for-it script to ensure database is ready
ADD https://raw.githubusercontent.com/vishnubob/wait-for-it/master/wait-for-it.sh /wait-for-it.sh
RUN chmod +x /wait-for-it.sh

EXPOSE 8080
# Wait for MySQL to be ready before starting the application
CMD ["/bin/sh", "-c", "/wait-for-it.sh db:3306 -t 60 -- java -jar app.jar"]