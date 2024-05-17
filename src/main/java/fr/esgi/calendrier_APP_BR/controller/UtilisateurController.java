package fr.esgi.calendrier_APP_BR.controller;

import fr.esgi.calendrier_APP_BR.business.Utilisateur;
import fr.esgi.calendrier_APP_BR.repository.UtilisateurRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Api(tags = "Utilisateur")
@RestController
@RequestMapping("/api/utilisateur")
public class UtilisateurController {
    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @ApiOperation(value = "Récupère tous les utilisateurs")
    @GetMapping
    public List<Utilisateur> findAll() {
        return utilisateurRepository.findAll();
    }

    @ApiOperation(value = "Récupère un utilisateur par son id")
    @GetMapping({"/{id}"})
    public Utilisateur findById(@PathVariable Long id) {
        return utilisateurRepository.getById(id);
    }

    @ApiOperation(value = "Crée un utilisateur")
    @PostMapping
    public Utilisateur creeUtilisateur(@RequestBody Utilisateur utilisateur) {
        return utilisateurRepository.save(utilisateur);
    }

    @ApiOperation(value = "Modifie un utilisateur")
    @PutMapping({"/{id}"})
    public Utilisateur modifierUtilisateur(@PathVariable Long id, @RequestBody Utilisateur utilisateurDetails) {
        Optional<Utilisateur> optionalUtilisateur = utilisateurRepository.findById(id);
        if (optionalUtilisateur.isPresent()) {
            Utilisateur utilisateur = optionalUtilisateur.get();
            utilisateur.setNom(utilisateurDetails.getNom());
            utilisateur.setPrenom(utilisateurDetails.getPrenom());
            utilisateur.setEmail(utilisateurDetails.getEmail());
            utilisateur.setMotDePasse(utilisateurDetails.getMotDePasse());
            utilisateur.setSoldePoints(utilisateurDetails.getSoldePoints());
            return utilisateurRepository.save(utilisateur);
        }
        return null;
    }

    @ApiOperation(value = "Supprime un utilisateur")
    @DeleteMapping({"/{id}"})
    public void supprimerUtilisateur(@PathVariable Long id) {
        utilisateurRepository.deleteById(id);
    }
}
