package com.example.weflix.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.weflix.Adapters.ActorListAdapter;
import com.example.weflix.Adapters.FilmListAdapter;
import com.example.weflix.Adapters.GenreEachFilmListAdapter;
import com.example.weflix.Domain.FilmItem;
import com.example.weflix.R;
import com.google.gson.Gson;

public class DetailActivity extends AppCompatActivity {
    private RequestQueue mRequestQueue;
    private StringRequest mStringRequest;
    private ProgressBar progressBar;
    private TextView titleTxt,movieRatingTxt,movieDurationTxt,movieSynopsisTxt,movieActorTxt;
    private int idFilm;
    private ImageView picDetail, backImage;
    private RecyclerView.Adapter adapterActorList;
    private RecyclerView recyclerViewActors;
    private NestedScrollView scrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        idFilm = getIntent().getIntExtra("id",0);
        initView();
        sendRequest();

    }

    private void sendRequest() {
        mRequestQueue = Volley.newRequestQueue(this);
        progressBar.setVisibility(View.VISIBLE);
        scrollView.setVisibility(View.GONE);

        mStringRequest = new StringRequest(Request.Method.GET, "https://moviesapi.ir/api/v1/movies/" + idFilm, response -> {
            Gson gson=new Gson();
            progressBar.setVisibility(View.GONE);
            scrollView.setVisibility(View.VISIBLE);

            FilmItem item=gson.fromJson(response, FilmItem.class);

            Glide.with(DetailActivity.this)
                    .load(item.getPoster())
                    .into(picDetail);
            titleTxt.setText(item.getTitle());
            movieRatingTxt.setText(item.getImdbRating());
            movieDurationTxt.setText(item.getRuntime());
            movieSynopsisTxt.setText(item.getPlot());
            movieActorTxt.setText(item.getActors());

            if(item.getImages()!=null){
                adapterActorList= new ActorListAdapter(item.getImages());
                recyclerViewActors.setAdapter(adapterActorList);
            }
        }, error -> progressBar.setVisibility(View.GONE));
        mRequestQueue.add(mStringRequest);
    }
    private void initView() {
        progressBar=findViewById(R.id.progressBarDetail);
        scrollView=findViewById(R.id.scrollView3);
        picDetail=findViewById(R.id.posterDetail);
        backImage=findViewById(R.id.backImg);
        recyclerViewActors=findViewById(R.id.actorView);

        titleTxt=findViewById(R.id.MovieTitleTxt);
        movieRatingTxt=findViewById(R.id.MovieRating);
        movieDurationTxt=findViewById(R.id.MovieDuration);
        movieSynopsisTxt=findViewById(R.id.MovieSynopsis);
        movieActorTxt=findViewById(R.id.MovieActor);


        recyclerViewActors.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

        backImage.setOnClickListener(v -> finish());



    }
}