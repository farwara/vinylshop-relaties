package nl.novi.vinylshop.mappers;

import nl.novi.vinylshop.dtos.ArtistRequestDTO;
import nl.novi.vinylshop.entities.ArtistEntity;
import org.springframework.stereotype.Component;

@Component
public class ArtistEntityMapper {

    public ArtistEntity mapToEntity(ArtistRequestDTO dto) {
        ArtistEntity entity = new ArtistEntity();
        entity.setName(dto.getName());
        entity.setBiography(dto.getBiography());

        return entity;
    }

    public void updateEntity(ArtistEntity entity, ArtistRequestDTO dto) {
        entity.setName(dto.getName());
        entity.setBiography(dto.getBiography());
    }
}