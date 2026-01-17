# ---------- Build stage ----------
FROM eclipse-temurin:21-jdk AS build
WORKDIR /app

# Copy Gradle wrapper and config
COPY gradlew gradlew.bat ./
COPY gradle ./gradle
RUN chmod +x gradlew

# Copy Gradle configuration to prefetch dependencies
COPY build.gradle settings.gradle ./

# Pre-download dependencies (cached layer)
RUN ./gradlew dependencies --no-daemon || true

# Copy source code
COPY src ./src

# Build the jar (skip tests to speed up)
RUN ./gradlew bootJar --no-daemon -x test --refresh-dependencies

# ---------- Runtime stage ----------
FROM eclipse-temurin:21-jre
WORKDIR /app

# Copy built jar from build stage
COPY --from=build /app/build/libs/*.jar app.jar

EXPOSE 8083

ENTRYPOINT ["java", "-jar", "app.jar"]