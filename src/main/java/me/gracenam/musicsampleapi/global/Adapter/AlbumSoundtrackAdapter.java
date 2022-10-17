package me.gracenam.musicsampleapi.global.Adapter;

import lombok.extern.slf4j.Slf4j;
import me.gracenam.musicsampleapi.domain.albums.dto.request.AlbumRequest;
import me.gracenam.musicsampleapi.domain.albums.dto.request.AlbumUpdateRequest;
import me.gracenam.musicsampleapi.domain.albums.dto.response.AlbumDetailResponse;
import me.gracenam.musicsampleapi.domain.albums.dto.response.AlbumResponse;
import me.gracenam.musicsampleapi.domain.albums.service.AlbumService;
import me.gracenam.musicsampleapi.domain.soundtrack.dto.request.SoundtrackRequest;
import me.gracenam.musicsampleapi.domain.soundtrack.dto.request.SoundtrackUpdateRequest;
import me.gracenam.musicsampleapi.domain.soundtrack.dto.response.SoundtrackResponse;
import me.gracenam.musicsampleapi.domain.soundtrack.service.SoundtrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class AlbumSoundtrackAdapter {

    @Autowired
    private AlbumService albumService;

    @Autowired
    private SoundtrackService soundtrackService;


    public AlbumResponse saveInfo(AlbumRequest albumReq) {
        log.info("albumReq = {}", albumReq);

        List<SoundtrackRequest> soundtrackReq = albumReq.getSoundtrackRequests();

        AlbumResponse albumResponse = albumService.saveAlbum(albumReq);

        log.info("album id = {}", albumResponse.getId());

        if (soundtrackReq != null && soundtrackReq.size() > 0) {
            soundtrackService.saveSoundtrack(albumResponse.getId(), soundtrackReq);
        }

        return albumResponse;
    }

    public AlbumDetailResponse findAlbumInfo(Long id) {
        AlbumDetailResponse albumDetailResponse = albumService.findAlbumById(id);

        albumDetailResponse.setSoundtrackList(soundtrackService.findSoundtracksByAlbumId(id));

        log.info("album detail = {}", albumDetailResponse);

        return albumDetailResponse;
    }

    public AlbumDetailResponse updateAlbumInfo(Long id,
                                               AlbumUpdateRequest albumReq) {
        AlbumDetailResponse albumDetailResponse = albumService.updateAlbumInfo(id, albumReq);

        albumDetailResponse.setSoundtrackList(soundtrackService.updateSoundtrack(id, albumReq.getStUpdateRequests()));

        return albumDetailResponse;
    }

    public void deleteAlbumInfo(Long id) {
        List<SoundtrackResponse> deleteList = soundtrackService.findSoundtracksByAlbumId(id);

        soundtrackService.deleteSoundtrack(id);

        albumService.deleteAlbum(id);
    }
}
