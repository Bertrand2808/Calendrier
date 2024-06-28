package fr.esgi.calendrier_APP_BR.business;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.validator.constraints.URL;
import jakarta.validation.constraints.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Gif {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Pattern(regexp = "^.+\\.(?i)(gif)$", message = "L'URL doit se terminer par .gif, .Gif ou .GIF")
    private String url;

    @ManyToOne
    private Utilisateur utilisateur;
}

