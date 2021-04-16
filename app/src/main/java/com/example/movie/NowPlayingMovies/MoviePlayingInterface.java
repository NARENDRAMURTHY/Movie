package com.example.movie.NowPlayingMovies;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MoviePlayingInterface {
    @GET("movie/now_playing?language=en-US&page=undefined&api_key=ba9fac0706cdc733edce82efc9536577")
    Call<MoviePosterResponse> getPosterList();
}
