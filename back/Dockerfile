# Build stage
FROM maven:3.8.3-openjdk-17 as build

WORKDIR /app

COPY . .

RUN mvn install -DskipTests=true

# Run stage
# Copy the built JAR file
FROM openjdk:17-jdk-slim

WORKDIR /run

COPY --from=build /app/target/back-0.0.1-SNAPSHOT.jar /run/back-0.0.1-SNAPSHOT.jar

# Expose the port the app runs on
EXPOSE 8080

# Use environment variables for database configuration
ENV SPRING_DATASOURCE_URL="jdbc:mysql://192.168.1.107:3306/backend_db"
ENV SPRING_DATASOURCE_USERNAME="root"
ENV SPRING_DATASOURCE_PASSWORD="123456"
ENV SPRING_DATASOURCE_DRIVER_CLASS_NAME="com.mysql.cj.jdbc.Driver"
ENV SPRING_JPA_HIBERNATE_DDL_AUTO="update"
ENV SPRING_JPA_SHOW_SQL="true"
ENV SPRING_JPA_DATABASE_PLATFORM="org.hibernate.dialect.MySQL5Dialect"

# Run the JAR file with environment variables
ENTRYPOINT ["java", "-jar", "/run/back-0.0.1-SNAPSHOT.jar"]
