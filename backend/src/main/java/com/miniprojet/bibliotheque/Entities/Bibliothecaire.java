package com.miniprojet.bibliotheque.Entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("BIBLIOTHECAIRE")
public class Bibliothecaire extends Utilisateur{
    public Bibliothecaire() {
    }
    public Bibliothecaire(String nom, String email, String password) {
        super( nom, email, password, true, Role.BIBLIOTHECAIRE);
    }
}
