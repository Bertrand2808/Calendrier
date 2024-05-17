package fr.esgi.calendrier_APP_BR.business;

import org.junit.jupiter.api.Test;
import java.util.Date;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class JourCalendrierTest {

    @Test
    void getId() {
        JourCalendrier jourCalendrier = new JourCalendrier();
        jourCalendrier.setId(1L);
        assertEquals(1L, jourCalendrier.getId());
    }

    @Test
    void getDate() {
        JourCalendrier jourCalendrier = new JourCalendrier();
        Date date = new Date();
        jourCalendrier.setDate(date);
        assertEquals(date, jourCalendrier.getDate());
    }

    @Test
    void getGifs() {
        JourCalendrier jourCalendrier = new JourCalendrier();
        Gif gif = new Gif(); // Assume Gif is a valid class
        jourCalendrier.setGifs(Collections.singletonList(gif));
        assertEquals(Collections.singletonList(gif), jourCalendrier.getGifs());
    }

    @Test
    void setId() {
        JourCalendrier jourCalendrier = new JourCalendrier();
        jourCalendrier.setId(2L);
        assertEquals(2L, jourCalendrier.getId());
    }

    @Test
    void setDate() {
        JourCalendrier jourCalendrier = new JourCalendrier();
        Date date = new Date();
        jourCalendrier.setDate(date);
        assertEquals(date, jourCalendrier.getDate());
    }

    @Test
    void setGifs() {
        JourCalendrier jourCalendrier = new JourCalendrier();
        Gif gif = new Gif(); // Assume Gif is a valid class
        jourCalendrier.setGifs(Collections.singletonList(gif));
        assertEquals(Collections.singletonList(gif), jourCalendrier.getGifs());
    }
}