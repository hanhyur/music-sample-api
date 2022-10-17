package me.gracenam.musicsampleapi.domain.albums.controller;

import lombok.RequiredArgsConstructor;
import me.gracenam.musicsampleapi.domain.albums.dto.request.AlbumRequest;
import me.gracenam.musicsampleapi.domain.albums.dto.request.AlbumUpdateRequest;
import me.gracenam.musicsampleapi.domain.albums.dto.response.AlbumResponse;
import me.gracenam.musicsampleapi.domain.albums.dto.response.AlbumSearchParam;
import me.gracenam.musicsampleapi.domain.albums.exception.AlbumValidationException;
import me.gracenam.musicsampleapi.domain.albums.service.AlbumService;
import me.gracenam.musicsampleapi.domain.soundtrack.dto.request.SoundtrackRequest;
import me.gracenam.musicsampleapi.domain.soundtrack.dto.request.SoundtrackUpdateRequest;
import me.gracenam.musicsampleapi.global.Adapter.AlbumSoundtrackAdapter;
import me.gracenam.musicsampleapi.global.commons.PageResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/album")
public class AlbumController {

    private final AlbumService albumService;

    private final AlbumSoundtrackAdapter albumSoundtrackAdapter;

    @GetMapping
    public ResponseEntity getAlbumsList(AlbumSearchParam pageable) {
        PageResponse<AlbumResponse> result = albumService.findAllAlbum(pageable);

        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity getAlbum(@PathVariable Long id) {
        return ResponseEntity.ok(albumSoundtrackAdapter.findAlbumInfo(id));
    }

    @PostMapping
    public ResponseEntity saveAlbum(@RequestBody @Valid AlbumRequest albumReq,
                                    BindingResult result) {
        if (result.hasErrors()) {
            throw new AlbumValidationException(result);
        }

        return ResponseEntity.ok(albumSoundtrackAdapter.saveInfo(albumReq));
    }

    @PatchMapping("/{id}")
    public ResponseEntity updateAlbum(@PathVariable Long id,
                                      @RequestBody @Valid AlbumUpdateRequest albumReq,
                                      BindingResult result) {
        if (result.hasErrors()) {
            throw new AlbumValidationException(result);
        }

        return ResponseEntity.ok(albumSoundtrackAdapter.updateAlbumInfo(id, albumReq));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteAlbum(@PathVariable Long id) {
        albumSoundtrackAdapter.deleteAlbumInfo(id);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
