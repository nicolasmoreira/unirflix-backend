package com.unir.movies.service;

import com.unir.movies.model.pojo.Movie;
import com.unir.movies.model.request.CreateMovieRequest;

import java.util.List;

public interface MoviesService {

    List<Movie> getMovies();

    List<Movie> getMovies(String title, String director, String year, String synopsis, String actors, String category, String language);

    Movie getMovie(String movieId);

    Boolean removeMovie(String movieId);

    Movie createMovie(CreateMovieRequest request);

    Movie updateMovie(String movieId, String updateRequest);

    Movie updateMovie(String movieId, CreateMovieRequest request);
}
