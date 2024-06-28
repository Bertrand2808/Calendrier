package fr.esgi.calendrier_APP_BR.business;

import fr.esgi.calendrier_APP_BR.business.customId.JourCalendrierId;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class JourCalendrierTest {

    @Mock
    private JourCalendrierId jourCalendrierId;

    @Mock
    private Gif gif;

    @Mock
    private Utilisateur utilisateur;

    @Mock
    private List<Reaction> reactions;

    @InjectMocks
    private JourCalendrier jourCalendrier;

    @Test
    void testId() {
        jourCalendrier.setId(jourCalendrierId);
        assertEquals(jourCalendrierId, jourCalendrier.getId());
    }

    @Test
    void testGif() {
        jourCalendrier.setGif(gif);
        assertEquals(gif, jourCalendrier.getGif());
    }

    @Test
    void testUtilisateur() {
        jourCalendrier.setUtilisateur(utilisateur);
        assertEquals(utilisateur, jourCalendrier.getUtilisateur());
    }

    @Test
    void testReactions() {
        jourCalendrier.setReactions(reactions);
        assertEquals(reactions, jourCalendrier.getReactions());
    }

    @Test
    void testPoints() {
        jourCalendrier.setPoints(500);
        assertEquals(500, jourCalendrier.getPoints());
    }
}