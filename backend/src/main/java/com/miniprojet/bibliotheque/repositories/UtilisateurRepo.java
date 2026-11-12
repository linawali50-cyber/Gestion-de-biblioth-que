package com.miniprojet.bibliotheque.repositories;

import com.miniprojet.bibliotheque.Entities.Adherent;
import com.miniprojet.bibliotheque.Entities.Role;
import com.miniprojet.bibliotheque.Entities.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UtilisateurRepo extends JpaRepository<Utilisateur,Integer> {
    Optional<Utilisateur> findByEmailAndPassword(String email, String password);
    Optional<Utilisateur> findByEmail(String email);
    List<Utilisateur> findByRole(Role role);
}
