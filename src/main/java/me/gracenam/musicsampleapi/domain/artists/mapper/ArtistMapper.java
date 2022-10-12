package me.gracenam.musicsampleapi.domain.artists.mapper;

import me.gracenam.musicsampleapi.domain.artists.dto.request.ArtistRequest;
import me.gracenam.musicsampleapi.domain.artists.dto.response.ArtistResponse;
import me.gracenam.musicsampleapi.domain.artists.entity.Artist;
import me.gracenam.musicsampleapi.global.commons.SearchParam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface ArtistMapper {

    Optional<Artist> findById(Long id);

    List<ArtistResponse> findAll(SearchParam param);

    Long save(Artist artist);

    List<Artist> search(String name);

    int update(@Param("id") Long id, @Param("dto") ArtistRequest dto);

    void delete(Long id);
}
