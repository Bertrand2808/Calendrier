package fr.esgi.calendrier_APP_BR.service.impl;

import fr.esgi.calendrier_APP_BR.business.Gif;
import fr.esgi.calendrier_APP_BR.repository.GifRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GifServiceImplTest {

    @Mock
    private GifRepository gifRepository;

    @InjectMocks
    private GifServiceImpl gifService;

    @Test
    public void getByIdTest() {
        Gif gif = new Gif();
        gif.setId(1L);
        when(gifRepository.findById(1L)).thenReturn(Optional.of(gif));

        Gif result = gifService.getById(1L);

        assertEquals(gif, result);
    }

    @Test
    public void saveTest() {
        Gif gif = new Gif();
        gif.setId(1L);
        when(gifRepository.save(gif)).thenReturn(gif);

        Gif result = gifService.save(gif);

        assertEquals(gif, result);
    }
}