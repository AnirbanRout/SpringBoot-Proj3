package com.springboot.animeinfo.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.springboot.animeinfo.entity.Anime;

public interface AnimeRepository extends JpaRepository<Anime, Integer> {

    Optional<Anime> findByName(String name);

}