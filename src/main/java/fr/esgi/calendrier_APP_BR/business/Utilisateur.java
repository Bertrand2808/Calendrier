package fr.esgi.calendrier_APP_BR.business;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@EqualsAndHashCode
@ToString
public class Utilisateur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nom;

    @NotBlank
    private String prenom;

    @Email
    @Pattern(regexp=".+@esgi\\.fr", message="L'email doit appartenir au domaine esgi.fr")
    private String email;

    @NotBlank
    @Size(min = 3, message = "Le mot de passe doit contenir au moins 3 caractères")
    private String motDePasse;

    private int soldePoints;
}
