package com.miniprojet.bibliotheque.Entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("ADMIN")
public class Admin extends Utilisateur {
    public Admin() {
    }
    public Admin(String nom, String email, String password, boolean isActive, Role role) {
        super(nom, email, password, isActive, role);
    }
}
