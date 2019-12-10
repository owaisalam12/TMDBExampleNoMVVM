package com.owais.tmdbexample.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.owais.tmdbexample.MovieActivity;
import com.owais.tmdbexample.R;
import com.owais.tmdbexample.model.Movie;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MyHolder> {

    private Context context;
    private ArrayList<Movie> moviesList;

    public MovieAdapter(Context context, ArrayList<Movie> moviesList) {
        this.context = context;
        this.moviesList = moviesList;
    }

    @NonNull
    @Override
    public MovieAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_list_item, parent, false);
        return new MovieAdapter.MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieAdapter.MyHolder holder, int position) {
        Movie movie=moviesList.get(position);
        holder.movieTitle.setText(movie.getTitle());
        holder.rate.setText(movie.getVoteAverage().toString());
        String imagePath="https://image.tmdb.org/t/p/w500"+movie.getPosterPath();

        Glide.with(context)
                .load(imagePath)
                .placeholder(R.drawable.loading)
                .into(holder.movieImage);
    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        public TextView movieTitle,rate;
        public ImageView movieImage;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            movieTitle=itemView.findViewById(R.id.tvTitle);
            rate=itemView.findViewById(R.id.tvRating);
            movieImage=itemView.findViewById(R.id.ivMovie);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position=getAdapterPosition();
                    if(position!=RecyclerView.NO_POSITION){
                        Movie selectedMovie=moviesList.get(position);
                        Intent intent=new Intent(context, MovieActivity.class);
                        intent.putExtra("movie",selectedMovie);
                        context.startActivity(intent);
                    }
                }
            });
        }
    }
}
