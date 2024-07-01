package fr.esgi.calendrier_APP_BR.controller;

import fr.esgi.calendrier_APP_BR.business.JourCalendrier;
import fr.esgi.calendrier_APP_BR.business.Reaction;
import fr.esgi.calendrier_APP_BR.business.Utilisateur;
import fr.esgi.calendrier_APP_BR.business.customId.JourCalendrierId;
import fr.esgi.calendrier_APP_BR.repository.JourCalendrierRepository;
import fr.esgi.calendrier_APP_BR.repository.ReactionRepository;
import fr.esgi.calendrier_APP_BR.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/reaction")
public class ReactionController {

    @Autowired
    private JourCalendrierRepository jourCalendrierRepository;

    @Autowired
    private ReactionRepository reactionRepository;

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @PostMapping("/add")
    public ResponseEntity<?> addReaction(@RequestParam String date, @RequestParam String emoji, @RequestParam Long utilisateurId) {
        JourCalendrierId id = JourCalendrier.fromDate(date);
        Optional<JourCalendrier> optionalJourCalendrier = jourCalendrierRepository.findById(id);
        Optional<Utilisateur> optionalUtilisateur = utilisateurRepository.findById(utilisateurId);

        if (optionalJourCalendrier.isPresent() && optionalUtilisateur.isPresent()) {
            JourCalendrier jourCalendrier = optionalJourCalendrier.get();
            Utilisateur utilisateur = optionalUtilisateur.get();

            Reaction reaction = new Reaction();
            reaction.setUnicode(emoji);
            reaction.setUtilisateur(utilisateur);
            reaction.setJourCalendrier(jourCalendrier);

            reactionRepository.save(reaction);

            System.out.println("Added reaction: " + emoji + " by user " + utilisateurId + " on date " + date);

            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/list")
    public ResponseEntity<List<String>> listReactions(@RequestParam String date) {
        JourCalendrierId id = JourCalendrier.fromDate(date);
        Optional<JourCalendrier> optionalJourCalendrier = jourCalendrierRepository.findById(id);

        if (optionalJourCalendrier.isPresent()) {
            JourCalendrier jourCalendrier = optionalJourCalendrier.get();
            List<String> reactions = reactionRepository.findByJourCalendrier(jourCalendrier).stream()
                    .map(reaction -> reaction.getUnicode() + ": " + reaction.getUtilisateur().getPrenom() + " " + reaction.getUtilisateur().getNom())
                    .collect(Collectors.toList());

            System.out.println("Fetched reactions for date " + date + ": " + reactions);

            return ResponseEntity.ok(reactions);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
