FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app

COPY . .

RUN find . -name "*.java" -exec javac {} \;

CMD ["java", "Main"]