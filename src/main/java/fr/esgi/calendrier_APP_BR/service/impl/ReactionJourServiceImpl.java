package fr.esgi.calendrier_APP_BR.service.impl;

import fr.esgi.calendrier_APP_BR.business.Reaction;
import fr.esgi.calendrier_APP_BR.business.ReactionJour;
import fr.esgi.calendrier_APP_BR.business.Utilisateur;
import fr.esgi.calendrier_APP_BR.business.customId.JourCalendrierId;
import fr.esgi.calendrier_APP_BR.business.customId.ReactionJourId;
import fr.esgi.calendrier_APP_BR.repository.ReactionJourRepository;
import fr.esgi.calendrier_APP_BR.service.ReactionJourService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class ReactionJourServiceImpl implements ReactionJourService {
    private final ReactionJourRepository reactionJourRepository;

    @Override
    public void addReactionJour(JourCalendrierId jourCalendrierId, Reaction reaction, Utilisateur utilisateur) {
        ReactionJour reactionJour = new ReactionJour();
        reactionJour.setId(new ReactionJourId(jourCalendrierId, utilisateur.getId()));
        reactionJour.setReaction(reaction);
        reactionJour.setUtilisateur(utilisateur);
        reactionJourRepository.save(reactionJour);
    }

    @Override
    public int countReactions(JourCalendrierId jourCalendrierId, String emoji) {
        return reactionJourRepository.countByIdJourCalendrierIdAndReactionUnicode(jourCalendrierId, emoji);
    }
}
