# Use an official OpenJDK image to run the application
FROM openjdk:17-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the pom.xml and other necessary files for dependency installation
COPY pom.xml /app/

# Download dependencies (this will help cache dependencies for faster builds)
RUN mvn install -DskipTests

# Copy the rest of the application files
COPY . /app/

# Build the Spring Boot application
RUN mvn clean package -DskipTests

# Expose the application port (usually 8080 for Spring Boot)
EXPOSE 8080

# Command to run the Spring Boot application
CMD ["java", "-jar", "target/capx-stock-portfolio-backend-0.0.1-SNAPSHOT.jar"]
