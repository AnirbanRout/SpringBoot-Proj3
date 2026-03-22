package com.springboot.animeinfo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.animeinfo.dto.AnimeDto;
import com.springboot.animeinfo.entity.Anime;
import com.springboot.animeinfo.services.AnimeServices;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/anime")
public class AnimeController {

    @Autowired
    @Qualifier("animeimp")
    private AnimeServices animeServices;

    @GetMapping("/home")
    public String homePage() {
        return "anime world home page...";
    }

    @PostMapping("/add-anime")
    public String addAnime(@Valid @RequestBody AnimeDto animeDto) {

        String message = animeServices.addAnime(animeDto);
        return message;

    }

    @PostMapping("/get-anime/{id}")
    public Anime getAnimeById(@PathVariable("id") int id) {
        return animeServices.getAnimeById(id);
    }

    @PostMapping("/find-anime")
    public String animeDetails(@RequestParam String name, @RequestParam String genre) {
        String msg = animeServices.getAnimeDetails(name, genre);
        return msg;
    }

    @PutMapping("/update-anime")
    public String updateAnime(@Valid @RequestBody AnimeDto animeDto, @RequestParam int id) {
        String msg = animeServices.updateAnime(animeDto, id);
        return msg;
    }

    @DeleteMapping("/delete-anime/{id}")
    public String deleteAnime(@PathVariable("id") int id) {
        String msg = animeServices.deleteAnime(id);
        return msg;
    }

}
