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
    @DisplayName("?????? ?????? ?????????")
    public void createAlbumTest() throws Exception {
        AlbumRequest request = AlbumRequest.builder()
                .artistName("?????????")
                .title("Love poem")
                .releaseDate(LocalDate.of(2019, 11, 18))
                .genre("K-pop")
                .description("????????????????????? ?????? ????????? ????????? ???????????? ?????? ????????? ?????? ?????? ????????? ?????? ???????????? ????????????.")
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
    @DisplayName("?????? ??? ????????? ?????? ???????????? ???")
    public void saveAlbumDataTest_Empty() throws Exception {
        AlbumRequest request = AlbumRequest.builder()
                .artistName("")
                .title("Love poem")
                .releaseDate(LocalDate.of(2019, 11, 18))
                .genre("K-pop")
                .description("????????????????????? ?????? ????????? ????????? ???????????? ?????? ????????? ?????? ?????? ????????? ?????? ???????????? ????????????.")
                .build();

        mockMvc.perform(post("/album")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("?????? ??????")
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
    @DisplayName("?????? ??????")
    public void findAlbumTest() throws Exception {
        IntStream.range(0, 100).forEach(this::generateAlbum);

        mockMvc.perform(get("/album/{id}", 20))
                .andDo(print())
                .andExpect(status().isOk())
        ;
    }

    @Test
    @DisplayName("?????? ??????")
    public void updateAlbumInfoTest() throws Exception {
        AlbumResponse album = generateAlbum(200);

        AlbumUpdateRequest dto = AlbumUpdateRequest.builder()
                .artistName("?????????")
                .title("NewJeans")
                .releaseDate(LocalDate.of(2022, 07, 20))
                .genre("K-pop")
                .description("????????????")
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
    @DisplayName("??????????????? ????????? ???????????? ?????? ???")
    public void updateAlbumInfoTest_NotFound() throws Exception {
        mockMvc.perform(patch("/album/100")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
        ;
    }


    @Test
    @DisplayName("?????? ??????")
    public void deleteAlbumTest() throws Exception {
        AlbumResponse album = generateAlbum(1);

        mockMvc.perform(delete("/album/{id}", album.getId()))
                .andDo(print())
                .andExpect(status().isOk())
        ;
    }

    @Test
    @DisplayName("??????????????? ????????? ???????????? ?????? ???")
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
                .artistName("?????????")
                .title("????????? ??????" + index)
                .releaseDate(LocalDate.of(2019, 11, 18))
                .genre("K-pop")
                .description("???????????? ??????")
                .build();

        return album;
    }

    private List<SoundtrackUpdateRequest> generateSoundtrack(Long albumId) {
        List<SoundtrackUpdateRequest> list = new ArrayList<>();

        for (int i = 1; i <= 5; i++) {
            SoundtrackUpdateRequest stRequest = SoundtrackUpdateRequest.builder()
                    .orders(i)
                    .title("??????" + i)
                    .playTime("3:38")
                    .exposure(true)
                    .albumId(albumId)
                    .build();

            list.add(stRequest);
        }

        return list;
    }
}