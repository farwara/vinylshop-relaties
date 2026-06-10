package nl.novi.vinylshop.mappers;

import nl.novi.vinylshop.dtos.PublisherRequestDTO;
import nl.novi.vinylshop.entities.PublisherEntity;
import org.springframework.stereotype.Component;

@Component
public class PublisherEntityMapper {

    public PublisherEntity mapToEntity(PublisherRequestDTO dto) {
        PublisherEntity entity = new PublisherEntity();
        entity.setName(dto.getName());

        return entity;
    }

    public void updateEntity(PublisherEntity entity, PublisherRequestDTO dto) {
        entity.setName(dto.getName());
    }
}