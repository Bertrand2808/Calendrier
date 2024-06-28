package fr.esgi.calendrier_APP_BR.business;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ReactionTest {

    @Test
    void testId() {
        Long expectedId = 1L;
        Reaction reaction = new Reaction();
        reaction.setId(expectedId);
        assertEquals(expectedId, reaction.getId());
    }

    @Test
    void testUnicode() {
        String expectedUnicode = "Happy";
        Reaction reaction = new Reaction();
        reaction.setUnicode(expectedUnicode);
        assertEquals(expectedUnicode, reaction.getUnicode());
    }
}