package com.miniprojet.bibliotheque.services;

import com.miniprojet.bibliotheque.Entities.Emprunt;

import java.time.LocalDate;
import java.util.List;

public interface IEmpruntServ {
    List<Emprunt> findAll();
    Emprunt findById(int id);
    List<Emprunt> findByAdherent(int adherentId);
    List<Emprunt> findNonRetournes();
    Emprunt emprunter(int adherentId, int livreId, LocalDate dateRetour);
    Emprunt validerRetour(int id);
    List<Emprunt> historique(int adherentId);
}
