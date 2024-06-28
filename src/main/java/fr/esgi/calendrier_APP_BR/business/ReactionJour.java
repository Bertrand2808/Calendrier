package fr.esgi.calendrier_APP_BR.business;

import fr.esgi.calendrier_APP_BR.business.customId.ReactionJourId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "reaction_jour")
public class ReactionJour {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private ReactionJourId id;

    @OneToOne()
    @MapsId("utilisateurId")
    private Utilisateur utilisateur;

    @ManyToOne()
    private Reaction reaction;
}
