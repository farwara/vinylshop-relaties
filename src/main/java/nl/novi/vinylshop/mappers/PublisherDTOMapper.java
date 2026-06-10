package nl.novi.vinylshop.mappers;

import nl.novi.vinylshop.dtos.PublisherResponseDTO;
import nl.novi.vinylshop.entities.PublisherEntity;
import org.springframework.stereotype.Component;

@Component
public class PublisherDTOMapper {

    public PublisherResponseDTO mapToDto(PublisherEntity entity) {
        if (entity == null) {
            return null;
        }

        PublisherResponseDTO dto = new PublisherResponseDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());

        return dto;
    }
}
