package nl.novi.vinylshop.controllers;

import jakarta.validation.Valid;
import nl.novi.vinylshop.dtos.ArtistRequestDTO;
import nl.novi.vinylshop.dtos.ArtistResponseDTO;
import nl.novi.vinylshop.services.ArtistService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/artists")
public class ArtistController {

    private final ArtistService artistService;

    public ArtistController(ArtistService artistService) {
        this.artistService = artistService;
    }

    @GetMapping
    public ResponseEntity<List<ArtistResponseDTO>> getAllArtists() {
        return ResponseEntity.ok(artistService.getAllArtists());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArtistResponseDTO> getArtistById(@PathVariable Long id) {
        return ResponseEntity.ok(artistService.getArtistById(id));
    }

    @PostMapping
    public ResponseEntity<ArtistResponseDTO> createArtist(
            @Valid @RequestBody ArtistRequestDTO dto) {

        return ResponseEntity.ok(artistService.createArtist(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ArtistResponseDTO> updateArtist(
            @PathVariable Long id,
            @Valid @RequestBody ArtistRequestDTO dto) {

        return ResponseEntity.ok(artistService.updateArtist(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArtist(@PathVariable Long id) {
        artistService.deleteArtist(id);
        return ResponseEntity.noContent().build();
    }
}