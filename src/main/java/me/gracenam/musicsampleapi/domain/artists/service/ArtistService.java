package me.gracenam.musicsampleapi.domain.artists.service;

import lombok.RequiredArgsConstructor;
import me.gracenam.musicsampleapi.domain.artists.dto.request.ArtistRequest;
import me.gracenam.musicsampleapi.domain.artists.dto.response.ArtistResponse;
import me.gracenam.musicsampleapi.domain.artists.entity.Artist;
import me.gracenam.musicsampleapi.domain.artists.mapper.ArtistMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ArtistService {

    private final ArtistMapper artistMapper;

    public ArtistResponse findArtistById(Long id) {
        Optional<Artist> artist = artistMapper.findById(id);

        if (artist.isPresent()) {
            ArtistResponse artistResponse = ArtistResponse.builder()
                    .artistId(artist.get().getArtistId())
                    .artistName(artist.get().getArtistName())
                    .birth(artist.get().getBirth())
                    .agency(artist.get().getAgency())
                    .nationality(artist.get().getNationality())
                    .introduction(artist.get().getIntroduction())
                    .registeredDate(artist.get().getRegisteredDate())
                    .build();

            return artistResponse;
        } else {
            return null;
        }
    }

    public List<ArtistResponse> findAllArtist(Pageable pageable) {
        List<Artist> artistList = artistMapper.findAll();

        List<ArtistResponse> artistResponseList = new ArrayList<>();

        for (Artist artist : artistList) {
            ArtistResponse artistResponse = ArtistResponse.builder()
                    .artistId(artist.getArtistId())
                    .artistName(artist.getArtistName())
                    .birth(artist.getBirth())
                    .agency(artist.getAgency())
                    .nationality(artist.getNationality())
                    .introduction(artist.getIntroduction())
                    .registeredDate(artist.getRegisteredDate())
                    .build();

            artistResponseList.add(artistResponse);
        }

        return artistResponseList;
    }

    @Transactional
    public Artist saveArtistData(ArtistRequest dto) {
        Long id = artistMapper.save(dto);

        Optional<Artist> artist = artistMapper.findById(id);

        return artist.orElse(null);
    }

    public List<ArtistResponse> searchArtistByName(Pageable pageable, String name) {
        List<Artist> artistList = artistMapper.search(name);

        List<ArtistResponse> searchResponseList = new ArrayList<>();

        for (Artist artist : artistList) {
            ArtistResponse artistResponse = ArtistResponse.builder()
                    .artistId(artist.getArtistId())
                    .artistName(artist.getArtistName())
                    .birth(artist.getBirth())
                    .agency(artist.getAgency())
                    .nationality(artist.getNationality())
                    .introduction(artist.getIntroduction())
                    .registeredDate(artist.getRegisteredDate())
                    .build();

            searchResponseList.add(artistResponse);
        }

        return searchResponseList;
    }

}
