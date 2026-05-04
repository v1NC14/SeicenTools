# 🛒 SeicenTools – Java Web E-commerce

## 📌 Descrizione del Progetto

**SeicenTools** è un'applicazione web sviluppata come progetto per il corso di *Tecnologie Software per il Web* presso l’Università degli Studi di Salerno .

Il sistema implementa un **e-commerce dinamico e responsive** specializzato nella vendita di materiali da ferramenta, con particolare focus su prodotti per pittura e imbiancatura.

L’obiettivo principale è offrire un’esperienza utente **semplice, intuitiva ed efficiente**, con una chiara separazione dei ruoli e delle funzionalità.

---

## 🎯 Obiettivi

* Realizzazione di un sito web e-commerce dinamico
* Implementazione di architettura basata su **Java Servlet**
* Interfaccia responsive
* Separazione dei ruoli utente (guest, registrato, amministratore)

---

## 🧱 Tecnologie Utilizzate

### Backend

* Java Servlet
* Apache Tomcat
* JDBC (Java DataBase Connectivity)

### Frontend

* HTML5
* CSS3
* Bootstrap
* JavaScript

### Database

* MySQL

### Versionamento

* Git
* GitHub

---

## 🏗️ Architettura

Il sistema segue il pattern **MVC (Model-View-Controller)**:

* **Model** → gestione dati (Java Beans + DAO)
* **View** → JSP / HTML
* **Controller** → Servlet

---

## 🗄️ Database

Il database è progettato per supportare le principali funzionalità di un e-commerce.

### Tabelle principali:

* `users`
* `products`
* `orders`
* `order_items`

Lo schema completo è disponibile in:

```text
database/schema.sql
```

---

## 👥 Ruoli Utente

### 👤 Guest

* Visualizza catalogo prodotti
* Naviga tra categorie
* Visualizza dettagli prodotti
* Aggiunge prodotti al carrello

⚠️ Non può completare acquisti

---

### 🧑 Utente Registrato

* Gestisce carrello
* Effettua checkout
* Inserisce dati personali
* Visualizza storico ordini

---

### 🛠️ Amministratore

* Inserisce nuovi prodotti
* Modifica prodotti esistenti
* Rimuove articoli
* Aggiorna prezzi e descrizioni

---

## 🚀 Setup del Progetto

### 1. Clonare la repository

```bash
git clone https://github.com/tuo-username/java-ecommerce-servlet.git
cd java-ecommerce-servlet
```

---

### 2. Setup Database

1. Aprire MySQL Workbench
2. Eseguire il file:

```text
database/schema.sql
```

---

### 3. Configurazione Server

* Installare Apache Tomcat
* Configurare il progetto nell'IDE (IntelliJ o Eclipse)
* Deploy della web app su Tomcat

Accesso:

```text
http://localhost:8080/nome-app
```

---

## 📱 Responsive Design

Il sistema è progettato secondo principi **mobile-first**, garantendo:

* Compatibilità con smartphone, tablet e desktop
* Layout adattivo tramite Bootstrap
* Navigazione ottimizzata

---

## 🧠 Analisi e Posizionamento

Il progetto si ispira a piattaforme come:

* Amazon
* Leroy Merlin
* ManoMano 

Differenziandosi per:

* Specializzazione verticale
* Interfaccia semplificata
* Ottimizzazione UX

---

## 📂 Struttura del Progetto

```text
src/
 ├── model/
 ├── dao/
 ├── servlet/
 └── utils/

webapp/
 ├── css/
 ├── js/
 ├── images/
 └── WEB-INF/

database/
 └── schema.sql
```

---

## 📌 Note

* Il progetto è sviluppato a scopo didattico
* Le funzionalità di pagamento sono simulate
* Focus su architettura, struttura e usabilità

---

## 👨‍🎓 Autori

* Gerardo Del Regno 
* Vincenzo Mauro 

---

## 📅 Corso

Tecnologie Software per il Web
Università degli Studi di Salerno 

---
