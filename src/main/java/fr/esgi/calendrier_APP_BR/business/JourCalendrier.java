package fr.esgi.calendrier_APP_BR.business;

import fr.esgi.calendrier_APP_BR.business.customId.JourCalendrierId;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;

import java.util.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JourCalendrier {
    @EmbeddedId
    private JourCalendrierId id;

    public String date() {
        String jour = String.valueOf(id.getJour());
        String mois = String.valueOf(id.getMois());

        if (jour.length() == 1) {
            jour = "0" + jour;
        }
        if (mois.length() == 1) {
            mois = "0" + mois;
        }

        return jour + "/" + mois;
    }

    public static JourCalendrierId fromDate(String date) {
        String[] parts = date.split("/");
        int jour = Integer.parseInt(parts[0]);
        int mois = Integer.parseInt(parts[1]);
        return new JourCalendrierId(jour, mois);
    }

    @OneToOne()
    @Nullable()
    @JoinColumn(name = "gif_id")
    private Gif gif;

    @ManyToOne()
    @Nullable()
    @JoinColumn(name = "utilisateur_id")
    private Utilisateur utilisateur;

    @OneToMany(mappedBy = "jourCalendrier", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reaction> reactions = new ArrayList<>();

    private int points;

    @PostLoad
    @PostPersist
    @PostUpdate
    private void initPoints() {
        if (points == 0) {
            Random random = new Random();
            this.points = random.nextInt(50) + 1;
        }
    }
}
