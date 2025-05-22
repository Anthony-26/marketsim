# MarketSim API

API d’investissement fictive permettant de simuler la gestion d’un portefeuille d’actifs financiers (actions, cryptos, etc.).

## 🚀 Stack technique

- Java 17+
- Spring Boot 3.4.5 (REST API, JPA, Validation)
- PostgreSQL (dev/prod), H2 (test)
- Flyway (migrations BDD)
- Lombok (réduction du boilerplate)
- Maven (build, gestion des dépendances)
- Jacoco (couverture des tests)
- Mockito (tests unitaires)

## 📁 Structure initiale

- `domain/` : logique métier, entités, services business
- `application/` : configuration technique, ports, sécurité
- `infrastructure/` : persistance, web, adapters, migration

## ⚙️ Profils Spring

- test : H2 embarqué, couverture Jacoco
- dev : PostgreSQL, logs complets
- prod : PostgreSQL, sécurité renforcée

## 🧪 Tests & Qualité

- mvn test : lance tous les tests unitaires/integration
- Rapport Jacoco : target/site/jacoco/index.html

## 📦 Plugins Maven utilisés

- jacoco-maven-plugin : couverture des tests
- maven-compiler-plugin : gestion version Java
- spring-boot-maven-plugin : build/exécution Spring Boot
- flyway-maven-plugin : gestion migrations BDD