package fr.esgi.calendrier_APP_BR.service.impl;

import fr.esgi.calendrier_APP_BR.business.Reaction;
import fr.esgi.calendrier_APP_BR.repository.ReactionRepository;
import fr.esgi.calendrier_APP_BR.service.ReactionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class ReactionServiceImpl implements ReactionService {
    private final ReactionRepository reactionRepository;

    @Override
    public void save(Reaction reaction) {
        this.reactionRepository.save(reaction);
    }

    @Override
    public Reaction findById(Long id) {
        return this.reactionRepository.findById(id).orElse(null);
    }

    @Override
    public Reaction findByUnicode(String unicode) {
        return this.reactionRepository.findByUnicode(unicode).orElse(null);
    }
}
