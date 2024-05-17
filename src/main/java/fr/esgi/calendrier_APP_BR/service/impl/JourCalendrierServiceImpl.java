package fr.esgi.calendrier_APP_BR.service.impl;

import fr.esgi.calendrier_APP_BR.business.JourCalendrier;
import fr.esgi.calendrier_APP_BR.repository.JourCalendrierRepository;
import fr.esgi.calendrier_APP_BR.service.JourCalendrierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JourCalendrierServiceImpl implements JourCalendrierService {

    private final JourCalendrierRepository jourCalendrierRepository;

    @Autowired
    public JourCalendrierServiceImpl(JourCalendrierRepository jourCalendrierRepository) {
        this.jourCalendrierRepository = jourCalendrierRepository;
    }

    @Override
    public JourCalendrier getById(Long id) {
        return jourCalendrierRepository.findById(id).orElse(null);
    }

    @Override
    public JourCalendrier save(JourCalendrier jourCalendrier) {
        return jourCalendrierRepository.save(jourCalendrier);
    }

    @Override
    public List<JourCalendrier> findAll() {
        return jourCalendrierRepository.findAll();
    }

    @Override
    public JourCalendrier findById(Long id) {
        return jourCalendrierRepository.findById(id).orElse(null);
    }
}
