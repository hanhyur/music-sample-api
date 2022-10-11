package me.gracenam.musicsampleapi.domain.artists.mapper;

import me.gracenam.musicsampleapi.domain.artists.dto.request.ArtistRequest;
import me.gracenam.musicsampleapi.domain.artists.entity.Artist;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface ArtistMapper {

    Optional<Artist> findById(Long id);

    List<Artist> findAll();

    Long save(@Param("dto") ArtistRequest dto);

    List<Artist> search(String name);

}
