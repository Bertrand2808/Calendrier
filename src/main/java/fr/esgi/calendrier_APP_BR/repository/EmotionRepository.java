package fr.esgi.calendrier_APP_BR.repository;

import fr.esgi.calendrier_APP_BR.business.Emotion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmotionRepository extends JpaRepository<Emotion, Long> {
}
