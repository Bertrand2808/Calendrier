package fr.esgi.calendrier_APP_BR.service;

import fr.esgi.calendrier_APP_BR.business.Utilisateur;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UtilisateurService extends UserDetailsService {
    Utilisateur getById(Long id);
    Utilisateur getByEmail(String email);
    Utilisateur save(Utilisateur utilisateur);
}
