package com.example.roomdatabase.viewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.roomdatabase.model.MovieModel;
import com.example.roomdatabase.network.APIservice;
import com.example.roomdatabase.network.RetroInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieListViewModel extends ViewModel {

    private MutableLiveData<List<MovieModel>> movielist;

    public MovieListViewModel(){

        movielist = new MutableLiveData<>();
    }
    public MutableLiveData<List<MovieModel>> getMovielistObserver(){
        return movielist;
    }
    public void makeApiCall(){
        APIservice apIservice = RetroInstance.getRetrofitInstance().create(APIservice.class);
        Call<List<MovieModel>> call = apIservice.getMovieList();
        call.enqueue(new Callback<List<MovieModel>>() {
            @Override
            public void onResponse(Call<List<MovieModel>> call, Response<List<MovieModel>> response) {
                movielist.postValue(response.body());

            }

            @Override
            public void onFailure(Call<List<MovieModel>> call, Throwable t) {
                movielist.postValue(null);
            }
        });
    }
}
