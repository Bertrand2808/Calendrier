package fr.esgi.calendrier_APP_BR.initialisation;

import fr.esgi.calendrier_APP_BR.business.Emotion;
import fr.esgi.calendrier_APP_BR.business.JourCalendrier;
import fr.esgi.calendrier_APP_BR.business.Theme;
import fr.esgi.calendrier_APP_BR.repository.EmotionRepository;
import fr.esgi.calendrier_APP_BR.repository.JourCalendrierRepository;
import fr.esgi.calendrier_APP_BR.repository.ThemeRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.stream.IntStream;

@Component
@AllArgsConstructor
public class AjoutDonneesInitiales implements CommandLineRunner {

    private final ThemeRepository themeRepository;
    private final JourCalendrierRepository jourCalendrierRepository;
    private final EmotionRepository emotionRepository;

    @Override
    public void run(String... args) throws Exception {
        // Ajout de deux thèmes
        themeRepository.save(new Theme("Light"));
        themeRepository.save(new Theme("Dark"));

        // Ajout de tous les jours du mois en cours
        YearMonth currentMonth = YearMonth.now();
        IntStream.rangeClosed(1, currentMonth.lengthOfMonth())
                .mapToObj(day -> new JourCalendrier(LocalDate.of(currentMonth.getYear(), currentMonth.getMonth(), day)))
                .forEach(jourCalendrierRepository::save);

        // Ajout de cinq émotions
        emotionRepository.save(new Emotion("Souriant"));
        emotionRepository.save(new Emotion("Monocle"));
        emotionRepository.save(new Emotion("Triste"));
        emotionRepository.save(new Emotion("En colère"));
        emotionRepository.save(new Emotion("Surpris"));
    }
}
