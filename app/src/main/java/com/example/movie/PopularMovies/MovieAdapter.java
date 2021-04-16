package com.example.movie.PopularMovies;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.movie.R;
import com.squareup.picasso.Picasso;


import java.util.ArrayList;

public class MovieAdapter  extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {
 public  static boolean isLoading = false;
    Context context;
    ArrayList<Movie> movies;

    public MovieAdapter(Context context, ArrayList<Movie> movies) {
        this.context = context;
        this.movies = movies;
    }

    public int getItemViewType(int position){
        if(position == movies.size() - 1){
            return 1;
        }
        return 0;
}

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater  = LayoutInflater.from(context);
        if(viewType == 0){
            return  new MovieViewHolder(layoutInflater.inflate(R.layout.movie_item,parent,false),viewType);
        }
        if(viewType == 1){
            return  new MovieViewHolder(layoutInflater.inflate(R.layout.progress_bar,parent,false), viewType);
        }
        return null;

    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        if(position == movies.size()- 1){
            isLoading = true;
            return;
        }
        Movie movie = movies.get(position);
        Picasso.with(context)
                .load("https://image.tmdb.org/t/p/w500/"+movie.getPoster_path())
                .into(holder.movie_poster);

        holder.movie_title.setText(movie.getTitle());
        holder.movie_release_date.setText(movie.getRelease_date());

    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder {
        ImageView movie_poster;
        TextView movie_title,movie_release_date;
        public MovieViewHolder(@NonNull View itemView, int viewType) {
            super(itemView);
            if(viewType == 0) {
                movie_poster = itemView.findViewById(R.id.movie_poster);
                movie_title = itemView.findViewById(R.id.movie_title);
                movie_release_date = itemView.findViewById(R.id.movie_release_date);
            }

        }
    }
}


