package me.gracenam.musicsampleapi.domain.albums.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class AlbumResponse {

    private Long id;

    private String name;

}
