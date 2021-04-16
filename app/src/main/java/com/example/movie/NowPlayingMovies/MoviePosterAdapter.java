package com.example.movie.NowPlayingMovies;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movie.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MoviePosterAdapter  extends RecyclerView.Adapter<MoviePosterAdapter.MoviePosterViewHolder> {

    Context context;
    ArrayList<MoviePoster> moviePosters;

    public MoviePosterAdapter(Context context, ArrayList<MoviePoster> moviePosters) {
        this.context = context;
        this.moviePosters = moviePosters;
    }

    @NonNull
    @Override
    public MoviePosterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.movie_poster_item, parent,false);
        return new MoviePosterAdapter.MoviePosterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MoviePosterViewHolder holder, int position) {
        MoviePoster moviePoster = moviePosters.get(position);
        Picasso.with(context)
                .load("https://image.tmdb.org/t/p/w500"+moviePoster.getPoster_path())
                .into(holder.movie_poster_playing);
    }

    @Override
    public int getItemCount() {
        return moviePosters.size();
    }

    public class MoviePosterViewHolder extends RecyclerView.ViewHolder {
        ImageView movie_poster_playing;
        public MoviePosterViewHolder(@NonNull View itemView) {
            super(itemView);
            movie_poster_playing = itemView.findViewById(R.id.movie_poster_playing);
        }
    }
}


