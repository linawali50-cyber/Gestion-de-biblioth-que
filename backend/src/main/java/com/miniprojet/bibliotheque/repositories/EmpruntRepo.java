package com.miniprojet.bibliotheque.repositories;

import com.miniprojet.bibliotheque.Entities.Adherent;
import com.miniprojet.bibliotheque.Entities.Emprunt;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmpruntRepo extends JpaRepository<Emprunt,Integer> {
    List<Emprunt> findByUtilisateurId(int id);
    List<Emprunt> findByRetourne(boolean retourne);
}
