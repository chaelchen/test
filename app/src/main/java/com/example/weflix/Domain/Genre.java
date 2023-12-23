
package com.example.weflix.Domain;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class Genre {
    private List<GenresItems> genres;

    public List<GenresItems> getGenres() {
        return genres;
    }

    public void setGenres(List<GenresItems> genres) {
        this.genres = genres;
    }
}
