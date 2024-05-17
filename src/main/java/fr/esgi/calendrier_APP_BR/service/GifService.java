package fr.esgi.calendrier_APP_BR.service;

import fr.esgi.calendrier_APP_BR.business.Gif;
import org.springframework.web.multipart.MultipartFile;

public interface GifService {
    Gif getById(Long id);
    Gif save(Gif gif);

    void addGifFromFile(MultipartFile file, String legend, Long dayId);

    void addGifFromUrl(String url, String legend, Long dayId);
}