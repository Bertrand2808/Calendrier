package fr.esgi.calendrier_APP_BR.service;

import fr.esgi.calendrier_APP_BR.business.JourCalendrier;

import java.util.List;

public interface JourCalendrierService {
    JourCalendrier getById(Long id);
    JourCalendrier save(JourCalendrier jourCalendrier);

    List<JourCalendrier> findAll();

    JourCalendrier findById(Long id);
}
