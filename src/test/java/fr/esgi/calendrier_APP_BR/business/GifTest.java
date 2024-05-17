package fr.esgi.calendrier_APP_BR.business;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GifTest {
    @Test
    void getId() {
        Gif gif = new Gif();
        gif.setId(1L);
        assertEquals(1L, gif.getId());
    }

    @Test
    void getUtilisateur() {
        Gif gif = new Gif();
        Utilisateur utilisateur = new Utilisateur(); // Assume Utilisateur is a valid class
        gif.setUtilisateur(utilisateur);
        assertEquals(utilisateur, gif.getUtilisateur());
    }

    @Test
    void getJour() {
        Gif gif = new Gif();
        JourCalendrier jour = new JourCalendrier(); // Assume JourCalendrier is a valid class
        gif.setJour(jour);
        assertEquals(jour, gif.getJour());
    }

    @Test
    void getUrl() {
        Gif gif = new Gif();
        gif.setUrl("https://example.com/example.gif");
        assertEquals("https://example.com/example.gif", gif.getUrl());
    }

    @Test
    void setId() {
        Gif gif = new Gif();
        gif.setId(2L);
        assertEquals(2L, gif.getId());
    }

    @Test
    void setUtilisateur() {
        Gif gif = new Gif();
        Utilisateur utilisateur = new Utilisateur(); // Assume Utilisateur is a valid class
        gif.setUtilisateur(utilisateur);
        assertEquals(utilisateur, gif.getUtilisateur());
    }

    @Test
    void setJour() {
        Gif gif = new Gif();
        JourCalendrier jour = new JourCalendrier(); // Assume JourCalendrier is a valid class
        gif.setJour(jour);
        assertEquals(jour, gif.getJour());
    }

    @Test
    void setUrl() {
        Gif gif = new Gif();
        gif.setUrl("https://example.com/example2.gif");
        assertEquals("https://example.com/example2.gif", gif.getUrl());
    }
}