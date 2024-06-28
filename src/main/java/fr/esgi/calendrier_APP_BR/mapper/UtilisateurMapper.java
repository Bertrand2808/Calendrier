package fr.esgi.calendrier_APP_BR.mapper;

import fr.esgi.calendrier_APP_BR.business.Utilisateur;
import fr.esgi.calendrier_APP_BR.dto.UtilisateurDto;
import org.mapstruct.*;

@Mapper(
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING
)
public interface UtilisateurMapper {
    Utilisateur toEntity(UtilisateurDto utilisateurDto);

    UtilisateurDto toDto(Utilisateur utilisateur);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Utilisateur partialUpdate(UtilisateurDto utilisateurDto,
                              @MappingTarget Utilisateur utilisateur);
}

