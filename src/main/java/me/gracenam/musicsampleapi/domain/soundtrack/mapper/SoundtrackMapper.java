package me.gracenam.musicsampleapi.domain.soundtrack.mapper;

import me.gracenam.musicsampleapi.domain.artists.entity.Artist;
import me.gracenam.musicsampleapi.domain.soundtrack.dto.response.SoundtrackResponse;
import me.gracenam.musicsampleapi.domain.soundtrack.entity.Soundtrack;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface SoundtrackMapper {

    Optional<Soundtrack> findById(Long id);

    int save(List<Soundtrack> soundtracks);

    List<SoundtrackResponse> findByAlbumId(Long id);

    void delete(List<Soundtrack> soundtracks);

}
