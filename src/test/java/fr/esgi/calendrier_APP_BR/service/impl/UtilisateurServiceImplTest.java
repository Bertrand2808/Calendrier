package fr.esgi.calendrier_APP_BR.service.impl;

import fr.esgi.calendrier_APP_BR.business.Utilisateur;
import fr.esgi.calendrier_APP_BR.repository.UtilisateurRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UtilisateurServiceImplTest {

    @Mock
    private UtilisateurRepository utilisateurRepository;

    @InjectMocks
    private UtilisateurServiceImpl utilisateurService;

    @Test
    public void loadUserByUsername() {
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setEmail("test@test.com");
        when(utilisateurRepository.findByEmail("test@test.com")).thenReturn(utilisateur);

        UserDetails result = utilisateurService.loadUserByUsername("test@test.com");

        assertEquals(utilisateur, result);
    }

    @Test
    public void loadUserByUsername_NotFound() {
        when(utilisateurRepository.findByEmail("test@test.com")).thenReturn(null);

        assertThrows(UsernameNotFoundException.class, () -> utilisateurService.loadUserByUsername("test@test.com"));
    }

    @Test
    public void save() {
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setId(1L);
        utilisateur.setMotDePasse("password"); // Set a password
        when(utilisateurRepository.save(utilisateur)).thenReturn(utilisateur);

        utilisateurService.save(utilisateur);

        assertEquals(500, utilisateur.getSoldePoints());
    }

    @Test
    public void getByEmail() {
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setEmail("test@test.com");
        when(utilisateurRepository.findByEmail("test@test.com")).thenReturn(utilisateur);

        Utilisateur result = utilisateurService.findByEmail("test@test.com");

        assertEquals(utilisateur, result);
    }
}