package nl.novi.vinylshop.mappers;

import nl.novi.vinylshop.dtos.GenreRequestDTO;
import nl.novi.vinylshop.entities.GenreEntity;
import org.springframework.stereotype.Component;

@Component
public class GenreEntityMapper {

    public GenreEntity mapToEntity(GenreRequestDTO dto) {
        GenreEntity entity = new GenreEntity();
        entity.setName(dto.getName());

        return entity;
    }

    public void updateEntity(GenreEntity entity, GenreRequestDTO dto) {
        entity.setName(dto.getName());
    }
}