package fr.esgi.calendrier_APP_BR.business;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@EqualsAndHashCode
@ToString
public class JourCalendrier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.DATE)
    private Date date;

    @OneToMany(mappedBy = "jour")
    private List<Gif> gifs;

    public JourCalendrier(LocalDate localDate) {
        this.date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }
}
