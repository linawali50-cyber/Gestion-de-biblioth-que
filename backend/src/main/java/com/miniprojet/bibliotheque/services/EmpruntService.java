package com.miniprojet.bibliotheque.services;


import com.miniprojet.bibliotheque.Entities.Adherent;
import com.miniprojet.bibliotheque.Entities.Emprunt;
import com.miniprojet.bibliotheque.Entities.Livre;
import com.miniprojet.bibliotheque.Entities.Utilisateur;
import com.miniprojet.bibliotheque.repositories.EmpruntRepo;
import com.miniprojet.bibliotheque.repositories.LivreRepo;
import com.miniprojet.bibliotheque.repositories.UtilisateurRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EmpruntService implements IEmpruntServ {
    @Autowired
    EmpruntRepo empruntRepo;

    @Autowired
    LivreRepo livreRepo;

    @Autowired
    UtilisateurRepo utilisateurRepo;

    public List<Emprunt> findAll() {
        return empruntRepo.findAll();
    }
    public Emprunt findById(int id) {
        return empruntRepo.findById(id).get();
    }
    public List<Emprunt> findByAdherent(int adherentId) {
        return empruntRepo.findByUtilisateurId(adherentId);
    }
    public List<Emprunt> findNonRetournes() {
        return empruntRepo.findByRetourne(false);
    }
    @Transactional
    public Emprunt emprunter(int adherentId, int livreId, LocalDate dateRetour) {

        Optional<Utilisateur> adherent = utilisateurRepo.findById(adherentId);
        Optional<Livre> livre = livreRepo.findById(livreId);
        if (adherent.isEmpty() || livre.isEmpty() || dateRetour == null) {
            throw new RuntimeException("Données invalides");
        }
        Livre l = livre.get();
        if (!l.isDisponible()) {
            throw new RuntimeException("Livre non disponible");
        }
        Emprunt emprunt = new Emprunt();
        emprunt.setUtilisateur(adherent.get());
        emprunt.setLivre(l);
        emprunt.setDateEmprunt(LocalDate.now());
        emprunt.setDateRetour(dateRetour);
        emprunt.setRetourne(false);

        l.setDisponible(false);
        livreRepo.save(l);

        return empruntRepo.save(emprunt);
    }
    public Emprunt validerRetour(int id) {
        Emprunt emprunt = empruntRepo.findById(id).get();
        if (emprunt != null) {
            emprunt.setDateRetour(LocalDate.now());
            emprunt.setRetourne(true);
            emprunt.getLivre().setDisponible(true);
            livreRepo.save(emprunt.getLivre());
            return empruntRepo.save(emprunt);
        }
        return null;
    }
    public List<Emprunt> historique(int adherentId) {
        return empruntRepo.findByUtilisateurId(adherentId);
    }
}
