package me.gracenam.musicsampleapi.domain.soundtrack.mapper;

import me.gracenam.musicsampleapi.domain.albums.dto.request.AlbumRequest;
import me.gracenam.musicsampleapi.domain.artists.entity.Artist;
import me.gracenam.musicsampleapi.domain.soundtrack.dto.request.SoundtrackUpdateRequest;
import me.gracenam.musicsampleapi.domain.soundtrack.dto.response.SoundtrackResponse;
import me.gracenam.musicsampleapi.domain.soundtrack.entity.Soundtrack;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface SoundtrackMapper {

    Optional<Soundtrack> findById(Long id);

    List<SoundtrackResponse> findByAlbumId(Long id);

    Long save(List<Soundtrack> soundtracks);

    void update(List<SoundtrackUpdateRequest> requests);

    void delete(List<SoundtrackResponse> deleteLists);

}
