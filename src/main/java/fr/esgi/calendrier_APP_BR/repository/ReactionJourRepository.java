package fr.esgi.calendrier_APP_BR.repository;

import fr.esgi.calendrier_APP_BR.business.ReactionJour;
import fr.esgi.calendrier_APP_BR.business.customId.JourCalendrierId;
import fr.esgi.calendrier_APP_BR.business.customId.ReactionJourId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReactionJourRepository extends JpaRepository<ReactionJour, ReactionJourId>{
    public int countByIdJourCalendrierIdAndReactionUnicode(JourCalendrierId jourCalendrierId, String unicode);

}
