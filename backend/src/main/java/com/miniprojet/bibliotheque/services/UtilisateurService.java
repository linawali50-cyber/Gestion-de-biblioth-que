package com.miniprojet.bibliotheque.services;

import com.miniprojet.bibliotheque.Entities.Adherent;
import com.miniprojet.bibliotheque.Entities.Role;
import com.miniprojet.bibliotheque.Entities.Utilisateur;
import com.miniprojet.bibliotheque.repositories.UtilisateurRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UtilisateurService implements IUtilisateurServ {

    @Autowired
    private UtilisateurRepo utilisateurRepo;

    @Override
    public Utilisateur sInscrire(Utilisateur u) {

        if (u.getNom() == null || u.getEmail() == null || u.getPassword() == null) {
            throw new RuntimeException("Champs obligatoires manquants");
        }

        if (u.getPassword().length() < 6) {
            throw new RuntimeException("Mot de passe trop court (minimum 6 caractères)");
        }

        if (utilisateurRepo.findByEmail(u.getEmail()).isPresent()) {
            throw new RuntimeException("Email déjà utilisé");
        }

        return utilisateurRepo.save(u);
    }

    @Override
    public Utilisateur login(String email, String password) {
        List<Utilisateur> all = utilisateurRepo.findAll();
        for (Utilisateur u : all) {
            System.out.println("[" + u.getEmail() + "]");
            System.out.println("[" + u.getPassword() + "]");
        }
        Utilisateur user = utilisateurRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Utilisateur introuvable"));

        if (!user.getPassword().equals(password)) {
            throw new RuntimeException("Mot de passe incorrect");
        }

        return user;
    }

    @Override
    public Utilisateur findById(int id) {
        return utilisateurRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Utilisateur introuvable avec l'id : " + id));
    }

    @Override
    public List<Utilisateur> findAll() {
        return utilisateurRepo.findAll();
    }

    @Override
    public Utilisateur save(Utilisateur u) {
        return utilisateurRepo.save(u);
    }

    @Override
    public Utilisateur update(int id, Utilisateur u) {
        Utilisateur existing = findById(id);
        if (u.getNom() != null && !u.getNom().isBlank()) {
            existing.setNom(u.getNom());
        }
        if (u.getEmail() != null && !u.getEmail().isBlank()) {
            existing.setEmail(u.getEmail());
        }
        if (u.getPassword() != null && u.getPassword().length() >= 6) {
            existing.setPassword(u.getPassword());
        }
        return utilisateurRepo.save(existing);
    }

    @Override
    public void supprimer(int id) {
        findById(id);
        utilisateurRepo.deleteById(id);
    }

    @Override
    public List<Utilisateur> findByRole(Role role) {
        return utilisateurRepo.findByRole(role);
    }

    @Override
    public Utilisateur validerCompte(int id) {
        Utilisateur u = findById(id);
        u.setActive(true);
        return utilisateurRepo.save(u);
    }

    @Override
    public Utilisateur changerRole(int id, Role role) {
        Utilisateur u = findById(id);
        u.setRole(role);
        return utilisateurRepo.save(u);
    }

    public Utilisateur modifierProfil(int id, Utilisateur u) {
        Utilisateur existing = utilisateurRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Utilisateur introuvable"));
        if (u.getNom() != null) {
            existing.setNom(u.getNom());
        }
        if (u.getEmail() != null) {
            existing.setEmail(u.getEmail());
        }
        if (u.getPassword() != null && u.getPassword().length() >= 6) {
            existing.setPassword(u.getPassword());
        }
        return utilisateurRepo.save(existing);
    }
}
