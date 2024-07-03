package fr.esgi.calendrier_APP_BR.initialisation;

import fr.esgi.calendrier_APP_BR.business.*;
import fr.esgi.calendrier_APP_BR.business.customId.JourCalendrierId;
import fr.esgi.calendrier_APP_BR.service.*;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Random;
import java.util.stream.IntStream;

@Component
@AllArgsConstructor
public class AjoutDonneesInitiales implements CommandLineRunner {

    private final JourCalendrierService jourCalendrierService;
    private final ReactionService reactionService;
    private final GifService gifService;
    private final UtilisateurService utilisateurService;
    private final ReactionJourService emotionJourService;

    private final Random random = new Random();

    @Override
    public void run(String... args) throws Exception {
        ajoutGif();
        ajoutDesReactions();
        ajoutDesJours();
        ajoutUtilisateurParDefaut();
        nettoyerRepertoireGifs();

        Utilisateur utilisateur = utilisateurService.findByEmail("test@esgi.fr");
        Gif gif = gifService.findById(1L);

        JourCalendrierId jourId = new JourCalendrierId(1, 1);
        JourCalendrier jour = new JourCalendrier();
        jour.setId(jourId);
        jour.setGif(gif);
        jour.setUtilisateur(utilisateur);
        jour.setPoints(100);
        jourCalendrierService.save(jour);
        Reaction reaction = reactionService.findById(1L);
        emotionJourService.addReactionJour(jourId, reaction, utilisateur);
    }

    private void ajoutDesJours() {
        int mois = LocalDate.now().getMonthValue();
        int annee = LocalDate.now().getYear();
        YearMonth yearMonthObject = YearMonth.of(annee, mois);
        int nombreDeJours = yearMonthObject.lengthOfMonth();
        IntStream.range(1, nombreDeJours + 1).forEach(i -> {
            JourCalendrierId jourId = new JourCalendrierId(i, mois);
            JourCalendrier jour = new JourCalendrier();
            jour.setId(jourId);
            jour.setPoints(0);
            jourCalendrierService.save(jour);
        });
    }

    private void ajoutDesReactions() {
        ArrayList<String> emojis = new ArrayList<>();
        emojis.add("\uD83D\uDE0D");
        emojis.add("\uD83D\uDE0E");
        emojis.add("\uD83D\uDE21");
        emojis.add("\uD83D\uDE2D");
        for (String emoji : emojis) {
            Reaction reaction = new Reaction();
            reaction.setUnicode(emoji);
            reactionService.save(reaction);
        }
    }

    private void ajoutGif() {
        Gif gif = new Gif();
        gif.setUrl("https://i.giphy.com/media/v1.Y2lkPTc5MGI3NjExMnNyMWlxZ3EzdDV4ZW5qaHgxZzRpb3V5dGRxbmZ2bWsxeGwxdGwxOCZlcD12MV9pbnRlcm5hbF9naWZfYnlfaWQmY3Q9Zw/Wck09E7lHDabjhHbzJ/giphy.gif");
        gifService.save(gif);
    }

    private void ajoutUtilisateurParDefaut() {
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setNom("test");
        utilisateur.setPrenom("test");
        utilisateur.setEmail("test@esgi.fr");
        utilisateur.setMotDePasse("test123");
        utilisateur.setTheme("dark");
        utilisateurService.save(utilisateur);
    }
    private void nettoyerRepertoireGifs() throws IOException {
        String uploadDirectory = "src/main/resources/static/gif/";
        Files.walk(Path.of(uploadDirectory))
                .filter(Files::isRegularFile)
                .map(Path::toFile)
                .forEach(File::delete);
    }
}

