package me.gracenam.musicsampleapi.domain.soundtrack.dto.response;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SoundtrackResponse {

    private Long id;

    private int orders;

    private String title;

    private String playTime;

    private boolean exposure;

    private Long albumId;

}
