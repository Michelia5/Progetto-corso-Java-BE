# 📛 Esame-Fine-Corso-Java

## 🎯 Obiettivo
Realizzare un'applicazione backend REST in Java con Spring Boot per la gestione di una scuola privata post-diploma. L'app consente di gestire studenti, docenti, corsi, iscrizioni, valutazioni, materiali didattici e autenticazione degli utenti con gestione dei ruoli.

## 🧰 Tecnologie
- Java 21
- Spring Boot
- Spring Data JPA
- Spring Security + JWT
- PostgreSQL
- Lombok
- JUnit
- Maven
- Swagger/OpenAPI
- Apache POI (per l’export in Excel)
- OpenPdf (per la generazione del PDF)

## 🏛️ Architettura
L'applicazione segue il pattern **MVC**:
- **Model**: rappresentazioni JPA delle entità di dominio.
- **Repository**: accesso ai dati tramite Spring Data JPA.
- **Service**: logica applicativa.
- **Controller**: gestione delle richieste REST.

Tutti i livelli sono ben separati. Sono presenti DTO, Mapper, validatori e gestione centralizzata delle eccezioni.

## 🔐 Sicurezza e autenticazione
- Autenticazione tramite JWT.
- Autorizzazione basata sui ruoli: `ROLE_ADMIN`, `ROLE_DOCENTE`, `ROLE_STUDENTE`.
- Endpoints protetti tramite `@PreAuthorize`.
- Registrazione tramite endpoint dedicato.
- Collegamento 1:1 tra `AuthUtente` e `Studente` / `Docente`.

## 🚀 Funzionalità principali
- CRUD completo su:
    - Studenti
    - Docenti
    - Corsi
    - Aule
    - Percorsi Formativi
    - Materiali Didattici
    - Iscrizioni
    - Esami
- Gestione delle iscrizioni con stato (`ATTIVA`, `COMPLETATA`, `RITIRATA`) e voto finale
- Relazioni complesse (@ManyToOne, @OneToMany, @OneToOne, @ManyToMany)

## 💡 Funzionalità aggiuntive
- Esportazione CSV:
    - Studenti
    - Iscrizioni
- Esportazione Excel:
    - Percorsi Formativi con relativi esami
- Generazione PDF:
    - Profilo completo dello studente
- Documentazione interattiva tramite Swagger
- Dataset iniziale realistico precaricato all'avvio
- Protezione con JWT e ruoli (docente/studente/admin)

## ✅ Testing
- Endpoint testabili da Postman
- Test manuali su login, registrazione, autorizzazione
- Swagger usato per il test degli endpoint
- JUnit incluso per test di unità selezionati
- Sicurezza testata con token JWT validi/non validi
- Collezione Postman `.json` inclusa nella cartella `/postman` per test degli endpoint.

## ▶️ Avvio del progetto
1. Clonare il repository
2. Configurare PostgreSQL (o qualsiasi altro DB) (Nome: `esame_java`)
3. Configurare application.properties come descritto di seguito in [Configurazione dell'applicazione](#-configurazione-dellapplicazione)
4. Avviare l’app Spring Boot (`main`)
5. Accedere a Swagger: [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)
6. Usare Postman o Swagger per testare le API
7. (Facoltativo) Eseguire i test unitari:
```
mvn test
```

## ⚙️ Configurazione dell'applicazione

Per eseguire l'applicazione è necessario **creare un file `application.properties`** all’interno di `src/main/resources`.

Nel progetto è incluso un file di esempio chiamato `application-example.properties`.  
Per utilizzarlo:

1. Copia `application-example.properties` come `application.properties`
2. Compila i valori richiesti (DB, credenziali, JWT):

```properties
# Database
spring.datasource.url=jdbc:postgresql://localhost:5432/esame_java
spring.datasource.username=yourUsername
spring.datasource.password=yourPassword

# JWT
jwt.secret=InserisciUnaChiaveJWTDiAlmeno32Caratteri
jwt.expirationMs=86400000
```

## 🧪 Dataset iniziale
Il caricamento dei dati avviene tramite `DataLoader` e `AuthDataLoader`:
- 5 Studenti + 3 Docenti
- 4 Corsi + 3 Percorsi Formativi
- 3 Aule
- 4 Esami
- 4 Materiali Didattici
- 6 Iscrizioni
- 1 Admin
- Utenti già associati a studenti/docenti

## 📐 Design Pattern utilizzati
- **Builder Pattern**: per creare oggetti entità con `@Builder` di Lombok
- **Repository Pattern**: con Spring Data JPA
- **Singleton**: i servizi sono singleton gestiti da Spring
- **DTO Pattern**: separazione completa tra entità e dati in transito
- **Exception Handling Centralizzato**: con `@ControllerAdvice` e custom exceptions
- **Security Pattern**: JWT-based stateless authentication

## 📝 Note finali
- Tutti gli endpoint sono RESTful e ben documentati.
- I dati vengono validati nei DTO con annotazioni (`@NotBlank`, `@Email`, ecc.).
- Ogni entità è testabile da Swagger o Postman.
- Utenti test disponibili:
- - Admin: `admin / admin123`
- - Studente: `luca / luca123`
- - Docente: `anna / anna123`


