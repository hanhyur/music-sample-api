package me.gracenam.musicsampleapi.domain.albums.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.gracenam.musicsampleapi.domain.albums.dto.request.AlbumRequest;
import me.gracenam.musicsampleapi.domain.albums.dto.request.AlbumUpdateRequest;
import me.gracenam.musicsampleapi.domain.albums.dto.response.AlbumResponse;
import me.gracenam.musicsampleapi.domain.artists.dto.request.ArtistRequest;
import me.gracenam.musicsampleapi.domain.artists.dto.response.ArtistResponse;
import me.gracenam.musicsampleapi.domain.artists.entity.Artist;
import me.gracenam.musicsampleapi.domain.artists.mapper.ArtistMapper;
import me.gracenam.musicsampleapi.domain.soundtrack.dto.request.SoundtrackRequest;
import me.gracenam.musicsampleapi.domain.soundtrack.dto.request.SoundtrackUpdateRequest;
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
import java.util.stream.IntStream;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
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

    @Test
    @DisplayName("등록 시 잘못된 값을 입력했을 때")
    public void saveAlbumDataTest_Empty() throws Exception {
        AlbumRequest request = AlbumRequest.builder()
                .artistName("")
                .title("Love poem")
                .releaseDate(LocalDate.of(2019, 11, 18))
                .genre("K-pop")
                .description("‘사랑시’라고 지어 놓고도 하나도 부끄럽지 않은 이유는 여기 담은 것들이 전부 진심이기 때문이다.")
                .build();

        mockMvc.perform(post("/album")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("목록 조회")
    public void findAllTest() throws Exception {
        IntStream.range(0, 100).forEach(this::generateAlbum);

        mockMvc.perform(get("/album")
                        .param("pageNum", "1")
                        .param("pageSize", "2"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("page.total").value(100))
        ;
    }

    @Test
    @DisplayName("상세 조회")
    public void findAlbumTest() throws Exception {
        IntStream.range(0, 100).forEach(this::generateAlbum);

        mockMvc.perform(get("/album/{id}", 20))
                .andDo(print())
                .andExpect(status().isOk())
        ;
    }

    @Test
    @DisplayName("수정 성공")
    public void updateAlbumInfoTest() throws Exception {
        AlbumResponse album = generateAlbum(200);

        AlbumUpdateRequest dto = AlbumUpdateRequest.builder()
                .artistName("뉴진스")
                .title("NewJeans")
                .releaseDate(LocalDate.of(2022, 07, 20))
                .genre("K-pop")
                .description("데뷔앨범")
                .build();

        List<SoundtrackUpdateRequest> soundtrack = generateSoundtrack(1L);

        dto.setStUpdateRequests(soundtrack);

        mockMvc.perform(patch("/album/{id}", album.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("수정하려는 앨범이 존재하지 않을 때")
    public void updateAlbumInfoTest_NotFound() throws Exception {
        mockMvc.perform(patch("/album/100")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
        ;
    }


    @Test
    @DisplayName("삭제 성공")
    public void deleteAlbumTest() throws Exception {
        AlbumResponse album = generateAlbum(1);

        mockMvc.perform(delete("/album/{id}", album.getId()))
                .andDo(print())
                .andExpect(status().isOk())
        ;
    }

    @Test
    @DisplayName("삭제하려는 앨범이 존재하지 않을 때")
    public void deleteAlbumTest_BadRequest() throws Exception {
        mockMvc.perform(delete("/album/200"))
                .andDo(print())
                .andExpect(status().isBadRequest())
        ;
    }

    private AlbumResponse generateAlbum(int index) {
        AlbumRequest albumRequest = buildArtist(index);

        return albumSoundtrackAdapter.saveInfo(albumRequest);
    }

    private static AlbumRequest buildArtist(int index) {
        AlbumRequest album = AlbumRequest.builder()
                .artistName("테스터")
                .title("테스트 앨범" + index)
                .releaseDate(LocalDate.of(2019, 11, 18))
                .genre("K-pop")
                .description("테스트용 앨범")
                .build();

        return album;
    }

    private List<SoundtrackUpdateRequest> generateSoundtrack(Long albumId) {
        List<SoundtrackUpdateRequest> list = new ArrayList<>();

        for (int i = 1; i <= 5; i++) {
            SoundtrackUpdateRequest stRequest = SoundtrackUpdateRequest.builder()
                    .orders(i)
                    .title("음원" + i)
                    .playTime("3:38")
                    .exposure(true)
                    .albumId(albumId)
                    .build();

            list.add(stRequest);
        }

        return list;
    }
}