package com.example.movie.NowPlayingMovies;

import com.google.gson.annotations.SerializedName;

public class MoviePoster {

    @SerializedName("poster_path")
    private String poster_path;

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public MoviePoster(String poster_path) {
        this.poster_path = poster_path;


    }
}


