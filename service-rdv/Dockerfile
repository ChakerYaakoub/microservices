# Image de base avec Java 17
FROM eclipse-temurin:17-jdk-alpine

# Informations sur le mainteneur
LABEL maintainer="cabinet-medical@example.com"

# Création du répertoire de l'application
WORKDIR /app

# Copie du fichier JAR de l'application
COPY target/*.jar app.jar

# Exposition du port utilisé par le service
EXPOSE 8083

# Commande pour démarrer l'application
ENTRYPOINT ["java", "-jar", "app.jar"]