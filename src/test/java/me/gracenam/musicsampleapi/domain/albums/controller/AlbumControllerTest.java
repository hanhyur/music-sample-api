package me.gracenam.musicsampleapi.domain.albums.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.gracenam.musicsampleapi.domain.albums.dto.request.AlbumRequest;
import me.gracenam.musicsampleapi.domain.albums.dto.response.AlbumResponse;
import me.gracenam.musicsampleapi.domain.artists.dto.request.ArtistRequest;
import me.gracenam.musicsampleapi.domain.artists.entity.Artist;
import me.gracenam.musicsampleapi.domain.artists.mapper.ArtistMapper;
import me.gracenam.musicsampleapi.domain.soundtrack.dto.request.SoundtrackRequest;
import me.gracenam.musicsampleapi.global.Adapter.AlbumSoundtrackAdapter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Sql("/truncate.sql")
class AlbumControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    AlbumSoundtrackAdapter albumSoundtrackAdapter;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    @DisplayName("앨범 등록 테스트")
    public void createAlbumTest() throws Exception {
        AlbumRequest request = AlbumRequest.builder()
                .artistName("아이유")
                .title("Love poem")
                .releaseDate(LocalDate.of(2019, 11, 18))
                .genre("K-pop")
                .description("‘사랑시’라고 지어 놓고도 하나도 부끄럽지 않은 이유는 여기 담은 것들이 전부 진심이기 때문이다.")
                .build();

        SoundtrackRequest stRequest = SoundtrackRequest.builder()
                .orders(3)
                .title("Blueming")
                .playTime("3:38")
                .exposure(true)
                .build();

        List<SoundtrackRequest> soundtrackRequestList = new ArrayList<>();
        soundtrackRequestList.add(stRequest);

        mockMvc.perform(post("/album")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").exists())
        ;
    }

}