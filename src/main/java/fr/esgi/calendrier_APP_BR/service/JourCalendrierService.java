package fr.esgi.calendrier_APP_BR.service;

import fr.esgi.calendrier_APP_BR.business.Gif;
import fr.esgi.calendrier_APP_BR.business.JourCalendrier;
import fr.esgi.calendrier_APP_BR.business.Reaction;
import fr.esgi.calendrier_APP_BR.business.Utilisateur;
import fr.esgi.calendrier_APP_BR.business.customId.JourCalendrierId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface JourCalendrierService {
    public void save(JourCalendrier jourCalendrier);
    public void setGif(JourCalendrierId id, Gif gif);
    public Page<JourCalendrier> findAll(Pageable pageable);
    public void setUtilisateur(JourCalendrierId id, Utilisateur utilisateur);
    public JourCalendrier findById(JourCalendrierId id);
    public void addReaction(JourCalendrierId id, Reaction reaction);
}
