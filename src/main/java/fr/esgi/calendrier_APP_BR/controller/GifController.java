package fr.esgi.calendrier_APP_BR.controller;

import fr.esgi.calendrier_APP_BR.business.Gif;
import fr.esgi.calendrier_APP_BR.business.JourCalendrier;
import fr.esgi.calendrier_APP_BR.business.Utilisateur;
import fr.esgi.calendrier_APP_BR.business.customId.JourCalendrierId;
import fr.esgi.calendrier_APP_BR.service.GifService;
import fr.esgi.calendrier_APP_BR.service.JourCalendrierService;
import fr.esgi.calendrier_APP_BR.service.UtilisateurService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.logging.Logger;


@Api(tags = "Gif")
@Controller
@AllArgsConstructor
public class GifController {

    private JourCalendrierService jourCalendrierService;
    private GifService gifService;
    private UtilisateurService utilisateurService;
    @GetMapping("gif/save/form/{jour}/{mois}")
    public String gifDistant(
            Model model,
            @PathVariable(value = "jour") String jour,
            @PathVariable(value = "mois") String mois
    ) {
        JourCalendrierId jourCalendrierId = new JourCalendrierId(Integer.parseInt(jour), Integer.parseInt(mois));

        model.addAttribute("jour", jourCalendrierService.findById(jourCalendrierId));

        return "uploadGif";
    }

    @PostMapping("/gif/save/form/{jour}/{mois}")
    public String addGif(
            @RequestParam String url,
            @PathVariable(value = "jour") String jour,
            @PathVariable(value = "mois") String mois,
            @AuthenticationPrincipal Utilisateur utilisateur,
            RedirectAttributes redirectAttributes
    ) {
        Logger logger = Logger.getLogger(GifController.class.getName());
        try {
            JourCalendrierId jourCalendrierId = new JourCalendrierId(Integer.parseInt(jour), Integer.parseInt(mois));
            JourCalendrier jourCalendrier = jourCalendrierService.findById(jourCalendrierId);
            int cost = jourCalendrier.getPoints();
            logger.info("Cost = " + cost);

            Utilisateur managedUtilisateur = utilisateurService.findById(utilisateur.getId());
            if (managedUtilisateur.getSoldePoints() < cost) {
                logger.warning("Solde insuffisant pour poster un gif");
                redirectAttributes.addFlashAttribute("message", "Solde insuffisant pour poster un gif");
                return "redirect:/";
            }

            managedUtilisateur.setSoldePoints(managedUtilisateur.getSoldePoints() - cost);
            logger.info("New soldePoints = " + managedUtilisateur.getSoldePoints());
            utilisateurService.save(managedUtilisateur);

            Gif gif = new Gif();
            gif.setUrl(url);
            gifService.save(gif);

            jourCalendrierService.setGif(jourCalendrierId, gif);
            jourCalendrierService.setUtilisateur(jourCalendrierId, managedUtilisateur);
            jourCalendrierService.save(jourCalendrier);

            redirectAttributes.addFlashAttribute("message", "Gif ajouté avec succès");
            return "redirect:/";
        } catch (Exception e) {
            e.printStackTrace();
            logger.severe("Erreur lors de l'ajout du gif: " + e.getMessage());
            redirectAttributes.addFlashAttribute("message", "Erreur lors de l'ajout du gif");
            return "redirect:/";
        }
    }
}
