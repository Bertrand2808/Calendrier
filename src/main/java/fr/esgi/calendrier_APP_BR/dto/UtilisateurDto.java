package fr.esgi.calendrier_APP_BR.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UtilisateurDto {

    @NotNull
    @NotEmpty
    private String nom;

    @NotNull
    @NotEmpty
    private String prenom;

    @NotNull
    @NotEmpty
    private String email;

    @NotNull
    @NotEmpty
    private String motDePasse;

    @NotNull
    @NotEmpty
    private String theme;

}
