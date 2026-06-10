package nl.novi.vinylshop.mappers;

import nl.novi.vinylshop.dtos.ArtistResponseDTO;
import nl.novi.vinylshop.entities.ArtistEntity;
import org.springframework.stereotype.Component;

@Component
public class ArtistDTOMapper {

    public ArtistResponseDTO mapToDto(ArtistEntity entity) {
        if (entity == null) {
            return null;
        }

        ArtistResponseDTO dto = new ArtistResponseDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setBiography(entity.getBiography());

        return dto;
    }
}