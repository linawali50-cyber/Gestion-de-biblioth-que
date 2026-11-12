package com.miniprojet.bibliotheque.services;

import com.miniprojet.bibliotheque.Entities.Adherent;
import com.miniprojet.bibliotheque.Entities.Role;
import com.miniprojet.bibliotheque.Entities.Utilisateur;

import java.util.List;

public interface IUtilisateurServ {
    Utilisateur sInscrire(Utilisateur u);
    Utilisateur login(String email, String password);
    Utilisateur findById(int id);
    List<Utilisateur> findAll();
    Utilisateur save(Utilisateur u);
    Utilisateur update(int id, Utilisateur u);
    void supprimer(int id);
    List<Utilisateur> findByRole(Role role);
    Utilisateur validerCompte(int id);
    Utilisateur changerRole(int id, Role role);
    Utilisateur modifierProfil(int id, Utilisateur u);

}
