package com.unir.movies.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateMovieRequest {

    private String title;
    private String director;
    private Integer year;
    private String synopsis;
    private String trailerUrl;
    private String actors;
    private String category;
    private String language;
}
