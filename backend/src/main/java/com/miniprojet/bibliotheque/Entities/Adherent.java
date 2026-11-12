package com.miniprojet.bibliotheque.Entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
@DiscriminatorValue("ADHERENT")
public class Adherent extends Utilisateur{
    public Adherent() {
    }

    public Adherent(String nom, String email, String password, boolean isActive, Role role) {
        super(nom, email, password, isActive, role);
    }
}
