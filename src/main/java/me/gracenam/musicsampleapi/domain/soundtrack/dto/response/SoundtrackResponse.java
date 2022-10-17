package me.gracenam.musicsampleapi.domain.soundtrack.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
public class SoundtrackResponse {

    private Long id;

    private int orders;

    private String title;

    private String playTime;

    private boolean exposure;

    private Long albumId;

}
