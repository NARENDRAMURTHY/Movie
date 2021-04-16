package com.example.movie.NowPlayingMovies;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MoviePlayingService {
    public static String BASE_URL ="https://api.themoviedb.org/3/";
    private static Retrofit retrofit2;
    public static Retrofit getInstanceTwo(){
        if(retrofit2 == null){
            retrofit2 = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit2;
    }
}


