package com.springboot.animeinfo.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AnimeDto {

    @NotEmpty(message = "enter the anime name")
    private String name;

    @NotEmpty(message = "enter the genre")
    private String genre;

    @NotNull(message = "enter the episode count")
    @jakarta.validation.constraints.Min(1)
    private Integer eps;

    @NotNull(message = "give ratings")
    @jakarta.validation.constraints.Min(1)
    @jakarta.validation.constraints.Max(10)
    private Double ratings;

    @NotNull(message = "enter the release year")
    @jakarta.validation.constraints.Min(2000)
    private Integer year;

}
