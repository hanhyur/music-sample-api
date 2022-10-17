package me.gracenam.musicsampleapi.domain.soundtrack.entity;

import lombok.*;

@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Soundtrack {

    private Long id;

    private int orders;

    private String title;

    private String playTime;

    private boolean exposure;

    private Long albumId;

}
