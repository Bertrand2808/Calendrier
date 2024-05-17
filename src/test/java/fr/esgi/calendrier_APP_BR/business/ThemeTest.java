package fr.esgi.calendrier_APP_BR.business;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ThemeTest {

    @Test
    void testNom() {
        String expectedNom = "Nature";
        Theme theme = new Theme(expectedNom);
        assertEquals(expectedNom, theme.getNom());
    }

    @Test
    void testId() {
        Long expectedId = 1L;
        Theme theme = new Theme("Nature");
        theme.setId(expectedId);
        assertEquals(expectedId, theme.getId());
    }
}