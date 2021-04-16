package com.example.movie;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;



import com.example.movie.NowPlayingMovies.MoviePlayingInterface;
import com.example.movie.NowPlayingMovies.MoviePlayingService;
import com.example.movie.NowPlayingMovies.MoviePoster;
import com.example.movie.NowPlayingMovies.MoviePosterAdapter;
import com.example.movie.NowPlayingMovies.MoviePosterResponse;
import com.example.movie.PopularMovies.Movie;
import com.example.movie.PopularMovies.MovieAdapter;
import com.example.movie.PopularMovies.MovieApiInterface;
import com.example.movie.PopularMovies.MovieApiService;
import com.example.movie.PopularMovies.MovieResponse;



import java.util.ArrayList;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView,movie_poster_recyclerView;
   MovieAdapter mMovieAdapter;
   private ArrayList<Movie> movie;


   private int currentPage = 1;
    private int mCount;

    private ArrayList<MoviePoster> moviePosters;
    MoviePosterAdapter moviePosterAdapter;

    static MovieApiInterface movieApiInterface;
    static Call<MovieResponse> moviePopularCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.action_bar_layout);

        movie_poster_recyclerView = findViewById(R.id.movie_poster_recyclerView);
        movie_poster_recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this,LinearLayoutManager.HORIZONTAL,false));
        nowPlayingMovies();

        recyclerView = findViewById(R.id.movie_recyclerView);
        recyclerView.setItemViewCacheSize(20);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this,LinearLayoutManager.VERTICAL,false));
        loadFirstPage();

      recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
          @Override
          public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
              super.onScrollStateChanged(recyclerView, newState);
          }

          @Override
          public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
              super.onScrolled(recyclerView, dx, dy);
              final int Count = mMovieAdapter.getItemCount();
              if(Count < mCount && MovieAdapter.isLoading){
                  new Handler().postDelayed(new Runnable() {
                      @Override
                      public void run() {
                          loadNextPage();
                      }
                  },1000);
              }
          }
      });
    }
    public void loadNextPage(){
        if(MovieAdapter.isLoading){
            int c = this.currentPage + 1;
            this.currentPage = c;
            Call<MovieResponse> results = movieApiInterface.getMovieList(c);
            moviePopularCall = results;
            results.enqueue(new Callback<MovieResponse>() {
                @Override
                public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                    if (response.isSuccessful()) {
                        MovieResponse movieResponse = response.body();
                        movie = new ArrayList<>(Arrays.asList(movieResponse.getResults()));
                        mMovieAdapter = new MovieAdapter(MainActivity.this, movie);
                        recyclerView.setAdapter(mMovieAdapter);

                        mMovieAdapter.notifyItemInserted(MainActivity.this.movie.size()-1);

                    }
                    MovieAdapter.isLoading = false;
                }

                @Override
                public void onFailure(Call<MovieResponse> call, Throwable t) {
                    Log.d("Errors",t.getMessage());
                }
            });

        }
    }
    public void loadFirstPage(){

        MovieApiInterface movieApiInterface1 = MovieApiService.getInstance().create(MovieApiInterface.class);
        movieApiInterface = movieApiInterface1;
        Call<MovieResponse> results = movieApiInterface.getMovieList(this.currentPage);
        moviePopularCall = results;
        results.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                if (response.isSuccessful()) {
                    MainActivity.this.mCount = response.body().getTotalResults();

                    MovieResponse movieResponse = response.body();
                    movie = new ArrayList<>(Arrays.asList(movieResponse.getResults()));
                    mMovieAdapter = new MovieAdapter(MainActivity.this, movie);
                    recyclerView.setAdapter(mMovieAdapter);

                }
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                Log.d("Errors",t.getMessage());
            }
        });

    }
    public void nowPlayingMovies(){
        MoviePlayingInterface moviePlayingInterface = MoviePlayingService.getInstanceTwo().create(MoviePlayingInterface.class);
        Call<MoviePosterResponse> calls = moviePlayingInterface.getPosterList();
        calls.enqueue(new Callback<MoviePosterResponse>() {
            @Override
            public void onResponse(Call<MoviePosterResponse> call, Response<MoviePosterResponse> response) {

                MoviePosterResponse moviePosterResponse = response.body();
                moviePosters = new ArrayList<>(Arrays.asList(moviePosterResponse.getResults()));
                moviePosterAdapter = new MoviePosterAdapter(MainActivity.this,moviePosters);
                movie_poster_recyclerView.setAdapter(moviePosterAdapter);

            }

            @Override
            public void onFailure(Call<MoviePosterResponse> call, Throwable t) {
                Log.d("Errors",t.getMessage());
            }
        });


    }
}