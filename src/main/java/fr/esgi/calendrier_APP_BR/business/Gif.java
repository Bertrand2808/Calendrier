package fr.esgi.calendrier_APP_BR.business;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.validator.constraints.URL;
import jakarta.validation.constraints.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@EqualsAndHashCode
@ToString
public class Gif {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "utilisateur_id")
    private Utilisateur utilisateur;

    @ManyToOne
    @JoinColumn(name = "jour_id")
    private JourCalendrier jour;

    @NotBlank
    @URL(regexp = "(https?|ftp)://.*\\.(gif|GIF)")
    private String url;
}

