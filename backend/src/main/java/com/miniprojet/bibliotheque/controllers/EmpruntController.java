package com.miniprojet.bibliotheque.controllers;


import com.miniprojet.bibliotheque.Entities.Emprunt;
import com.miniprojet.bibliotheque.services.IEmpruntServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/emprunts")
public class EmpruntController {

    @Autowired
    IEmpruntServ empruntServ;

    @GetMapping
    public List<Emprunt> getAll() {
        return empruntServ.findAll();
    }

    @GetMapping("/{id}")
    public Emprunt getById(@PathVariable int id) {
        return empruntServ.findById(id);
    }

    @GetMapping("/adherent/{adherentId}")
    public List<Emprunt> getByAdherent(@PathVariable int adherentId) {
        return empruntServ.findByAdherent(adherentId);
    }

    @GetMapping("/NonRetourner")
    public List<Emprunt> getNonRetournes() {
        return empruntServ.findNonRetournes();
    }

    @GetMapping("/historique/{adherentId}")
    public List<Emprunt> getHistorique(@PathVariable int adherentId) {
        return empruntServ.historique(adherentId);
    }

    @PostMapping("/emprunter/{adherentId}/{livreId}")
    public Emprunt emprunter(
            @PathVariable int adherentId,
            @PathVariable int livreId,
            @RequestParam String dateRetour) {
        return empruntServ.emprunter(adherentId, livreId, LocalDate.parse(dateRetour));
    }

    @PutMapping("/retour/{id}")
    public Emprunt validerRetour(@PathVariable int id) {
        return empruntServ.validerRetour(id);
    }
}
