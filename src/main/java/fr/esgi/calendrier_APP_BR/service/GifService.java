package fr.esgi.calendrier_APP_BR.service;

import fr.esgi.calendrier_APP_BR.business.Gif;
import org.springframework.web.multipart.MultipartFile;

public interface GifService {
    public Gif findById(Long id);
    public void save(Gif gif);
}