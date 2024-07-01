package fr.esgi.calendrier_APP_BR.business;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String unicode;

    @ManyToOne
    private Utilisateur utilisateur;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "jour", referencedColumnName = "jour"),
            @JoinColumn(name = "mois", referencedColumnName = "mois")
    })
    private JourCalendrier jourCalendrier;

}
