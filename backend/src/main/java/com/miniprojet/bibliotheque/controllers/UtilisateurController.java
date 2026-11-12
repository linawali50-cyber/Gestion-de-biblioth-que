package com.miniprojet.bibliotheque.controllers;

import com.miniprojet.bibliotheque.Entities.Adherent;
import com.miniprojet.bibliotheque.Entities.Role;
import com.miniprojet.bibliotheque.Entities.Utilisateur;
import com.miniprojet.bibliotheque.services.IUtilisateurServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/utilisateurs")
public class UtilisateurController {

    @Autowired
    private IUtilisateurServ utilisateurServ;

    @PostMapping("/inscrire")
    public Utilisateur inscrire(@RequestBody Utilisateur u) {
        return utilisateurServ.sInscrire(u);
    }

    @PostMapping("/login")
    public Utilisateur login(@RequestParam String email, @RequestParam String password) {
        return utilisateurServ.login(email, password);
    }

    @GetMapping("/admin/tous")
    public List<Utilisateur> getAllUtilisateurs() {
        return utilisateurServ.findAll();
    }

    @GetMapping("/admin/role")
    public List<Utilisateur> getByRole(@RequestParam Role role) {
        return utilisateurServ.findByRole(role);
    }

    @GetMapping("/admin/adherents")
    public List<Utilisateur> getAdherents() {
        return utilisateurServ.findByRole(Role.ADHERENT);
    }

    @GetMapping("/admin/bibliothecaires")
    public List<Utilisateur> getBibliothecaires() {
        return utilisateurServ.findByRole(Role.BIBLIOTHECAIRE);
    }

    @PutMapping("/admin/valider/{id}")
    public Utilisateur validerCompte(@PathVariable int id) {
        return utilisateurServ.validerCompte(id);
    }

    @PutMapping("/admin/role/{id}")
    public Utilisateur changerRole(@PathVariable int id, @RequestParam Role role) {
        return utilisateurServ.changerRole(id, role);
    }

    @PostMapping("/admin/ajouter")
    public Utilisateur ajouter(@RequestBody Utilisateur u) {
        return utilisateurServ.save(u);
    }

    @DeleteMapping("/admin/{id}")
    public void supprimer(@PathVariable int id) {
        utilisateurServ.supprimer(id);
    }

    @GetMapping("/bibliothecaire/adherent/{id}")
    public Utilisateur getAdherentById(@PathVariable int id) {
        return utilisateurServ.findById(id);
    }

    @PutMapping("/bibliothecaire/adherent/{id}")
    public Utilisateur updateAdherent(@PathVariable int id, @RequestBody Utilisateur u) {
        return utilisateurServ.update(id, u);
    }

    @GetMapping("/{id}")
    public Utilisateur getProfil(@PathVariable int id) {
        return utilisateurServ.findById(id);
    }

    @PutMapping("/{id}/profil")
    public Utilisateur modifierProfil(@PathVariable int id, @RequestBody Utilisateur u) {
        return utilisateurServ.modifierProfil(id, u);
    }
}