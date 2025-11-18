# API Gestion des Serveurs
## Exercice 1 : REST

Service simple pour gérer et surveiller un ensemble de serveurs dans un data center.
Le service permet de créer, lister, renommer, démarrer, arrêter et supprimer des serveurs.

## Structure du projet:

```
src/main/java/com.supnum.supnum_td
│
├── controller
├── service
├── repository
├── model
├── config
└── exception
```

## Technologies:

- Spring Boot 3

- Java 17

- Spring Web

- Spring Data JPA

- PostgreSQL

- Swagger OpenAPI



## Configuration:

Fichier `application.properties`:

```
 spring.application.name=supnum_td
 server.port=8082

 spring.datasource.url=jdbc:postgresql://localhost:5432/supnum_td1
 spring.datasource.username=postgres
 spring.datasource.password=your_password

 spring.jpa.hibernate.ddl-auto=update
 spring.jpa.show-sql=true
 spring.jpa.properties.hibernate.format_sql=true
```


## Modèle:

Table `servers`:

- id

- name

- ipAddress

- running


## Swagger:

Documentation automatique accessible via:
```
http://localhost:8082/swagger-ui/index.html
```

## Endpoints:

### 1. Créer un serveur

**POST**
`/api/servers`

Body:
```
{
  "name": "server1",
  "ipAddress": "192.168.1.10"
}
```
Réponse:

```
{
  "id": 1,
  "name": "server1",
  "ipAddress": "192.168.1.10",
  "running": false
}

```

### 2. Lister tous les serveurs

**GET**
`/api/servers`


Réponse:
```
[
  {
    "id": 1,
    "name": "server1",
    "ipAddress": "192.168.1.10",
    "running": false
  }
]

```


### 3. Renommer un serveur

**PUT**
`/api/servers/{id}/rename`

Body:
```
{
  "name": "newName"
}

```

Réponse:
```
{
    "id": 1,
    "name": "newName",
    "ipAddress": "192.168.1.10",
    "running": false
}
```


### 4. Récupérer le statut d’un serveur

**GET**
`/api/servers/{id}/status`


Réponse:
```
{
  "running": false
}

```



### 5. Démarrer un serveur

**PUT**
`/api/servers/{id}/start`


Réponse:
```
{
  "id": 1,
  "name": "newName",
  "ipAddress": "192.168.1.10",
  "running": true
}

```



### 6. Arrêter un serveur

**PUT**
`/api/servers/{id}/stop`



### 7. Supprimer un serveur

**DELETE**
`/api/servers/{id}`

### Condition:

- Suppression interdite si le serveur est en cours d’exécution

- Renvoie une erreur dans ce cas



