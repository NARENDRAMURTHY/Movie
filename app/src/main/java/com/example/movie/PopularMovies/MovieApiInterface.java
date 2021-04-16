package com.example.movie.PopularMovies;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieApiInterface {


    @GET("movie/popular?api_key=ba9fac0706cdc733edce82efc9536577")
    Call<MovieResponse> getMovieList(@Query("page")int pageNumber) ;

}


