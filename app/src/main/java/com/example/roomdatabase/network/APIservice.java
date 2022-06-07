package com.example.roomdatabase.network;

import com.example.roomdatabase.model.MovieModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIservice {

    @GET("")
    Call<List<MovieModel>> getMovieList();
}
