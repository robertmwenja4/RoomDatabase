package com.example.roomdatabase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.roomdatabase.adapter.MovieAdpaterList;
import com.example.roomdatabase.model.MovieModel;
import com.example.roomdatabase.viewModel.MovieListViewModel;

import java.util.List;

public class Main extends AppCompatActivity implements MovieAdpaterList.ItemClickListener{

    private List<MovieModel> movieModelList;
    private MovieAdpaterList adpater;
    private MovieListViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        RecyclerView recyclerView = findViewById(R.id.recycler);
        TextView no = findViewById(R.id.noResult);
        LinearLayoutManager linearLayout = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(linearLayout);
        adpater = new MovieAdpaterList(this,movieModelList,this);
        recyclerView.setAdapter(adpater);

        viewModel = ViewModelProviders.of(this).get(MovieListViewModel.class);
        viewModel.getMovielistObserver().observe(this, new Observer<List<MovieModel>>() {
            @Override
            public void onChanged(List<MovieModel> movieModels) {
                if (movieModels != null){
                    movieModelList = movieModels;
                    adpater.setMovielist(movieModels);
                }else {
                    no.setVisibility(View.VISIBLE);
                }
            }
        });
        viewModel.makeApiCall();
    }

    @Override
    public void onClickLisener(MovieModel movie) {

        Toast.makeText(this, "Movie Clicked is: "+movie.getTitle(), Toast.LENGTH_SHORT).show();
    }
}