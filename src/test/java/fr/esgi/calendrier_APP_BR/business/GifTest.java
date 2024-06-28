package fr.esgi.calendrier_APP_BR.business;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GifTest {

    @Test
    void testId() {
        Long expectedId = 1L;
        Gif gif = new Gif();
        gif.setId(expectedId);
        assertEquals(expectedId, gif.getId());
    }

    @Test
    void testUrl() {
        String expectedUrl = "https://example.com/example.gif";
        Gif gif = new Gif();
        gif.setUrl(expectedUrl);
        assertEquals(expectedUrl, gif.getUrl());
    }
}