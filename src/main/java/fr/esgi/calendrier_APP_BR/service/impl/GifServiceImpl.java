package fr.esgi.calendrier_APP_BR.service.impl;

import fr.esgi.calendrier_APP_BR.business.Gif;
import fr.esgi.calendrier_APP_BR.repository.GifRepository;
import fr.esgi.calendrier_APP_BR.service.GifService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class GifServiceImpl implements GifService {

    private final GifRepository gifRepository;

    @Autowired
    public GifServiceImpl(GifRepository gifRepository) {
        this.gifRepository = gifRepository;
    }

    @Override
    public Gif getById(Long id) {
        return gifRepository.findById(id).orElse(null);
    }

    @Override
    public Gif save(Gif gif) {
        return gifRepository.save(gif);
    }

    @Override
    public void addGifFromFile(MultipartFile file, String legend, Long dayId) {

    }

    @Override
    public void addGifFromUrl(String url, String legend, Long dayId) {

    }
}
