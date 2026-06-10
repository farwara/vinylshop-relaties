package nl.novi.vinylshop.mappers;

import nl.novi.vinylshop.dtos.StockRequestDTO;
import nl.novi.vinylshop.entities.StockEntity;
import org.springframework.stereotype.Component;

@Component
public class StockEntityMapper {

    public StockEntity mapToEntity(StockRequestDTO dto) {
        StockEntity entity = new StockEntity();
        entity.setCondition(dto.getCondition());
        entity.setPrice(dto.getPrice());

        return entity;
    }

    public void updateEntity(StockEntity entity, StockRequestDTO dto) {
        entity.setCondition(dto.getCondition());
        entity.setPrice(dto.getPrice());
    }
}