package nl.novi.vinylshop.controllers;

import jakarta.validation.Valid;
import nl.novi.vinylshop.dtos.StockRequestDTO;
import nl.novi.vinylshop.dtos.StockResponseDTO;
import nl.novi.vinylshop.services.StockService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/albums/{albumId}/stock")
public class StockController {

    private final StockService stockService;

    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @GetMapping
    public ResponseEntity<List<StockResponseDTO>> getAllStockForAlbum(
            @PathVariable Long albumId) {

        return ResponseEntity.ok(stockService.getAllStockForAlbum(albumId));
    }

    @GetMapping("/{stockId}")
    public ResponseEntity<StockResponseDTO> getStockById(
            @PathVariable Long albumId,
            @PathVariable Long stockId) {

        return ResponseEntity.ok(stockService.getStockById(albumId, stockId));
    }

    @PostMapping
    public ResponseEntity<StockResponseDTO> createStock(
            @PathVariable Long albumId,
            @Valid @RequestBody StockRequestDTO dto) {

        return ResponseEntity.ok(stockService.createStock(albumId, dto));
    }

    @PutMapping("/{stockId}")
    public ResponseEntity<StockResponseDTO> updateStock(
            @PathVariable Long albumId,
            @PathVariable Long stockId,
            @Valid @RequestBody StockRequestDTO dto) {

        return ResponseEntity.ok(stockService.updateStock(albumId, stockId, dto));
    }

    @DeleteMapping("/{stockId}")
    public ResponseEntity<Void> deleteStock(
            @PathVariable Long albumId,
            @PathVariable Long stockId) {

        stockService.deleteStock(albumId, stockId);
        return ResponseEntity.noContent().build();
    }
}