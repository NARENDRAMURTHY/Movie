package com.example.movie.NowPlayingMovies;

import com.example.movie.NowPlayingMovies.MoviePoster;
import com.google.gson.annotations.SerializedName;

public class MoviePosterResponse {

    @SerializedName("results")
    private MoviePoster[] results;

    public MoviePoster[] getResults() {
        return results;
    }

    public void setResult(MoviePoster[] results) {
        this.results = results;
    }
}


