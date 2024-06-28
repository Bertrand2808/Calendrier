package fr.esgi.calendrier_APP_BR.repository;

import fr.esgi.calendrier_APP_BR.business.JourCalendrier;
import fr.esgi.calendrier_APP_BR.business.customId.JourCalendrierId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JourCalendrierRepository extends JpaRepository<JourCalendrier, JourCalendrierId> {
}
