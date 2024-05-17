package fr.esgi.calendrier_APP_BR.service;

import fr.esgi.calendrier_APP_BR.business.Utilisateur;
import fr.esgi.calendrier_APP_BR.repository.UtilisateurRepository;
import fr.esgi.calendrier_APP_BR.service.impl.UtilisateurServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UtilisateurServiceTest {

    @Mock
    private UtilisateurRepository utilisateurRepository;

    @InjectMocks
    private UtilisateurServiceImpl utilisateurService;

    @Test
    public void getById() {
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setId(1L);
        when(utilisateurRepository.findById(1L)).thenReturn(Optional.of(utilisateur));

        Utilisateur result = utilisateurService.getById(1L);

        assertEquals(utilisateur, result);
    }

    @Test
    public void getByIdNotFound() {
        when(utilisateurRepository.findById(1L)).thenReturn(Optional.empty());

        Utilisateur result = utilisateurService.getById(1L);

        assertNull(result);
    }

    @Test
    public void getByEmail() {
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setEmail("test@test.com");
        when(utilisateurRepository.findByEmail("test@test.com")).thenReturn(Optional.of(utilisateur));

        Utilisateur result = utilisateurService.getByEmail("test@test.com");

        assertEquals(utilisateur, result);
    }

    @Test
    public void getByEmailNotFound() {
        when(utilisateurRepository.findByEmail("test@test.com")).thenReturn(Optional.empty());

        Utilisateur result = utilisateurService.getByEmail("test@test.com");

        assertNull(result);
    }

    @Test
    public void save() {
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setId(1L);
        when(utilisateurRepository.save(utilisateur)).thenReturn(utilisateur);

        Utilisateur result = utilisateurService.save(utilisateur);

        assertEquals(utilisateur, result);
    }
}
