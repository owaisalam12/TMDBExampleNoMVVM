package com.owais.tmdbexample;

import android.content.Intent;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.owais.tmdbexample.model.Movie;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MovieActivity extends AppCompatActivity {

    Movie movie;
    ImageView imageView;
    String image;
    private TextView movieTitle,movieSynopsis,movieRating,movieReleaseDate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
        Toolbar toolbar = findViewById(R.id.toolbar);
        imageView=findViewById(R.id.ivMovieLarge);
        setSupportActionBar(toolbar);
        movieTitle=findViewById(R.id.tvMovieTitle);
        movieSynopsis=findViewById(R.id.tvPlotsynopsis);
        movieRating=findViewById(R.id.tvMovieRating);
        movieReleaseDate=findViewById(R.id.tvReleaseDate);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent intent=getIntent();
        if(intent.hasExtra("movie")){
            movie=getIntent().getParcelableExtra("movie");
            //Toast.makeText(this, movie.getTitle(), Toast.LENGTH_SHORT).show();
            image=movie.getPosterPath();
            String path="https://image.tmdb.org/t/p/w500"+image;
            Glide.with(this)
                    .load(path)
                    .placeholder(R.drawable.loading)
                    .into(imageView);
            getSupportActionBar().setTitle(movie.getTitle());
            movieTitle.setText(movie.getTitle());
            movieSynopsis.setText(movie.getOverview());
            movieRating.setText(movie.getVoteAverage().toString());
            movieReleaseDate.setText(movie.getReleaseDate());

        }
    }

}
