package com.miniprojet.bibliotheque.Entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "utilisateurs")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
public class Utilisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nom;
    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    private boolean isActive;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @OneToMany(mappedBy = "utilisateur")
    @JsonIgnore
    private List<Emprunt> emprunts;


    public Utilisateur() {}

    public Utilisateur(String nom, String email, String password, boolean isActive, Role role) {
        this.nom      = nom;
        this.email    = email;
        this.password = password;
        this.isActive = isActive;
        this.role     = role;
    }

    public int getId()                      { return id; }
    public void setId(int id)               { this.id = id; }

    public String getNom()                  { return nom; }
    public void setNom(String nom)          { this.nom = nom; }

    public String getEmail()                { return email; }
    public void setEmail(String email)      { this.email = email; }

    public String getPassword()             { return password; }
    public void setPassword(String password){ this.password = password; }

    public boolean isActive()               { return isActive; }
    public void setActive(boolean active)   { this.isActive = active; }

    public Role getRole()                   { return role; }
    public void setRole(Role role)          { this.role = role; }

    @Override
    public String toString() {
        return "Utilisateur{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", isActive=" + isActive +
                ", role=" + role +
                ", emprunts=" + emprunts +
                '}';
    }

    public List<Emprunt> getEmprunts()               { return emprunts; }
    public void setEmprunts(List<Emprunt> emprunts)  { this.emprunts = emprunts; }
}
