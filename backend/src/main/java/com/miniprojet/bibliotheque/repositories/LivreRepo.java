package com.miniprojet.bibliotheque.repositories;

import com.miniprojet.bibliotheque.Entities.Adherent;
import com.miniprojet.bibliotheque.Entities.Livre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface LivreRepo extends JpaRepository<Livre,Integer> {
    List<Livre> findByTitre(String titre);
    List<Livre> findByAuteur(String auteur);
    List<Livre> findByCategorie(String categorie);
    List<Livre> findByDisponible(boolean disponible);
    Optional<Livre> findByTitreAndAuteur(String titre, String auteur);
}
