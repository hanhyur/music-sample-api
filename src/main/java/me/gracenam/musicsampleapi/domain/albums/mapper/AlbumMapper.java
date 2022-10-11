package me.gracenam.musicsampleapi.domain.albums.mapper;

import me.gracenam.musicsampleapi.domain.albums.entity.Album;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AlbumMapper {

    Album insertAlbumData();

}
