package fr.esgi.calendrier_APP_BR.business;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class EmotionTest {

    @Test
    void testNom() {
        String expectedNom = "Happy";
        Emotion emotion = new Emotion(expectedNom);
        assertEquals(expectedNom, emotion.getNom());
    }

    @Test
    void testId() {
        Long expectedId = 1L;
        Emotion emotion = new Emotion("Happy");
        emotion.setId(expectedId);
        assertEquals(expectedId, emotion.getId());
    }
}