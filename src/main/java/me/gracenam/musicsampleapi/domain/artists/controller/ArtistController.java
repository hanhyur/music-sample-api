package me.gracenam.musicsampleapi.domain.artists.controller;

import lombok.RequiredArgsConstructor;
import me.gracenam.musicsampleapi.domain.artists.dto.request.ArtistRequest;
import me.gracenam.musicsampleapi.domain.artists.dto.response.ArtistResponse;
import me.gracenam.musicsampleapi.domain.artists.dto.response.ArtistSearchParam;
import me.gracenam.musicsampleapi.domain.artists.exception.ArtistValidationException;
import me.gracenam.musicsampleapi.domain.artists.service.ArtistService;
import me.gracenam.musicsampleapi.global.commons.PageResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/artist")
public class ArtistController {

    private final ArtistService artistService;

    @GetMapping
    public ResponseEntity getArtists(ArtistSearchParam pageable) {
        PageResponse<ArtistResponse> result = artistService.findAllArtist(pageable);

        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity getArtist(@PathVariable Long id) {
        return ResponseEntity.ok(artistService.findArtistById(id));
    }
    
    @PostMapping
    public ResponseEntity saveArtist(@RequestBody @Valid ArtistRequest dto,
                                     BindingResult result) {
        if (result.hasErrors()) {
            throw new ArtistValidationException(result);
        }

        return ResponseEntity.ok(artistService.saveArtistData(dto));
    }

    @PatchMapping("/{id}")
    public ResponseEntity updateArtistInfo(@PathVariable Long id,
                                           @RequestBody @Valid ArtistRequest dto,
                                           BindingResult result) {
        if (result.hasErrors()) {
            throw new ArtistValidationException(result);
        }

        return ResponseEntity.ok(artistService.updateArtistInfo(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteArtistInfo(@PathVariable Long id) {
        artistService.deleteArtistInfo(id);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
