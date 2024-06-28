package fr.esgi.calendrier_APP_BR.service;

import fr.esgi.calendrier_APP_BR.business.Reaction;
import fr.esgi.calendrier_APP_BR.business.Utilisateur;
import fr.esgi.calendrier_APP_BR.business.customId.JourCalendrierId;

public interface ReactionJourService {
    public void addReactionJour(JourCalendrierId jourCalendrierId, Reaction reaction, Utilisateur utilisateur);
    public int countReactions(JourCalendrierId jourCalendrierId, String emoji);
}
