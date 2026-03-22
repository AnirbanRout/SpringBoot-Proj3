package com.springboot.animeinfo.services;

import com.springboot.animeinfo.dto.AnimeDto;
import com.springboot.animeinfo.entity.Anime;

public interface AnimeServices {

    String addAnime(com.springboot.animeinfo.dto.AnimeDto animeDto);

    Anime getAnimeById(int id);

    String getAnimeDetails(String name, String genre);

    String updateAnime(AnimeDto animeDto, int id);

    String deleteAnime(int id);

}
