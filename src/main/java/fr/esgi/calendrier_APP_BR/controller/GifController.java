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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
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
            @RequestParam("legende") String legende,
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
            gif.setLegende(legende);
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

    @GetMapping("/gif/save/form-local/{jour}/{mois}")
    public String gifLocal(
            Model model,
            @PathVariable(value = "jour") String jour,
            @PathVariable(value = "mois") String mois
    ) {
        JourCalendrierId jourCalendrierId = new JourCalendrierId(Integer.parseInt(jour), Integer.parseInt(mois));

        model.addAttribute("jour", jourCalendrierService.findById(jourCalendrierId));

        return "uploadGifLocal";
    }

    @PostMapping("/gif/save/form-local/{jour}/{mois}")
    public String addGifLocal(
            @RequestParam("file") MultipartFile file,
            @RequestParam("legende") String legende,
            @PathVariable(value = "jour") String jour,
            @PathVariable(value = "mois") String mois,
            @AuthenticationPrincipal Utilisateur utilisateur,
            RedirectAttributes redirectAttributes
    ) {
        Logger logger = Logger.getLogger(GifController.class.getName());
        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Veuillez sélectionner un fichier");
            return "redirect:/gif/save/form-local/" + jour + "/" + mois;
        }
        try {
            String uploadDirectory = "src/main/resources/static/gif/";
            String fileName = file.getOriginalFilename();
            Path path = Path.of(uploadDirectory + fileName);
            Files.copy(file.getInputStream(), path);

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
            gif.setUrl("/" + fileName);
            gif.setLegende(legende);
            gifService.save(gif);

            jourCalendrierService.setGif(jourCalendrierId, gif);
            jourCalendrierService.setUtilisateur(jourCalendrierId, managedUtilisateur);
            jourCalendrierService.save(jourCalendrier);

            redirectAttributes.addFlashAttribute("message", "Gif ajouté avec succès");
        } catch (Exception e) {
            e.printStackTrace();
            logger.severe("Erreur lors de l'ajout du gif: " + e.getMessage());
            redirectAttributes.addFlashAttribute("message", "Erreur lors de l'ajout du gif");
            return "redirect:/";
        }
        return "redirect:/";
    }
}
