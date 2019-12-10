package com.owais.tmdbexample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.res.Configuration;
import android.os.Bundle;

import com.owais.tmdbexample.adapter.MovieAdapter;
import com.owais.tmdbexample.model.Movie;
import com.owais.tmdbexample.model.MovieDBResponse;
import com.owais.tmdbexample.service.RetrofitInstance;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Movie> moviesList;
    private RecyclerView recyclerView;
    private MovieAdapter movieAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getPopularMovies();

        swipeRefreshLayout=findViewById(R.id.swiperefresh);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                moviesList.clear();
                movieAdapter.notifyDataSetChanged();
                getPopularMovies();

            }
        });



    }
    private void getPopularMovies(){
        Call<MovieDBResponse> call= RetrofitInstance.getService().getPopularMovies(this.getString(R.string.api_key));
        call.enqueue(new Callback<MovieDBResponse>() {
            @Override
            public void onResponse(Call<MovieDBResponse> call, Response<MovieDBResponse> response) {
                MovieDBResponse movieDBResponse=response.body();
                if(movieDBResponse!=null && movieDBResponse.getMovies()!=null){
                    moviesList=(ArrayList<Movie>)movieDBResponse.getMovies();

                    showOnRecyclerView();
                    swipeRefreshLayout.setRefreshing(false);
                }

            }

            @Override
            public void onFailure(Call<MovieDBResponse> call, Throwable t) {

            }
        });
    }

    private void showOnRecyclerView(){
        recyclerView=findViewById(R.id.rvMovies);
        movieAdapter=new MovieAdapter(this,moviesList);
        if(this.getResources().getConfiguration().orientation== Configuration.ORIENTATION_PORTRAIT){
            recyclerView.setLayoutManager(new GridLayoutManager(this,2));

        }else{
            recyclerView.setLayoutManager(new GridLayoutManager(this,4));

        }
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(movieAdapter);
        movieAdapter.notifyDataSetChanged();
    }
}
