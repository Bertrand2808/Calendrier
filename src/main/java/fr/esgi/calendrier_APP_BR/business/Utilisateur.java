package fr.esgi.calendrier_APP_BR.business;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Utilisateur implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @NotBlank
    protected String nom;

    @NotBlank
    protected String prenom;

    @Email
    @NotNull
    @Pattern(regexp=".+@esgi\\.fr", message="L'email doit appartenir au domaine esgi.fr")
    @Column(name = "email", unique = true)
    protected String email;

    @NotBlank
    @Size(min = 3, message = "Le mot de passe doit contenir au moins 3 caractères")
    protected String motDePasse;

    protected int soldePoints = 500;

    protected String theme;

    @OneToMany(mappedBy = "utilisateur")
    private List<JourCalendrier> jours = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getPassword() {
        return this.motDePasse;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}