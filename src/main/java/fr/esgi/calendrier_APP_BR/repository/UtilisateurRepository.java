package fr.esgi.calendrier_APP_BR.repository;

import fr.esgi.calendrier_APP_BR.business.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {
    Optional<Utilisateur> findByEmail(String email);
}
