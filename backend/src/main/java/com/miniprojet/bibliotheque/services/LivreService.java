package com.miniprojet.bibliotheque.services;

import com.miniprojet.bibliotheque.Entities.Livre;
import com.miniprojet.bibliotheque.repositories.LivreRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LivreService implements ILivreServ{
    @Autowired
    private LivreRepo livreRepo;


    public List<Livre> selectAll() {
        return livreRepo.findAll();
    }
    public Livre findById(int id) {
        return livreRepo.findById(id).get();
    }
    public List<Livre> getByTitre(String titre) {
        return livreRepo.findByTitre(titre);
    }
    public List<Livre> getByAuteur(String auteur) {
        return livreRepo.findByAuteur(auteur);
    }
    public List<Livre> getByCategorie(String categorie) {
        return livreRepo.findByCategorie(categorie);
    }
    public List<Livre> estDisponible() {
        return livreRepo.findByDisponible(true);
    }
    public Livre ajouter(Livre livre) {
        if (livre.getTitre() == null || livre.getAuteur() == null) {
            throw new RuntimeException("Champs obligatoires manquants");
        }
        if (livre.getTitre().length() < 2 || livre.getAuteur().length() < 2) {
            throw new RuntimeException("Titre ou auteur invalide");
        }
        Optional<Livre> existant = livreRepo.findByTitreAndAuteur(livre.getTitre(), livre.getAuteur());
        if (existant.isPresent()) {
            throw new RuntimeException("Livre déjà existant");
        }
        livre.setDisponible(true);
        return livreRepo.save(livre);
        }
    public Livre modifier(int id, Livre livreDetails) {
        Livre livre = livreRepo.findById(id).get();
        if (livre != null) {
            livre.setTitre(livreDetails.getTitre());
            livre.setAuteur(livreDetails.getAuteur());
            livre.setCategorie(livreDetails.getCategorie());
            livre.setDisponible(livreDetails.isDisponible());
            return livreRepo.save(livre);
        }
        return null;
    }
    public void supprimer(int id) {
        Optional<Livre> optLivre = livreRepo.findById(id);
        if (!optLivre.isPresent()) {
            throw new RuntimeException("Livre introuvable");
        }
        Livre livre = optLivre.get();
        if (!livre.isDisponible()) {
            throw new RuntimeException("Impossible de supprimer un livre emprunté ou indisponible");
        }
        livreRepo.deleteById(id);
    }


}
