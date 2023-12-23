package com.example.weflix.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weflix.Domain.GenresItems;
import com.example.weflix.R;

import java.util.ArrayList;
import java.util.List;

public class GenreEachFilmListAdapter extends RecyclerView.Adapter<GenreEachFilmListAdapter.ViewHolder> {
    List<String> items;
    Context context;

    public GenreEachFilmListAdapter(List<String> item) {
        this.items = item;
    }

    @NonNull
    @Override
    public GenreEachFilmListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context=parent.getContext();
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_film,parent,false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull GenreEachFilmListAdapter.ViewHolder holder, int position) {
        holder.titleTxt.setText(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView titleTxt;
        ImageView pic;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTxt=itemView.findViewById(R.id.titleTxt);
            pic=itemView.findViewById(R.id.pic);
        }
    }
}
