# API Gestion des Serveurs (SOAP)
# Exercice 2 : SOAP

Service SOAP pour gérer et surveiller un ensemble de serveurs dans un data center.
Le service permet de créer, lister, renommer, démarrer, arrêter et supprimer des serveurs via des requêtes SOAP.

## Technologies:

- Spring Boot 3

- Java 17

- Spring Web Services

- Spring Data JPA

- PostgreSQL

- SOAP

- JAXB (Java Architecture for XML Binding)


## Configuration:

Fichier `application.properties` :

```
spring.application.name=supnum_td
server.port=8083

spring.datasource.url=jdbc:postgresql://localhost:5432/supnum_td1
spring.datasource.username=postgres
spring.datasource.password=47571064

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

## WSDL (Web Service Definition Language):

La définition WSDL de votre service SOAP est accessible à l'adresse :

```
http://localhost:8083/ws/servers.wsdl
```

## Endpoints: ** SOAP UI **
### 1. Créer un serveur

### SOAP Request :
```
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:ser="http://supnum.com/servers">
   <soapenv:Header/>
   <soapenv:Body>
      <ser:createServerRequest>
         <ser:name>server3</ser:name>
         <ser:ipAddress>192.168.1.3</ser:ipAddress>
      
         
      </ser:createServerRequest>
   </soapenv:Body>
</soapenv:Envelope>
```

### SOAP Response :

```
<SOAP-ENV:Envelope xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/">
   <SOAP-ENV:Header/>
   <SOAP-ENV:Body>
      <ns3:createServerResponse xmlns:ns3="http://supnum.com/servers">
         <ns3:server>
            <id>6</id>
            <ipAddress>192.168.1.3</ipAddress>
            <name>server3</name>
            <running>false</running>
         </ns3:server>
      </ns3:createServerResponse>
   </SOAP-ENV:Body>
</SOAP-ENV:Envelope>
```

### 2. Lister tous les serveurs

### SOAP Request :
```
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:ser="http://supnum.com/servers">
   <soapenv:Header/>
   <soapenv:Body>
      <ser:listServersRequest></ser:listServersRequest>
   </soapenv:Body>
</soapenv:Envelope>
```

### SOAP Response :

```
<SOAP-ENV:Envelope xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/">
   <SOAP-ENV:Header/>
   <SOAP-ENV:Body>
      <ns3:listServersResponse xmlns:ns3="http://supnum.com/servers">
         <ns3:servers>
            <id>2</id>
            <ipAddress>192.168.1.20</ipAddress>
            <name>server2</name>
            <running>true</running>
         </ns3:servers>
         <ns3:servers>
            <id>6</id>
            <ipAddress>192.168.1.3</ipAddress>
            <name>server3</name>
            <running>false</running>
         </ns3:servers>
      </ns3:listServersResponse>
   </SOAP-ENV:Body>
</SOAP-ENV:Envelope>
```


### 3. Renommer un serveur

### SOAP Request :
```
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:ser="http://supnum.com/servers">
   <soapenv:Header/>
   <soapenv:Body>
      <ser:renameServerRequest>
         <ser:id>2</ser:id>
         <ser:newName>Maste server</ser:newName>
      </ser:renameServerRequest>
   </soapenv:Body>
</soapenv:Envelope>
```

### SOAP Response :

```
<SOAP-ENV:Envelope xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/">
   <SOAP-ENV:Header/>
   <SOAP-ENV:Body>
      <ns3:renameServerResponse xmlns:ns3="http://supnum.com/servers">
         <ns3:server>
            <id>2</id>
            <ipAddress>192.168.1.20</ipAddress>
            <name>Maste server</name>
            <running>true</running>
         </ns3:server>
      </ns3:renameServerResponse>
   </SOAP-ENV:Body>
</SOAP-ENV:Envelope>
```


### 4. Démarrer un serveur

### SOAP Request :
```
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:ser="http://supnum.com/servers">
   <soapenv:Header/>
   <soapenv:Body>
      <ser:startServerRequest>
         <ser:id>6</ser:id>
      </ser:startServerRequest>
   </soapenv:Body>
</soapenv:Envelope>
```

### SOAP Response :

```
<SOAP-ENV:Envelope xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/">
   <SOAP-ENV:Header/>
   <SOAP-ENV:Body>
      <ns3:startServerResponse xmlns:ns3="http://supnum.com/servers">
         <server>
            <id>6</id>
            <ipAddress>192.168.1.3</ipAddress>
            <name>server3</name>
            <running>true</running>
         </server>
      </ns3:startServerResponse>
   </SOAP-ENV:Body>
</SOAP-ENV:Envelope>
```



### 5. Arrette un serveur

### SOAP Request :
```
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:ser="http://supnum.com/servers">
   <soapenv:Header/>
   <soapenv:Body>
      <ser:stopServerRequest>
         <ser:id>6</ser:id>
      </ser:stopServerRequest>
   </soapenv:Body>
</soapenv:Envelope>
```

### SOAP Response :

```
<SOAP-ENV:Envelope xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/">
   <SOAP-ENV:Header/>
   <SOAP-ENV:Body>
      <ns3:stopServerResponse xmlns:ns3="http://supnum.com/servers">
         <ns3:server>
            <id>6</id>
            <ipAddress>192.168.1.3</ipAddress>
            <name>server3</name>
            <running>false</running>
         </ns3:server>
      </ns3:stopServerResponse>
   </SOAP-ENV:Body>
</SOAP-ENV:Envelope>
```



### 6. Supprimer un serveur

### SOAP Request :
```
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:ser="http://supnum.com/servers">
   <soapenv:Header/>
   <soapenv:Body>
      <ser:deleteServerRequest>
         <ser:id>6</ser:id>
      </ser:deleteServerRequest>
   </soapenv:Body>
</soapenv:Envelope>
```

### SOAP Response :

```
<SOAP-ENV:Envelope xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/">
   <SOAP-ENV:Header/>
   <SOAP-ENV:Body>
      <ns2:deleteServerResponse xmlns:ns2="http://supnum.com/servers">
         <ns2:success>true</ns2:success>
      </ns2:deleteServerResponse>
   </SOAP-ENV:Body>
</SOAP-ENV:Envelope>
```
