package nl.novi.vinylshop.mappers;

import nl.novi.vinylshop.dtos.GenreResponseDTO;
import nl.novi.vinylshop.entities.GenreEntity;
import org.springframework.stereotype.Component;

@Component
public class GenreDTOMapper {

    public GenreResponseDTO mapToDto(GenreEntity entity) {
        if (entity == null) {
            return null;
        }

        GenreResponseDTO dto = new GenreResponseDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());

        return dto;
    }
}