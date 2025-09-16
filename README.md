# MealPrepBack - Notes Techniques

## 1️⃣ Description

Backend Java Spring Boot pour le projet MealPrep, gestion des ingrédients et recettes.  
Stack :
- Java 21 LTS
- Spring Boot 3.2.x
- PostgreSQL
- Maven
- Lombok
- JPA / Hibernate

---

## 2️⃣ Commandes Maven utiles

- Lancer le projet :
```bash
mvn spring-boot:run
Vérifier les mises à jour des dépendances :

bash
Copier le code
mvn versions:display-dependency-updates
Scanner les vulnérabilités des dépendances :

bash
Copier le code
mvn org.owasp:dependency-check-maven:check
Générer les sources et mettre à jour les dossiers (IntelliJ Community) :

bash
Copier le code
mvn generate-sources
Compiler et tester :

bash
Copier le code
mvn clean install 
```

# BONUS : runtime
- mvn clean compile
- mvn spring-boot:run
- CTRL+F9 (Build Project)
- Tricks: nano ~/.bashrc (alias mvn-run='mvn clean compile && mvn spring-boot:run'
  )

## 3️⃣ PostgreSQL - Développement local
Créer la base et l’utilisateur
CREATE DATABASE mealprep;
CREATE USER mealprep_user WITH PASSWORD 'password';
GRANT ALL PRIVILEGES ON DATABASE mealprep TO mealprep_user;

Connexion dans IntelliJ / DBeaver
Host : localhost
Port : 5432
Database : mealprep
User : mealprep_user
Password : password

## 4️⃣ Configuration Spring Boot (application.properties)
spring.datasource.url=jdbc:postgresql://localhost:5432/mealprep
spring.datasource.username=mealprep_user
spring.datasource.password=password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

# Optionnel : logs SQL
logging.level.org.hibernate.SQL=DEBUG
logging.level.org.hibernate.type.descriptor.sql.BasicBinder=TRACE

## 5️⃣ Structure projet recommandée
MealPrepBack/
├─ src/main/java/com/example/mealprep
│   ├─ entity/       # Entités JPA
│   ├─ repository/   # Repositories Spring Data JPA
│   ├─ service/      # Services métiers
│   └─ controller/   # REST Controllers
├─ src/main/resources
│   └─ application.properties
├─ pom.xml
└─ README.md        # Notes techniques et commandes utiles

## 6️⃣  Notes pratiques

Spring Boot embarque Tomcat intégré → pas besoin d’installer un serveur web séparé.

Hibernate / JPA créent automatiquement les tables si ddl-auto=update.

Pour tester les endpoints REST, utiliser Bruno ou Postman.

Lombok simplifie les getters/setters, ajoute @Getter / @Setter si nécessaire.

## 7️⃣ Commandes utiles PostgreSQL (via terminal)

Se connecter à la base :

sudo -i -u postgres
psql -d mealprep


Lister les tables :

\dt


Quitter PostgreSQL :

\q






## WINDOWS

path to the project:
 cd /c/Workspace/JavaMealPrepBack/

