#  Ma Bibliothèque — JSP · Servlet · Hibernate · Jakarta EE

Application web complète de **gestion de bibliothèque** développée dans le cadre du module Java EE, démontrant l'intégration de JSP, Servlet, Hibernate avec un **pattern DAO générique** et **Bean Validation** sous Jakarta EE.

---

##  Objectifs pédagogiques

- Maîtriser l'architecture **MVC** avec JSP et Servlets Jakarta EE
- Implémenter un **DAO générique** réutilisable avec les Generics Java
- Configurer et utiliser **Hibernate 6** (ORM) avec Jakarta EE
- Gérer **trois entités liées** (`Author`, `Book`, `Category`) avec des opérations CRUD complètes
- Mettre en place la **validation des données** avec Bean Validation (`@NotBlank`, `@Size`, `@Email`, `@Pattern`, `@Min`, `@Max`)
- Implémenter des **relations JPA** (`@ManyToOne`, `@OneToMany`)

---

##  Prérequis

| Outil | Version minimale |
|-------|-----------------|
| JDK | 11 ou supérieur |
| Apache Tomcat | 10.x *(compatible Jakarta EE)* |
| Maven | 3.x |
| MySQL | 8.x |
| IDE | Eclipse / IntelliJ IDEA / NetBeans |

---

##  Démarrage rapide

### 1. Créer le projet Maven

```bash
mvn archetype:generate \
  -DgroupId=com.example \
  -DartifactId=library-management \
  -DarchetypeArtifactId=maven-archetype-webapp \
  -DinteractiveMode=false

cd library-management
```

### 2. Configurer la base de données

Ouvrez `src/main/resources/hibernate.cfg.xml` et renseignez vos paramètres MySQL :

```xml
<property name="hibernate.connection.url">
  jdbc:mysql://localhost:3306/librarydb?useSSL=false&amp;serverTimezone=UTC
</property>
<property name="hibernate.connection.username">root</property>
<property name="hibernate.connection.password">votre_mot_de_passe</property>
```

>  La base de données `librarydb` et les tables sont générées automatiquement grâce à `hibernate.hbm2ddl.auto = update`.

### 3. Compiler et lancer

```bash
mvn clean package
mvn tomcat7:run
```

### 4. Accéder à l'application

```
http://localhost:8080/library-management/
```

---

##  Structure du projet

```
src/
└── main/
    ├── java/com/example/
    │   ├── controller/                  # Servlets (Jakarta EE)
    │   │   ├── AuthorListServlet.java
    │   │   ├── AuthorFormServlet.java
    │   │   ├── AuthorCreateServlet.java
    │   │   ├── AuthorUpdateServlet.java
    │   │   ├── AuthorDeleteServlet.java
    │   │   ├── BookListServlet.java
    │   │   ├── BookFormServlet.java
    │   │   ├── BookCreateServlet.java
    │   │   ├── BookUpdateServlet.java
    │   │   ├── BookDeleteServlet.java
    │   │   ├── CategoryListServlet.java
    │   │   ├── CategoryFormServlet.java
    │   │   ├── CategoryCreateServlet.java
    │   │   ├── CategoryUpdateServlet.java
    │   │   └── CategoryDeleteServlet.java
    │   ├── dao/                         # Pattern DAO générique
    │   │   ├── GenericDAO.java
    │   │   ├── GenericDAOImpl.java
    │   │   ├── AuthorDAO.java
    │   │   ├── BookDAO.java
    │   │   └── CategoryDAO.java
    │   ├── model/                       # Entités JPA
    │   │   ├── Author.java
    │   │   ├── Book.java
    │   │   └── Category.java
    │   ├── listener/
    │   │   └── AppStartupListener.java
    │   └── util/
    │       ├── HibernateUtil.java
    │       └── ValidationUtil.java
    ├── resources/
    │   └── hibernate.cfg.xml
    └── webapp/
        ├── WEB-INF/
        │   └── web.xml
        ├── index.jsp
        ├── header.jsp
        ├── footer.jsp
        ├── author-list.jsp
        ├── author-form.jsp
        ├── book-list.jsp
        ├── book-form.jsp
        ├── category-list.jsp
        ├── category-form.jsp
        └── error.jsp
```

---

##  Architecture MVC

```
Navigateur
    │
    ▼
[Servlet Controller]  ←─── Requête HTTP
    │
    ├──► [ValidationUtil]  ←─── Bean Validation (Jakarta)
    │
    ├──► [DAO Layer]  ←─── GenericDAOImpl<T, ID>
    │         │
    │         ▼
    │    [Hibernate ORM]
    │         │
    │         ▼
    │    [Base de données MySQL — librarydb]
    │
    └──► [JSP View]  ──► Réponse HTML
```

### Couches de l'application

**Modèle (Model)**
- `Author`, `Book`, `Category` — entités JPA annotées avec contraintes Bean Validation
- `GenericDAO<T, ID>` — interface CRUD générique
- `GenericDAOImpl<T, ID>` — implémentation abstraite commune
- `AuthorDAO`, `BookDAO`, `CategoryDAO` — extensions avec méthodes métier spécifiques

**Vue (View)**
- Pages JSP avec JSTL (`<c:forEach>`, `<c:if>`, `<c:choose>`, `<c:out>`)
- Bootstrap 4 pour le style et la mise en page responsive

**Contrôleur (Controller)**
- Servlets annotés `@WebServlet` traitant les requêtes HTTP
- `ValidationUtil` — utilitaire générique de validation basé sur `jakarta.validation`

---

##  Modèle de données

```
┌──────────────────┐         ┌──────────────────┐         ┌─────────────┐
│     Author       │         │      Book        │   N..1  │  Category   │
├──────────────────┤         ├──────────────────┤◄────────├─────────────┤
│ id               │         │ id               │         │ id          │
│ fullName         │         │ title            │         │ name        │
│ nationality      │         │ isbn (unique)    │         │ description │
│ birthYear        │         │ genre            │         └─────────────┘
│ email (unique)   │         │ publicationYear  │
│ registeredAt     │         │ availableCopies  │
└──────────────────┘         │ addedAt          │
                             │ category_id (FK) │
                             └──────────────────┘
```

<img width="415" height="235" alt="image" src="https://github.com/user-attachments/assets/0c794224-d125-47b8-a333-6e29c8261cd0" />

**Relations JPA :**
- `Book` → `Category` : `@ManyToOne` — un livre appartient à une catégorie
- `Category` → `Book` : `@OneToMany` — une catégorie regroupe plusieurs livres
- La catégorie est optionnelle (un livre peut être *Sans catégorie*)

---

##  Fonctionnalités

### Gestion des auteurs

<img width="415" height="235" alt="image" src="https://github.com/user-attachments/assets/ac23d5d2-0f7e-49d9-a498-10cfd2817456" />

- Lister tous les auteurs
- Ajouter un auteur (nom complet, nationalité, année de naissance, email)
- Modifier un auteur
- Supprimer un auteur avec confirmation
- Recherche par email (`findByEmail`)
- Filtrage par nationalité (`findByNationality`)

### Gestion des livres

<img width="416" height="237" alt="image" src="https://github.com/user-attachments/assets/7f23ba66-1c34-4043-a0ef-196032adf1b1" />

- Lister tous les livres avec leur catégorie affichée en badge
- Rechercher par titre, genre ou ISBN (filtre dynamique)
- Ajouter un livre avec sélection de catégorie dans un `<select>`
- Modifier un livre
- Supprimer un livre avec confirmation
- Recherche par ISBN (`findByIsbn`)
- Chargement optimisé via `left join fetch` pour éviter le problème N+1

### Gestion des catégories

<img width="415" height="236" alt="image" src="https://github.com/user-attachments/assets/86dbffcf-cf20-4a52-bade-1581945f185f" />

- Lister toutes les catégories
- Ajouter une catégorie (nom unique, description)
- Modifier une catégorie
- Supprimer une catégorie
- Recherche par nom (`findByName`)

---

## Validation des données (Bean Validation)

Les entités sont annotées avec des contraintes déclaratives Jakarta :

**Author**
```java
@NotBlank(message = "Le nom complet est obligatoire")
@Size(min = 2, max = 100)
private String fullName;

@Email(message = "Format d'email invalide")
private String email;

@Min(1900) @Max(2024)
private Integer birthYear;
```


**Book**
```java
@NotBlank(message = "Le titre est obligatoire")
@Size(max = 200)
private String title;

@Pattern(regexp = "^[0-9-]{10,17}$", message = "Format ISBN invalide (ex: 978-2-07-036024-5)")
private String isbn;

@Min(0) // pas de stock négatif
private Integer availableCopies;
```

La classe utilitaire `ValidationUtil` centralise la validation pour toutes les entités :

```java
// Retourne un map { nomDuChamp → messageErreur }
Map<String, String> errors = ValidationUtil.validate(entity);
```

---

##  Le DAO Générique — Concept clé

L'interface `GenericDAO<T, ID>` définit les opérations communes :

```java
void save(T entity);
void update(T entity);
Optional<T> findById(ID id);
List<T> findAll();
void delete(ID id);
void deleteEntity(T entity);
```

`GenericDAOImpl<T, ID>` les implémente une seule fois. Chaque DAO spécifique hérite et ajoute ses méthodes métier :

```java
// BookDAO — surcharge fetchAll() pour charger la catégorie en JOIN
public List<Book> fetchAll() {
    return session.createQuery(
        "select b from Book b left join fetch b.category", Book.class
    ).list();
}
public Optional<Book> findByIsbn(String isbn) { ... }
public List<Book> searchBooks(String keyword) { ... }

// AuthorDAO
public Optional<Author> findByEmail(String email) { ... }
public List<Author> findByNationality(String nationality) { ... }

// CategoryDAO
public Optional<Category> findByName(String name) { ... }
```

**Avantages :**
- Aucune duplication du code CRUD entre les 3 entités
- Maintenance centralisée
- Ajout d'une nouvelle entité = hériter de `GenericDAOImpl`, zéro répétition

---

##  Concepts abordés

- **Jakarta EE** — évolution de Java EE (`jakarta.*` remplace `javax.*`)
- **Hibernate 6 / JPA** — `@Entity`, `@ManyToOne`, `@OneToMany`, `@JoinColumn`, `FetchType.EAGER`
- **Bean Validation** — `@NotBlank`, `@Size`, `@Email`, `@Pattern`, `@Min`, `@Max`
- **Pattern DAO générique** — `<T, ID>` pour un code type-safe et réutilisable
- **Architecture MVC** — séparation stricte Modèle / Vue / Contrôleur
- **JSTL** — `<c:forEach>`, `<c:if>`, `<c:choose>`, `<c:out>`
- **Optimisation N+1** — `left join fetch` dans les requêtes HQL
- **Gestion des transactions** — `persist()`, `merge()`, `remove()` avec rollback automatique

---

