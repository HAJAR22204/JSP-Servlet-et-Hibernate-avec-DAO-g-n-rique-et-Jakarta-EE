package com.example.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.util.Date;

@Entity
@Table(name = "authors")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Le nom complet est obligatoire")
    @Size(min = 2, max = 100, message = "Le nom doit contenir entre 2 et 100 caractères")
    @Column(name = "full_name", nullable = false, length = 100)
    private String fullName;

    @NotBlank(message = "La nationalité est obligatoire")
    @Column(nullable = false, length = 60)
    private String nationality;

    @Min(value = 1900, message = "L'année doit être supérieure à 1900")
    @Max(value = 2024, message = "L'année doit être inférieure à 2024")
    @Column(name = "birth_year")
    private Integer birthYear;

    @NotBlank(message = "L'email est obligatoire")
    @Email(message = "Format d'email invalide")
    @Column(unique = true, nullable = false, length = 100)
    private String email;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "registered_at")
    private Date registeredAt;

    public Author() { this.registeredAt = new Date(); }

    public Author(String fullName, String nationality, Integer birthYear, String email) {
        this.fullName = fullName;
        this.nationality = nationality;
        this.birthYear = birthYear;
        this.email = email;
        this.registeredAt = new Date();
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    public String getNationality() { return nationality; }
    public void setNationality(String nationality) { this.nationality = nationality; }
    public Integer getBirthYear() { return birthYear; }
    public void setBirthYear(Integer birthYear) { this.birthYear = birthYear; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public Date getRegisteredAt() { return registeredAt; }
    public void setRegisteredAt(Date registeredAt) { this.registeredAt = registeredAt; }
}