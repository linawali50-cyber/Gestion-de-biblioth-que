package com.miniprojet.bibliotheque.Entities;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.time.LocalDate;


@Entity
public class Emprunt {

    @Id
    @GeneratedValue
    private int id;

    private LocalDate dateEmprunt;
    private LocalDate dateRetour;
    private boolean retourne;

    @ManyToOne
    @JoinColumn(name = "idAdh")
    @JsonIgnoreProperties("emprunts")
    private Utilisateur utilisateur;

    @ManyToOne
    @JoinColumn(name = "idLivre")
    @JsonIgnoreProperties("emprunts")
    private Livre livre;

    public Emprunt() {}
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDateEmprunt() {
        return dateEmprunt;
    }

    public void setDateEmprunt(LocalDate dateEmprunt) {
        this.dateEmprunt = dateEmprunt;
    }

    public LocalDate getDateRetour() {
        return dateRetour;
    }

    public void setDateRetour(LocalDate dateRetour) {
        this.dateRetour = dateRetour;
    }

    public boolean isRetourne() {
        return retourne;
    }

    public void setRetourne(boolean retourne) {
        this.retourne = retourne;
    }

    public Livre getLivre() {
        return livre;
    }

    public void setLivre(Livre livre) {
        this.livre = livre;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

}
