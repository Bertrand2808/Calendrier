package fr.esgi.calendrier_APP_BR.service;

import fr.esgi.calendrier_APP_BR.business.Reaction;

public interface ReactionService {
    public void save(Reaction emotion);

    public Reaction findById(Long id);
    public Reaction findByUnicode(String unicode);
}
