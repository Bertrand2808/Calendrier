package fr.esgi.calendrier_APP_BR.service.impl;

import fr.esgi.calendrier_APP_BR.business.Gif;
import fr.esgi.calendrier_APP_BR.repository.GifRepository;
import fr.esgi.calendrier_APP_BR.service.GifService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class GifServiceImpl implements GifService {

    private final GifRepository gifRepository;

    @Override
    public Gif findById(Long id) {
        return gifRepository.findById(id).orElse(null);
    }

    @Override
    public void save(Gif gif) {
        this.gifRepository.save(gif);
    }

}
