package fr.esgi.calendrier_APP_BR.service.impl;

import fr.esgi.calendrier_APP_BR.business.Gif;
import fr.esgi.calendrier_APP_BR.business.JourCalendrier;
import fr.esgi.calendrier_APP_BR.business.Reaction;
import fr.esgi.calendrier_APP_BR.business.Utilisateur;
import fr.esgi.calendrier_APP_BR.business.customId.JourCalendrierId;
import fr.esgi.calendrier_APP_BR.repository.JourCalendrierRepository;
import fr.esgi.calendrier_APP_BR.service.JourCalendrierService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class JourCalendrierServiceImpl implements JourCalendrierService {

    private final JourCalendrierRepository jourCalendrierRepository;
    private final ReactionServiceImpl reactionServiceImpl;

    @Override
    public void save(JourCalendrier jourCalendrier) {
        this.jourCalendrierRepository.save(jourCalendrier);
    }

    @Override
    public void setGif(JourCalendrierId id, Gif gif) {
        JourCalendrier jourCalendrier = jourCalendrierRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Jour not found"));
        jourCalendrier.setGif(gif);
        this.jourCalendrierRepository.save(jourCalendrier);
    }

    @Override
    public Page<JourCalendrier> findAll(Pageable pageable) {
        return this.jourCalendrierRepository.findAll(pageable);
    }

    @Override
    public JourCalendrier findById(JourCalendrierId id) {
        return this.jourCalendrierRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Jour not found"));
    }

    public void setUtilisateur(JourCalendrierId id, Utilisateur utilisateur) {
        Optional<JourCalendrier> optionalJourCalendrier = jourCalendrierRepository.findById(id);
        if (optionalJourCalendrier.isPresent()) {
            JourCalendrier jourCalendrier = optionalJourCalendrier.get();
            jourCalendrier.setUtilisateur(utilisateur);
            jourCalendrierRepository.save(jourCalendrier);
        }
    }

    public void addReaction(JourCalendrierId id, Reaction reaction) {
        Optional<JourCalendrier> jourCalendrier = jourCalendrierRepository.findById(id);
        if (jourCalendrier.isPresent()) {
            JourCalendrier jour = jourCalendrier.get();
            reaction.setJourCalendrier(jour); // Assuming Reaction has a relationship with JourCalendrier
            jour.getReactions().add(reaction);
            reactionServiceImpl.save(reaction);
            jourCalendrierRepository.save(jour);
        }
    }
}
