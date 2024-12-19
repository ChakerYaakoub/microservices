# Cabinet Médical - Architecture Microservices

## Description

Ce projet implémente une architecture microservices pour la gestion d'un cabinet médical. Il permet de gérer les patients, les praticiens, les rendez-vous et les dossiers médicaux.

## Architecture

Le projet est structuré en microservices interconnectés :

- **Service Eureka** (Port: 8761) : Service de découverte pour l'enregistrement et la localisation dynamique des services
- **Service Gateway** (Port: 8080) : API Gateway centralisée pour le routage et la sécurité
- **Service Patient** (Port: 8081) : Gestion complète des patients et de leurs informations
- **Service Praticien** (Port: 8082) : Administration des praticiens et de leurs spécialités
- **Service RDV** (Port: 8083) : Système avancé de gestion des rendez-vous
- **Service Dossier Médical** (Port: 8084) : Gestion sécurisée des dossiers médicaux

## Prérequis

Java 17
Docker et Docker Compose
Maven

## Installation et Démarrage

### Avec Docker Compose (Recommandé)

1. Cloner le repository :
   git clone [url-du-repo]

2. Construire et démarrer les services :

```bash
    docker-compose up --build
```

### Installation Manuelle

#### 1. Service Discovery (Eureka)

```bash

    cd service-eureka
    mvn spring-boot:run
```

#### 2. API Gateway

```bash
    cd ../service-gateway
    mvn spring-boot:run
```

#### 3. Services métier (dans des terminaux séparés)

```bash
    cd ../service-patient
    mvn spring-boot:run
```

```bash
    cd ../service-praticien
    mvn spring-boot:run
```

```bash
    cd ../service-rdv
    mvn spring-boot:run
```

```bash
    cd ../service-medical-record
    mvn spring-boot:run
```

## Documentation API

Les documentations Swagger sont disponibles aux URLs suivants :

- Gateway : http://localhost:8080/swagger-ui.html
- Service Patient : http://localhost:8081/swagger-ui.html
- Service Praticien : http://localhost:8082/swagger-ui.html
- Service RDV : http://localhost:8083/swagger-ui.html
- Service Dossier Médical : http://localhost:8084/swagger-ui.html

## Fonctionnalités Principales

### Service Patient

- CRUD des patients
- Gestion des informations personnelles
- Validation des données

### Service Praticien

- CRUD des praticiens
- Gestion des spécialités
- Validation des données

### Service RDV

- Prise de rendez-vous
- Gestion du calendrier
- Intégration avec Google Calendar
- Circuit breaker pour la résilience

### Service Dossier Médical

- Gestion des dossiers médicaux
- Historique des consultations
- Sécurisation des données

## Sécurité et Résilience

- Circuit Breaker avec Resilience4j
- Retry pattern implémenté
- Fallback mechanisms

## Base de Données

Chaque service utilise une base de données H2 en mémoire pour le développement.

- Console H2 disponible sur `/h2-console` pour chaque service
- Credentials par défaut :
  - JDBC URL : voir application.properties
  - Username: sa
  - Password: password

## Monitoring

- Eureka Dashboard : http://localhost:8761
- Actuator endpoints disponibles pour chaque service

## Environnement de Développement

Les services peuvent être configurés via leurs fichiers `application.properties` respectifs.
Variables d'environnement importantes :

- `EUREKA_CLIENT_SERVICEURL_DEFAULTZONE`
- Ports des services
- Configuration des bases de données

## Contribution

1. Fork le projet
2. Créer une branche feature
3. Commit les changements
4. Push vers la branche
5. Créer une Pull Request

## Support

Pour toute question ou problème :

- Email : chakeryb.work@hotmail.com
- Issues GitHub

## License

Ce projet est sous licence MIT.
