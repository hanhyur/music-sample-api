package me.gracenam.musicsampleapi.global.Adapter;

import me.gracenam.musicsampleapi.domain.albums.dto.request.AlbumRequest;
import me.gracenam.musicsampleapi.domain.albums.dto.response.AlbumDetailResponse;
import me.gracenam.musicsampleapi.domain.albums.dto.response.AlbumResponse;
import me.gracenam.musicsampleapi.domain.albums.service.AlbumService;
import me.gracenam.musicsampleapi.domain.soundtrack.dto.request.SoundtrackRequest;
import me.gracenam.musicsampleapi.domain.soundtrack.service.SoundtrackService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class AlbumSoundtrackAdapter {

    @Autowired
    private AlbumService albumService;

    @Autowired
    private SoundtrackService soundtrackService;


    public AlbumResponse saveInfo(AlbumRequest albumReq, List<SoundtrackRequest> soundtrackReq) {
        AlbumResponse albumResponse = albumService.saveAlbum(albumReq);

        if (soundtrackReq.size() > 0) {
            soundtrackService.saveSoundtrack(albumResponse.getId(), soundtrackReq);
        }

        return albumResponse;
    }

    public AlbumDetailResponse findAlbumInfo(Long id) {
        AlbumDetailResponse albumDetailResponse = albumService.findAlbumById(id);

        albumDetailResponse.setSoundtrackList(soundtrackService.findSoundtracksByAlbumId(id));

        return albumDetailResponse;
    }
}
