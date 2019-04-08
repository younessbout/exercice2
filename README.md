
# Présentation  
  
L'exercice consiste en la création d'un ensemble d'api permettant de gérer les commentaires d'un poste.  
  
# Implémentation  
## Suppositions  
  
 - Existence d'une liaison des PostComment avec une entité Post et Author . Ces liaisons sont matérialisées respectivement par les champs "**post_id**" et "**author**" dans la table "**Post_Comment**"
 - Les **PostComment** sont CRUD intensifs, ce qui justifie le choix d'une BDD relationelle.
 -   

## Architecture 
L'architecture choisie est basée sur le modèle d'architectures **Hexagonales**. Ce qui favorise un faible couplage entre les couches. Favorisé pour les implémentations DDD.

 1. Le module **domain** est le noyau de la solution (ce module n'est visible que par les modules **application** et **infra**)
 2. Le module **application** porte les traitements métiers et gère les transactions (éventuellement les le cache )
 3. Le module **exposition** est la façade de communication avec l'extérieur
 4. Le module **infra** est un module fournissant les éléments d'infrastructure (implémentation BDD, Cache, ...) indépendamment du métier

## Implémentation
L'implémentation a été faite en suivant le mode de développement TDD, mais en utilisant que les TI (tests d'intégration).
#### Technologies utilisées 

 - Spring Boot 2
 - Spring Data JPA
 - Spring MVC
 - Mockito
 - Flyway
 - la base de données H2

 # Exécution
 Pour lancer l'application, se positionner sur la racine du projet et lancer la commande suivante:

    ./mvnw install && ./mvnw spring-boot:run -pl exposition 

Cette commande va lancer le build et les tests d'intégration et en même temps démarre l'application.

## Utilisation de Postman
Vous trouverez une fichier avec le nom "" de collection des appels API à importer dans Postman.