package com.miniprojet.bibliotheque.services;

import com.miniprojet.bibliotheque.Entities.Livre;

import java.util.List;
import java.util.Optional;

public interface ILivreServ {
    List<Livre> selectAll();

    Livre findById(int id);

    List<Livre> getByTitre(String titre);

    List<Livre> getByAuteur(String auteur);

    List<Livre> getByCategorie(String categorie);

    List<Livre> estDisponible();

    Livre ajouter(Livre livre);

    Livre modifier(int id, Livre livre);

    void supprimer(int id);
}
