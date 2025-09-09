FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app

COPY . .

RUN find . -name "*.java" > sources.txt && \
    javac -d . @sources.txt

CMD ["java", "-cp", ".", "br.com.dio.Main"]