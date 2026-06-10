package nl.novi.vinylshop.controllers;

import jakarta.validation.Valid;
import nl.novi.vinylshop.dtos.PublisherRequestDTO;
import nl.novi.vinylshop.dtos.PublisherResponseDTO;
import nl.novi.vinylshop.services.PublisherService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/publishers")
public class PublisherController {

    private final PublisherService publisherService;

    public PublisherController(PublisherService publisherService) {
        this.publisherService = publisherService;
    }

    @GetMapping
    public ResponseEntity<List<PublisherResponseDTO>> getAllPublishers() {
        return ResponseEntity.ok(publisherService.getAllPublishers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PublisherResponseDTO> getPublisherById(@PathVariable Long id) {
        return ResponseEntity.ok(publisherService.getPublisherById(id));
    }

    @PostMapping
    public ResponseEntity<PublisherResponseDTO> createPublisher(
            @Valid @RequestBody PublisherRequestDTO dto) {

        return ResponseEntity.ok(publisherService.createPublisher(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PublisherResponseDTO> updatePublisher(
            @PathVariable Long id,
            @Valid @RequestBody PublisherRequestDTO dto) {

        return ResponseEntity.ok(publisherService.updatePublisher(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePublisher(@PathVariable Long id) {
        publisherService.deletePublisher(id);
        return ResponseEntity.noContent().build();
    }
}