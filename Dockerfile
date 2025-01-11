# Use OpenJDK 17 with Buster image for package management
FROM openjdk:17-buster

# Install wget and Maven by downloading from the official URL
RUN apt-get update && apt-get install -y wget && \
    wget https://dlcdn.apache.org/maven/maven-3/3.9.6/binaries/apache-maven-3.9.6-bin.tar.gz && \
    tar -xvzf apache-maven-3.9.6-bin.tar.gz && \
    mv apache-maven-3.9.6 /opt/maven && \
    ln -s /opt/maven/bin/mvn /usr/local/bin/mvn

# Set working directory inside the container
WORKDIR /app

# Copy the pom.xml and install dependencies first
COPY pom.xml .

# Download dependencies (this will help cache dependencies for faster builds)
RUN mvn install -DskipTests

# Copy the rest of the application files
COPY src /app/src

# Expose the port the app will run on
EXPOSE 8080

# Run the application
CMD ["mvn", "spring-boot:run"]