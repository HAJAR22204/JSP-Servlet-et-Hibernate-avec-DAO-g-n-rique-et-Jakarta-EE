package com.example.model;

import jakarta.persistence.*;
import java.util.Date;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Le titre est obligatoire")
    @Size(max = 200, message = "Le titre ne peut pas dépasser 200 caractères")
    @Column(nullable = false, length = 200)
    private String title;

    @NotBlank(message = "L'ISBN est obligatoire")
    @Pattern(regexp = "^[0-9-]{10,17}$", message = "Format ISBN invalide (ex: 978-2-07-036024-5)")
    @Column(unique = true, nullable = false, length = 20)
    private String isbn;

    @NotBlank(message = "Le genre est obligatoire")
    @Column(nullable = false, length = 80)
    private String genre;

    @Min(value = 1000, message = "Année de publication invalide")
    @Max(value = 2024, message = "Année de publication invalide")
    @Column(name = "publication_year")
    private Integer publicationYear;

    @Min(value = 0, message = "Le nombre d'exemplaires ne peut pas être négatif")
    @Column(name = "available_copies", nullable = false)
    private Integer availableCopies;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "added_at")
    private Date addedAt;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    private Category category;

    public Book() {
        this.addedAt = new Date();
    }

    public Book(String title, String isbn, String genre, Integer publicationYear, Integer availableCopies) {
        this.title = title;
        this.isbn = isbn;
        this.genre = genre;
        this.publicationYear = publicationYear;
        this.availableCopies = availableCopies;
        this.addedAt = new Date();
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn) { this.isbn = isbn; }

    public String getGenre() { return genre; }
    public void setGenre(String genre) { this.genre = genre; }

    public Integer getPublicationYear() { return publicationYear; }
    public void setPublicationYear(Integer publicationYear) { this.publicationYear = publicationYear; }

    public Integer getAvailableCopies() { return availableCopies; }
    public void setAvailableCopies(Integer availableCopies) { this.availableCopies = availableCopies; }

    public Date getAddedAt() { return addedAt; }
    public void setAddedAt(Date addedAt) { this.addedAt = addedAt; }

    public Category getCategory() { return category;}
    public void setCategory(Category category) { this.category = category;}

    @Override
    public String toString() {
        return "Book [id=" + id + ", title=" + title + ", isbn=" + isbn + "]";
    }
}