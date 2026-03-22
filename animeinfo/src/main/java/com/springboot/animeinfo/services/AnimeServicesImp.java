package com.springboot.animeinfo.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.animeinfo.Exception.AnimeNotFoundException;
import com.springboot.animeinfo.dto.AnimeDto;
import com.springboot.animeinfo.entity.Anime;
import com.springboot.animeinfo.repository.AnimeRepository;

@Service("animeimp")
public class AnimeServicesImp implements AnimeServices {

    @Autowired
    private AnimeRepository animeRepository;

    @Override
    public String addAnime(AnimeDto animeDto) {

        Anime anime = Anime.builder()
                .name(animeDto.getName())
                .genre(animeDto.getGenre())
                .eps(animeDto.getEps())
                .ratings(animeDto.getRatings())
                .year(animeDto.getYear())
                .build();

        animeRepository.save(anime);
        return "anime obj saved successfully...";

    }

    @Override
    public Anime getAnimeById(int id) {

        return animeRepository.findById(id)
                .orElseThrow(() -> new AnimeNotFoundException("no anime found with this id."));

    }

    @Override
    public String getAnimeDetails(String name, String genre) {

        Optional<Anime> optionalAnime = animeRepository.findByName(name);
        if (optionalAnime.isPresent()) {
            Anime anime = optionalAnime.get();
            if (anime.getGenre().equals(genre)) {
                return "correct match found...";
            } else {
                return "matching genre not found...";
            }
        } else {
            return "no anime found with this name...";
        }

    }

    @Override
    public String updateAnime(AnimeDto animeDto, int id) {

        Optional<Anime> optionalAnime = animeRepository.findById(id);

        if (optionalAnime.isPresent()) {
            Anime anime = optionalAnime.get();

            if (animeDto.getName() != null) {
                anime.setName(animeDto.getName());
            }

            if (animeDto.getGenre() != null) {
                anime.setGenre(animeDto.getGenre());
            }

            if (animeDto.getEps() != 0) {
                anime.setEps(animeDto.getEps());
            }

            if (animeDto.getRatings() != 0.0) {
                anime.setRatings(animeDto.getRatings());
            }

            if (animeDto.getYear() != 0) {
                anime.setYear(animeDto.getYear());
            }

            animeRepository.save(anime);
            return "anime obj updated successfully...";
        }

        return "no anime found with this id...";

    }

    @Override
    public String deleteAnime(int id) {

        Optional<Anime> optionalAnime = animeRepository.findById(id);

        if (optionalAnime.isPresent()) {
            Anime anime = optionalAnime.get();

            animeRepository.delete(anime);
            return "anime obj deleted successfully with id:" + anime.getId();

        }

        return "no anime found with this id...";

    }

}
