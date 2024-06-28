package fr.esgi.calendrier_APP_BR.business.customId;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReactionJourId implements Serializable {
    @Column(name = "jour_calendrier_id")
    private JourCalendrierId jourCalendrierId;
    @Column(name = "utilisateur_id")
    private Long utilisateurId;

    // Override equals and hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReactionJourId reactionJourId = (ReactionJourId) o;
        return jourCalendrierId.equals(reactionJourId.jourCalendrierId) && utilisateurId.equals(reactionJourId.utilisateurId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(jourCalendrierId, utilisateurId);
    }
}
