package fr.esgi.calendrier_APP_BR.controller;

import fr.esgi.calendrier_APP_BR.business.JourCalendrier;
import fr.esgi.calendrier_APP_BR.business.Reaction;
import fr.esgi.calendrier_APP_BR.business.Utilisateur;
import fr.esgi.calendrier_APP_BR.business.customId.JourCalendrierId;
import fr.esgi.calendrier_APP_BR.repository.JourCalendrierRepository;
import fr.esgi.calendrier_APP_BR.repository.ReactionRepository;
import fr.esgi.calendrier_APP_BR.repository.UtilisateurRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/reaction")
@AllArgsConstructor
public class ReactionController {

    private final JourCalendrierRepository jourCalendrierRepository;
    private final ReactionRepository reactionRepository;
    private final UtilisateurRepository utilisateurRepository;

    @PostMapping("/add")
    public ResponseEntity<?> addReaction(@RequestParam String date, @RequestParam String emoji, @RequestParam Long utilisateurId) {
        JourCalendrierId id = JourCalendrier.fromDate(date);
        Optional<JourCalendrier> optionalJourCalendrier = jourCalendrierRepository.findById(id);
        Optional<Utilisateur> optionalUtilisateur = utilisateurRepository.findById(utilisateurId);

        if (optionalJourCalendrier.isPresent() && optionalUtilisateur.isPresent()) {
            JourCalendrier jourCalendrier = optionalJourCalendrier.get();
            Utilisateur utilisateur = optionalUtilisateur.get();

            boolean reactionExists = reactionRepository.findByJourCalendrierAndUtilisateur(jourCalendrier, utilisateur).isPresent();
            if (reactionExists) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Reaction already exists");
            }

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

    @PostMapping("/react/{jour}/{mois}")
    public String reactToGif(@PathVariable int jour, @PathVariable int mois, @RequestParam String emoji, @RequestParam Long utilisateurId, Model model) {
        JourCalendrierId id = new JourCalendrierId(jour, mois);
        Optional<JourCalendrier> optionalJourCalendrier = jourCalendrierRepository.findById(id);
        Optional<Utilisateur> optionalUtilisateur = utilisateurRepository.findById(utilisateurId);

        if (optionalJourCalendrier.isPresent() && optionalUtilisateur.isPresent()) {
            JourCalendrier jourCalendrier = optionalJourCalendrier.get();
            Utilisateur utilisateur = optionalUtilisateur.get();

            boolean reactionExists = reactionRepository.findByJourCalendrierAndUtilisateur(jourCalendrier, utilisateur).isPresent();
            if (reactionExists) {
                model.addAttribute("error", "Vous avez déjà réagi à ce gif.");
                model.addAttribute("jour", jourCalendrier);
                return "reactGif";
            }

            Reaction reaction = new Reaction();
            reaction.setUnicode(emoji);
            reaction.setUtilisateur(utilisateur);
            reaction.setJourCalendrier(jourCalendrier);

            reactionRepository.save(reaction);

            return "redirect:/";
        } else {
            model.addAttribute("error", "Jour ou utilisateur non trouvé.");
            if (optionalJourCalendrier.isPresent()) {
                model.addAttribute("jour", optionalJourCalendrier.get());
            }
            return "reactGif";
        }
    }

    @GetMapping("/react/{jour}/{mois}")
    public String showReactGifPage(@PathVariable int jour, @PathVariable int mois, Model model) {
        JourCalendrierId id = new JourCalendrierId(jour, mois);
        Optional<JourCalendrier> optionalJourCalendrier = jourCalendrierRepository.findById(id);

        if (optionalJourCalendrier.isPresent()) {
            JourCalendrier jourCalendrier = optionalJourCalendrier.get();
            model.addAttribute("jour", jourCalendrier);
            return "reactGif";
        } else {
            return "redirect:/";
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

    @GetMapping("/check")
    public ResponseEntity<Void> checkReaction(@RequestParam String date, @RequestParam Long utilisateurId) {
        JourCalendrierId id = JourCalendrier.fromDate(date);
        Optional<JourCalendrier> optionalJourCalendrier = jourCalendrierRepository.findById(id);
        Optional<Utilisateur> optionalUtilisateur = utilisateurRepository.findById(utilisateurId);

        if (optionalJourCalendrier.isPresent() && optionalUtilisateur.isPresent()) {
            JourCalendrier jourCalendrier = optionalJourCalendrier.get();
            Utilisateur utilisateur = optionalUtilisateur.get();
            boolean reactionExists = reactionRepository.findByJourCalendrierAndUtilisateur(jourCalendrier, utilisateur).isPresent();
            if (reactionExists) {
                return ResponseEntity.status(HttpStatus.CONFLICT).build();
            } else {
                return ResponseEntity.ok().build();
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
