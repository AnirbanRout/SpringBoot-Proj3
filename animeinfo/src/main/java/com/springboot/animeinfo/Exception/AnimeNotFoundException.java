package com.springboot.animeinfo.Exception;

public class AnimeNotFoundException extends RuntimeException {

    public AnimeNotFoundException(String msg) {
        super(msg);
    }

}
