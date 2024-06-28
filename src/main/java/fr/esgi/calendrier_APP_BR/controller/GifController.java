package fr.esgi.calendrier_APP_BR.controller;

import fr.esgi.calendrier_APP_BR.business.Gif;
import fr.esgi.calendrier_APP_BR.business.Utilisateur;
import fr.esgi.calendrier_APP_BR.business.customId.JourCalendrierId;
import fr.esgi.calendrier_APP_BR.service.GifService;
import fr.esgi.calendrier_APP_BR.service.JourCalendrierService;
import fr.esgi.calendrier_APP_BR.service.UtilisateurService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("gif/save/form/{jour}/{mois}")
    public String addGif(
            String url,
            @PathVariable(value = "jour") String jour,
            @PathVariable(value = "mois") String mois,
            @AuthenticationPrincipal Utilisateur utilisateur
            ) {
        Gif gif = new Gif();
        gif.setUrl(url);
        gifService.save(gif);

        JourCalendrierId jourCalendrierId = new JourCalendrierId(Integer.parseInt(jour), Integer.parseInt(mois));
        jourCalendrierService.setGif(jourCalendrierId, gif);
        Utilisateur managedUtilisateur = utilisateurService.findById(utilisateur.getId()).orElseThrow(() -> new RuntimeException("User not found"));

        jourCalendrierService.setUtilisateur(jourCalendrierId, managedUtilisateur);

        return "redirect:/";
    }
}
