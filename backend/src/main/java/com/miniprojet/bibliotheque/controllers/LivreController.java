package com.miniprojet.bibliotheque.controllers;

import com.miniprojet.bibliotheque.Entities.Livre;
import com.miniprojet.bibliotheque.services.ILivreServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/livres")
public class LivreController {

    @Autowired
    private ILivreServ livreServ;

    @GetMapping
    public List<Livre> getAll() {
        return livreServ.selectAll();
    }

    @GetMapping("/{id}")
    public Livre getById(@PathVariable int id) {
        return livreServ.findById(id);
    }

    @GetMapping("/titre/{titre}")
    public List<Livre> getByTitre(@PathVariable String titre) {
        return livreServ.getByTitre(titre);
    }

    @GetMapping("/auteur/{auteur}")
    public List<Livre> getByAuteur(@PathVariable String auteur) {
        return livreServ.getByAuteur(auteur);
    }

    @GetMapping("/categorie/{categorie}")
    public List<Livre> getByCategorie(@PathVariable String categorie) {
        return livreServ.getByCategorie(categorie);
    }

    @GetMapping("/disponibles")
    public List<Livre> getDisponibles() {
        return livreServ.estDisponible();
    }

    @PostMapping
    public Livre ajouter(@RequestBody Livre livre) {
        return livreServ.ajouter(livre);
    }

    @PutMapping("/{id}")
    public Livre modifier(@PathVariable int id, @RequestBody Livre livre) {
        return livreServ.modifier(id, livre);
    }

    @DeleteMapping("/{id}")
    public void supprimer(@PathVariable int id) {
        livreServ.supprimer(id);
    }

}
