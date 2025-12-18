FROM amazoncorretto:17
LABEL authors="scarpinarthur.dev@gmail.com"
WORKDIR /app
COPY target/MagicFridgeAI-0.0.1-SNAPSHOT.jar /app/magicfridge.jar
COPY data /app/data
ENTRYPOINT ["java","-jar","magicfridge.jar"]