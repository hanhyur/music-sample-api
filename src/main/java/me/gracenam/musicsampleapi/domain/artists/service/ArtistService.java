package me.gracenam.musicsampleapi.domain.artists.service;

import lombok.RequiredArgsConstructor;
import me.gracenam.musicsampleapi.domain.artists.dto.request.ArtistRequest;
import me.gracenam.musicsampleapi.domain.artists.dto.response.ArtistResponse;
import me.gracenam.musicsampleapi.domain.artists.entity.Artist;
import me.gracenam.musicsampleapi.domain.artists.exception.ArtistNotFoundException;
import me.gracenam.musicsampleapi.domain.artists.mapper.ArtistMapper;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ArtistService {

    private final ArtistMapper artistMapper;

    private final ModelMapper modelMapper;

    public ArtistResponse findArtistById(Long id) {
        Artist findArtist = artistMapper.findById(id).orElseThrow();

        ArtistResponse artistResponse = ArtistResponse.builder().build();

        return artistResponse;
    }

    public List<ArtistResponse> findAllArtist(Pageable pageable) {
        List<Artist> artistList = artistMapper.findAll();

        List<ArtistResponse> artistResponseList = new ArrayList<>();

        for (Artist artist : artistList) {
            ArtistResponse artistResponse = ArtistResponse.builder()
                    .id(artist.getId())
                    .name(artist.getName())
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
    public ArtistResponse saveArtistData(ArtistRequest dto) {
        Artist artist = modelMapper.map(dto, Artist.class);

        System.out.println("artist = " + artist);

        Long id = artistMapper.save(artist);

        Artist result = artistMapper.findById(id).orElse(null);

        ArtistResponse artistResponse = ArtistResponse.builder()
                .id(result.getId())
                .name(result.getName())
                .birth(result.getBirth())
                .agency(result.getAgency())
                .nationality(result.getNationality())
                .introduction(result.getIntroduction())
                .registeredDate(result.getRegisteredDate())
                .build();

        return artistResponse;
    }

    public List<ArtistResponse> searchArtistByName(Pageable pageable, String name) {
        List<Artist> artistList = artistMapper.search(name);

        List<ArtistResponse> searchResponseList = new ArrayList<>();

        for (Artist artist : artistList) {
            ArtistResponse artistResponse = ArtistResponse.builder()
                    .id(artist.getId())
                    .name(artist.getName())
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

    @Transactional
    public ArtistResponse updateArtistInfo(Long id, ArtistRequest dto) {
        artistMapper.update(id, dto);

        Optional<Artist> result = artistMapper.findById(id);

        if (result.isPresent()) {
            ArtistResponse artistResponse = ArtistResponse.builder()
                    .id(result.get().getId())
                    .name(result.get().getName())
                    .birth(result.get().getBirth())
                    .agency(result.get().getAgency())
                    .nationality(result.get().getNationality())
                    .introduction(result.get().getIntroduction())
                    .registeredDate(result.get().getRegisteredDate())
                    .build();

            return artistResponse;
        } else {
            return null;
        }
    }

}
