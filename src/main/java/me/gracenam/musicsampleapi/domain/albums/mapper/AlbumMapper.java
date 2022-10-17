package me.gracenam.musicsampleapi.domain.albums.mapper;

import me.gracenam.musicsampleapi.domain.albums.dto.request.AlbumRequest;
import me.gracenam.musicsampleapi.domain.albums.dto.response.AlbumResponse;
import me.gracenam.musicsampleapi.domain.albums.entity.Album;
import me.gracenam.musicsampleapi.global.commons.SearchParam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface AlbumMapper {

    Optional<Album> findById(Long id);

    List<AlbumResponse> findAll(SearchParam param);

    Long save(Album album);

    void update(@Param("id") Long id, @Param("dto") AlbumRequest dto);

    void delete(Long id);

}
