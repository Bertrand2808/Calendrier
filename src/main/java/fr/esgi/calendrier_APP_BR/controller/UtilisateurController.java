package fr.esgi.calendrier_APP_BR.controller;

import fr.esgi.calendrier_APP_BR.business.Utilisateur;
import fr.esgi.calendrier_APP_BR.dto.UtilisateurDto;
import fr.esgi.calendrier_APP_BR.mapper.UtilisateurMapper;
import fr.esgi.calendrier_APP_BR.service.JourCalendrierService;
import fr.esgi.calendrier_APP_BR.service.UtilisateurService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Api(tags = "Utilisateur")
@Controller
@AllArgsConstructor
public class UtilisateurController {

    private final UtilisateurService utilisateurService;
    private final UtilisateurMapper utilisateurMapper;
    private final JourCalendrierService jourCalendrierService;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        UtilisateurDto utilisateurDto = new UtilisateurDto();
        model.addAttribute("utilisateur", utilisateurDto);
        return "register";
    }

    @PostMapping("/register")
    public String registration(UtilisateurDto utilisateurDto, Model model) {
        utilisateurService.save(utilisateurMapper.toEntity(utilisateurDto));
        return "redirect:/register?success";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/")
    public String home(
            Model model,
            @PageableDefault(size = 7) Pageable pageable
    ) {
        model.addAttribute("jours", jourCalendrierService.findAll(pageable));

        return "index";
    }

    @GetMapping("/utilisateur/points")
    @ResponseBody
    public int getUserPoints(@AuthenticationPrincipal Utilisateur utilisateur) {
        Utilisateur managedUtilisateur = utilisateurService.findById(utilisateur.getId());
        return managedUtilisateur.getSoldePoints();
    }

}
