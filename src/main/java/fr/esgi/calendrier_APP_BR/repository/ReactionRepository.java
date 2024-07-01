package fr.esgi.calendrier_APP_BR.repository;

import fr.esgi.calendrier_APP_BR.business.JourCalendrier;
import fr.esgi.calendrier_APP_BR.business.Reaction;
import fr.esgi.calendrier_APP_BR.business.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReactionRepository extends JpaRepository<Reaction, Long> {

    public Optional<Reaction> findByUnicode(String unicode);
    public List<Reaction> findByJourCalendrier(JourCalendrier jourCalendrier);
    public Optional<Reaction> findByJourCalendrierAndUtilisateur(JourCalendrier jourCalendrier, Utilisateur utilisateur);
}
