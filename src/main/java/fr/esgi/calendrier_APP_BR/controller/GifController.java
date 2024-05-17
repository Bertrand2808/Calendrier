package fr.esgi.calendrier_APP_BR.controller;

import fr.esgi.calendrier_APP_BR.business.Gif;
import fr.esgi.calendrier_APP_BR.repository.GifRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "Gif")
@RestController
@RequestMapping("/api/gif")
public class GifController {

    @Autowired
    private GifRepository gifRepository;

    @ApiOperation(value = "Récupère la liste des gifs")
    @GetMapping
    public List<Gif> recupererGifs() {
        return gifRepository.findAll();
    }

    @ApiOperation(value = "Récupère un gif par son id")
    @GetMapping("/{id}")
    public Gif getGifById(@PathVariable Long id) {
        return gifRepository.findById(id).orElse(null);
    }

    @ApiOperation(value = "Crée un gif")
    @PostMapping
    public Gif creerGif(Gif gif) {
        return gifRepository.save(gif);
    }

    @ApiOperation(value = "Supprime un gif")
    @PostMapping("/delete")
    public void supprimerGif(Long id) {
        gifRepository.deleteById(id);
    }

    @ApiOperation(value = "Met à jour un gif par son id")
    @PutMapping("/{id}")
    public Gif updateGif(@PathVariable Long id, @RequestBody Gif gifDetails) {
        Gif gif = gifRepository.findById(id).orElse(null);
        if (gif != null) {
            gif.setUrl(gifDetails.getUrl());
            gif.setJour(gifDetails.getJour());
            gif.setUtilisateur(gifDetails.getUtilisateur());
            return gifRepository.save(gif);
        }
        return null;
    }
}
