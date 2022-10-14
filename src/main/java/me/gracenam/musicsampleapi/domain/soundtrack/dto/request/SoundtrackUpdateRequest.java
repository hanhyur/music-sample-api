package me.gracenam.musicsampleapi.domain.soundtrack.dto.request;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SoundtrackUpdateRequest {

    private Long id;

    private int orders;

    @NotBlank(message = "음원명을 입력해주세요")
    private String title;

    @NotBlank(message = "재생시간을 입력해주세요")
    private Long playTime;

    private boolean exposure;
}
