package fr.esgi.calendrier_APP_BR.business;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UtilisateurTest {

    @Test
    void getId() {
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setId(1L);
        assertEquals(1L, utilisateur.getId());
    }

    @Test
    void getNom() {
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setNom("Dupont");
        assertEquals("Dupont", utilisateur.getNom());
    }

    @Test
    void getPrenom() {
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setPrenom("Jean");
        assertEquals("Jean", utilisateur.getPrenom());
    }

    @Test
    void getEmail() {
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setEmail("jean@esgi.fr");
        assertEquals("jean@esgi.fr", utilisateur.getEmail());
    }

    @Test
    void getMotDePasse() {
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setMotDePasse("password");
        assertEquals("password", utilisateur.getMotDePasse());
    }

    @Test
    void getSoldePoints() {
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setSoldePoints(100);
        assertEquals(100, utilisateur.getSoldePoints());
    }

    @Test
    void setId() {
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setId(2L);
        assertEquals(2L, utilisateur.getId());
    }

    @Test
    void setNom() {
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setNom("Durand");
        assertEquals("Durand", utilisateur.getNom());
    }

    @Test
    void setPrenom() {
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setPrenom("Pierre");
        assertEquals("Pierre", utilisateur.getPrenom());
    }

    @Test
    void setEmail() {
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setEmail("pierre@esgi.fr");
        assertEquals("pierre@esgi.fr", utilisateur.getEmail());
    }

    @Test
    void setMotDePasse() {
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setMotDePasse("password2");
        assertEquals("password2", utilisateur.getMotDePasse());
    }

    @Test
    void setSoldePoints() {
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setSoldePoints(200);
        assertEquals(200, utilisateur.getSoldePoints());
    }
}