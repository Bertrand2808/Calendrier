package fr.esgi.calendrier_APP_BR.service;

import fr.esgi.calendrier_APP_BR.business.Utilisateur;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface UtilisateurService extends UserDetailsService {
    public Utilisateur findByEmail(String email);
    public void save(Utilisateur utilisateur);
    public Optional<Utilisateur> findById(Long id);
}
