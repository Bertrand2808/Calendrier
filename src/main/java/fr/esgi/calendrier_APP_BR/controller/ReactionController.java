package fr.esgi.calendrier_APP_BR.controller;

import fr.esgi.calendrier_APP_BR.business.Reaction;
import fr.esgi.calendrier_APP_BR.business.Utilisateur;
import fr.esgi.calendrier_APP_BR.business.customId.JourCalendrierId;
import fr.esgi.calendrier_APP_BR.service.ReactionService;
import fr.esgi.calendrier_APP_BR.service.JourCalendrierService;
import fr.esgi.calendrier_APP_BR.service.UtilisateurService;
import fr.esgi.calendrier_APP_BR.service.ReactionJourService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
@RequestMapping("/reaction")
public class ReactionController {

    private final ReactionService reactionService;
    private final JourCalendrierService jourCalendrierService;
    private final UtilisateurService utilisateurService;
    private final ReactionJourService reactionJourService;

    @PostMapping("/add")
    public String addReaction(
            @RequestParam("jour") int jour,
            @RequestParam("mois") int mois,
            @RequestParam("emoji") String emoji,
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        Reaction reaction = new Reaction();
        reaction.setUnicode(emoji);
        reactionService.save(reaction);

        JourCalendrierId jourCalendrierId = new JourCalendrierId(jour, mois);
        Utilisateur utilisateur = utilisateurService.findByEmail(userDetails.getUsername());

        reactionJourService.addReactionJour(jourCalendrierId, reaction, utilisateur);

        return "redirect:/";
    }

    @GetMapping("/count")
    @ResponseBody
    public int countReactions(
            @RequestParam("jour") int jour,
            @RequestParam("mois") int mois,
            @RequestParam("emoji") String emoji
    ) {
        JourCalendrierId jourCalendrierId = new JourCalendrierId(jour, mois);
        return reactionJourService.countReactions(jourCalendrierId, emoji);
    }
}
