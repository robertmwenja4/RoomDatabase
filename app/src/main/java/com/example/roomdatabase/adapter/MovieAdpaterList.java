package com.example.roomdatabase.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.roomdatabase.R;
import com.example.roomdatabase.model.MovieModel;

import java.util.List;

public class MovieAdpaterList extends RecyclerView.Adapter<MovieAdpaterList.MyViewHolder> {

    public Context context;
    public List<MovieModel> movielist;
    public ItemClickListener clickListener;

    public MovieAdpaterList(Context context, List<MovieModel> movielist, ItemClickListener clickListener){
        this.context = context;
        this.movielist = movielist;
        this.clickListener = clickListener;
    }
    public void setMovielist(List<MovieModel> movielist){
        this.movielist = movielist;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MovieAdpaterList.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_row2, parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieAdpaterList.MyViewHolder holder, int position) {

        holder.tvTitle.setText(this.movielist.get(position).getTitle().toString());
        holder.mimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickListener.onClickLisener(movielist.get(position));
            }
        });
        Glide.with(context).load(this.movielist.get(position).getImage()).apply(RequestOptions.centerCropTransform())
                .into(holder.mimage);

    }

    @Override
    public int getItemCount() {
        if (this.movielist != null){
            return this.movielist.size();
        }
        return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tvTitle;
        ImageView mimage;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvMovie);
            mimage = itemView.findViewById(R.id.image);
        }
    }

    public interface ItemClickListener{
        public void onClickLisener(MovieModel movie);
    }
}
