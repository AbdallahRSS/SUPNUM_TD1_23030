# SUPNUM TD1 – Partie B : SOAP → REST → Consommateur

## 1. Objectif

Cette partie B vise à définir et implémenter les mécanismes de communication entre trois services :

- **SOAP-service** : service historique de supervision basé sur SOAP (Partie A – Exercice 2).
- **middle-service (REST)** : service intermédiaire qui consomme le SOAP-service et expose des API REST.
- **consommateur (REST)** : service REST client qui consomme l’ensemble des endpoints exposés par le middle-service.

L’objectif métier : permettre aux clients du datacenter de superviser leurs serveurs (création, liste, statut, démarrage, arrêt, renommage, suppression) via des API REST, alors que le système historique est basé sur SOAP.

---

## 2. Architecture globale

Le dépôt est organisé comme suit :

- `SOAP-service/` : service SOAP + accès PostgreSQL
- `middle-service/` : service REST consommant SOAP-service
- `consommateur/` : service REST consommateur du middle-service
- `docker-compose.yml` : orchestration Docker des 3 services + DB

Architecture d’exécution :

```text
consommateur (REST, port 8084)
        ↓ (REST)
middle-service (REST, port 8083)
        ↓ (SOAP)
SOAP-service (SOAP, port 8082)
        ↓ (JPA)
PostgreSQL (supnum_td1)
```

---

## 3. Branch et regroupement des services

### 3.1. Branche Git

Une branche dédiée a été créée pour la partie B :

```bash
git checkout -b SOA_TO_REST
```

### 3.2. Regroupement des services

Les trois services sont regroupés dans un même répertoire racine :

- `SOAP-service/`
- `middle-service/`
- `consommateur/`
- `docker-compose.yml`

---

## 4. Déploiement avec Docker Compose

### 4.1. Fichiers utilisés

- `docker-compose.yml`
- `SOAP-service/Dockerfile`
- `middle-service/Dockerfile`
- `consommateur/Dockerfile`

### 4.2. Lancement

Depuis la racine du projet :

```bash
# (optionnel) construire les jars si les Dockerfile copient target/*.jar
cd SOAP-service && ./mvnw clean package -DskipTests && cd ..
cd middle-service && ./mvnw clean package -DskipTests && cd ..
cd consommateur && ./mvnw clean package -DskipTests && cd ..

cd SUPNUM_TD1_23030
docker-compose down
docker-compose up --build
```

Les services exposent :

- **PostgreSQL** : `localhost:5433` (port interne 5432)
- **SOAP-service** : `http://localhost:8082`
- **middle-service** : `http://localhost:8083`
- **consommateur** : `http://localhost:8084`

---

## 5. SOAP-service

### 5.1. Rôle

Service historique de supervision de serveurs, exposé via SOAP (Spring-WS) avec persistance dans PostgreSQL (BDD `supnum_td1`, table `servers`).

### 5.2. Accès SOAP

- **WSDL** :  
  `http://localhost:8082/ws/servers.wsdl`

Les opérations SOAP implémentent les cas d’usage :

- Créer un serveur
- Lister les serveurs
- Récupérer le statut (running ou non)
- Démarrer / arrêter un serveur
- Renommer un serveur
- Supprimer un serveur (interdit si running)

---

## 6. middle-service (REST ↔ SOAP)

### 6.1. Rôle

- Consomme **tous les endpoints SOAP** du SOAP-service.
- Convertit les requêtes REST en appels SOAP.
- Expose des API REST pour le datacenter.

### 6.2. Connexion au SOAP-service

Dans `middle-service`, le client SOAP utilise le hostname Docker :

```java
template.setDefaultUri("http://soap-service:8082/ws");
```

En local (hors Docker), l’URL peut être : `http://localhost:8082/ws`.

---

## 7. consommateur (REST client)

### 7.1. Rôle

- Service REST qui **consomme tous les endpoints** exposés par le middle-service.
- Ne possède **pas** sa propre base de données :
  - Il agit comme un client REST du middle-service.
- Expose une API REST pour les clients finaux (ou une UI/Web/front futur).

### 7.2. Connexion au middle-service

Configuration dans `consommateur/src/main/resources/application.properties` :

```properties
spring.application.name=consommateur
server.port=8084

# par défaut (développement local)
middle.service.base-url=${MIDDLE_SERVICE_BASE_URL:http://localhost:8083}
```

En Docker, la variable d’environnement est définie dans `docker-compose.yml` :

```yaml
environment:
  MIDDLE_SERVICE_BASE_URL: http://middle-service:8083
```

---

## 8. Contrats de communication – middle-service

### 8.1. Ressource principale : `Server`

Format JSON :

```json
{
  "id": 1,
  "name": "Server-A",
  "ipAddress": "10.0.0.10",
  "running": false
}
```

### 8.2. Endpoints middle-service

Base URL : `http://localhost:8083/api/servers`

| Endpoint                    | Méthode | Description                        | Corps requête (JSON)                                        | Réponse (JSON)                             |
|-----------------------------|---------|------------------------------------|--------------------------------------------------------------|--------------------------------------------|
| `/api/servers`             | POST    | Créer un serveur                   | `{ "name": "...", "ipAddress": "...", "running": false }`   | `Server` créé                              |
| `/api/servers`             | GET     | Lister tous les serveurs           | –                                                            | `List<Server>`                             |
| `/api/servers/{id}/status` | GET     | Statut (running ou non)            | –                                                            | `{ "id": x, "running": true/false }`       |
| `/api/servers/{id}/start`  | POST/PUT| Démarrer un serveur                | –                                                            | `Server` mis à jour                        |
| `/api/servers/{id}/stop`   | POST/PUT| Arrêter un serveur                 | –                                                            | `Server` mis à jour                        |
| `/api/servers/{id}/name`   | PUT     | Renommer un serveur                | `{ "newName": "NouveauNom" }`                               | `Server` mis à jour                        |
| `/api/servers/{id}`        | DELETE  | Supprimer un serveur               | –                                                            | Confirmation (ex: bool) / vide selon impl. |

- **Format des requêtes/réponses :** JSON.
- **Paramètres d’entrée :**
  - `id` : paramètre de chemin (`PathVariable`, `Long`).
  - Corps JSON pour POST/PUT selon le cas.

---

## 9. Contrats de communication – consommateur

### 9.1. Ressource principale : `Server` (DTO)

Le consommateur réutilise la même structure :

```json
{
  "id": 1,
  "name": "Server-A",
  "ipAddress": "10.0.0.10",
  "running": false
}
```

Pour le statut, le consommateur retourne :

```json
{
  "running": true
}
```

### 9.2. Endpoints consommateur

Base URL : `http://localhost:8084/api/servers`

| Endpoint                    | Méthode | Description                        | Corps requête (JSON)                                         | Réponse (JSON)              |
|-----------------------------|---------|------------------------------------|---------------------------------------------------------------|-----------------------------|
| `/api/servers`             | POST    | Créer un serveur (via middle)      | `{ "name": "...", "ipAddress": "...", "running": false }`    | `Server` créé              |
| `/api/servers`             | GET     | Lister tous les serveurs           | –                                                             | `List<Server>`             |
| `/api/servers/{id}/rename` | PUT     | Renommer un serveur                | `{ "name": "NouveauNom" }`                                   | `Server` mis à jour        |
| `/api/servers/{id}/status` | GET     | Statut (running ou non)            | –                                                             | `{ "running": true/false }`|
| `/api/servers/{id}/start`  | PUT     | Démarrer un serveur                | –                                                             | `Server` mis à jour        |
| `/api/servers/{id}/stop`   | PUT     | Arrêter un serveur                 | –                                                             | `Server` mis à jour        |
| `/api/servers/{id}`        | DELETE  | Supprimer un serveur               | –                                                             | HTTP 204 No Content (vide) |

- Le consommateur **transforme légèrement** certains contrats :
  - Par ex. `rename` : reçoit `{ "name": "NewName" }` et envoie `{ "newName": "NewName" }` au middle-service.
- Toutes les réponses sont au format **JSON**.

---

## 10. Swagger / Documentation OpenAPI

### 10.1. middle-service

- Dépendance `springdoc-openapi-starter-webmvc-ui` activée.
- Accès Swagger UI (en local) :

```text
http://localhost:8083/swagger-ui/index.html
```

### 10.2. consommateur

- Même dépendance et config Swagger.
- Accès Swagger UI (en local) :

```text
http://localhost:8084/swagger-ui/index.html
```

Swagger liste automatiquement :

- Les endpoints
- Les méthodes HTTP
- Les modèles JSON de requêtes/réponses

---

## 11. Tests de bout en bout (Postman)

### 11.1. Exemple de scénario complet

1. **Créer un serveur via le consommateur**

   - `POST http://localhost:8084/api/servers`  
   - Body :

     ```json
     {
       "name": "Server-A",
       "ipAddress": "10.0.0.10",
       "running": false
     }
     ```

2. **Lister les serveurs via le consommateur**

   - `GET http://localhost:8084/api/servers`

3. **Vérifier que le middle-service voit les mêmes serveurs**

   - `GET http://localhost:8083/api/servers`

4. **Vérifier dans PostgreSQL (dans la VM Docker)**

   ```bash
   docker exec -it supnum_postgres_td1 psql -U postgres -d supnum_td1
   SELECT * FROM servers;
   ```

---

## 12. Conclusion

La partie B implémente :

- Un **middle-service REST** consommant le service historique **SOAP**.
- Un **service consommateur REST** consommant le **middle-service**.
- Des **contrats de communication clairs** :
  - Endpoints REST, méthodes HTTP, paramètres, formats JSON.
- Une **architecture complète** orchestrée par Docker Compose : SOAP-service, middle-service, consommateur, PostgreSQL.

L’ensemble des sources des trois services est regroupé dans le répertoire du projet et versionné sur la branche `SOA_TO_REST`, conformément aux exigences du TD.