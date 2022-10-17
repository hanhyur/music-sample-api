package me.gracenam.musicsampleapi.domain.soundtrack.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.gracenam.musicsampleapi.domain.soundtrack.dto.request.SoundtrackRequest;
import me.gracenam.musicsampleapi.domain.soundtrack.dto.request.SoundtrackUpdateRequest;
import me.gracenam.musicsampleapi.domain.soundtrack.dto.response.SoundtrackResponse;
import me.gracenam.musicsampleapi.domain.soundtrack.entity.Soundtrack;
import me.gracenam.musicsampleapi.domain.soundtrack.exception.SoundtrackNotFoundException;
import me.gracenam.musicsampleapi.domain.soundtrack.mapper.SoundtrackMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class SoundtrackService {

    @Autowired
    private SoundtrackMapper soundtrackMapper;

    @Autowired
    private ModelMapper modelMapper;

    @Transactional
    public void saveSoundtrack(Long albumId, List<SoundtrackRequest> dto) {
        List<Soundtrack> soundtracks = new ArrayList<>();

        for (SoundtrackRequest request : dto) {
            SoundtrackResponse soundtrackRes = modelMapper.map(request, SoundtrackResponse.class);
            soundtrackRes.setAlbumId(albumId);

            Soundtrack soundtrack = modelMapper.map(soundtrackRes, Soundtrack.class);

            log.info("soundtrack = {}", soundtrack);

            soundtracks.add(soundtrack);

        }

        soundtrackMapper.save(soundtracks);
    }

    public SoundtrackResponse findSoundtrackById(Long trackId) {
        Soundtrack soundtrack = soundtrackMapper.findById(trackId)
                .orElseThrow(() -> new SoundtrackNotFoundException(Long.toString(trackId)));

        SoundtrackResponse soundtrackResponse = SoundtrackResponse.builder()
                .id(soundtrack.getId())
                .orders(soundtrack.getOrders())
                .title(soundtrack.getTitle())
                .playTime(soundtrack.getPlayTime())
                .exposure(soundtrack.isExposure())
                .albumId(soundtrack.getId())
                .build();

        return soundtrackResponse;
    }

    public List<SoundtrackResponse> findSoundtracksByAlbumId(Long albumId) {
        List<SoundtrackResponse> responses = soundtrackMapper.findByAlbumId(albumId);

        log.info("res = {}", responses);

        return responses;
    }

    @Transactional
    public List<SoundtrackResponse> updateSoundtrack(Long albumId, List<SoundtrackUpdateRequest> req) {
        req.forEach(updateRequest -> updateRequest.setAlbumId(albumId));

        List<SoundtrackResponse> chkList = findSoundtracksByAlbumId(albumId);

        List<SoundtrackResponse> deleteList = chkList.stream()
                .filter(e -> req.stream().noneMatch(Predicate.isEqual(e)))
                .collect(Collectors.toList());
        soundtrackMapper.delete(deleteList);

        insertNewTrackStream(req);
        updateTrackStream(req);

        return findSoundtracksByAlbumId(albumId);
    }

    private void updateTrackStream(List<SoundtrackUpdateRequest> req) {
        List<SoundtrackUpdateRequest> updateList = req.stream()
                .filter(e -> req.stream().anyMatch(Predicate.isEqual(e)))
                .collect(Collectors.toList());

        soundtrackMapper.update(updateList);
    }

    private void insertNewTrackStream(List<SoundtrackUpdateRequest> req) {
        List<SoundtrackUpdateRequest> insertReq = req.stream().filter(e -> e.getId() == null)
                .collect(Collectors.toList());

        List<Soundtrack> insertList = insertReq.stream()
                .map(e -> {
                    Soundtrack soundtrack = modelMapper.map(e, Soundtrack.class);
                    return soundtrack;
                })
                .collect(Collectors.toList());

        soundtrackMapper.save(insertList);
    }

    @Transactional
    public void deleteSoundtrack(Long id) {
        List<SoundtrackResponse> deleteLists = findSoundtracksByAlbumId(id);

        soundtrackMapper.delete(deleteLists);
    }

}
