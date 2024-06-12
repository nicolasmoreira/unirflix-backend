package com.unir.movies.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatchException;
import com.github.fge.jsonpatch.mergepatch.JsonMergePatch;
import com.unir.movies.data.MovieRepository;
import com.unir.movies.model.pojo.Movie;
import com.unir.movies.model.request.CreateMovieRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@Slf4j
public class MoviesServiceImpl implements MoviesService {

    @Autowired
    private MovieRepository repository;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public List<Movie> getMovies() {

        List<Movie> movies = repository.getMovies();
        return movies.isEmpty() ? null : movies;
    }

    @Override
    public Movie getMovie(String movieId) {

        return repository.getById(Long.valueOf(movieId));
    }

    @Override
    public Boolean removeMovie(String movieId) {

        Movie movie = repository.getById(Long.valueOf(movieId));
        if (movie != null) {
            repository.delete(movie);
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }

    @Override
    public Movie createMovie(CreateMovieRequest request) {

        if (request != null
                && StringUtils.hasLength(request.getTitle().trim())
                && StringUtils.hasLength(request.getDirector().trim())
                && request.getYear() > 0
                && StringUtils.hasLength(request.getSynopsis().trim())
                && StringUtils.hasLength(request.getTrailerUrl().trim())
                && StringUtils.hasLength(request.getActors().trim())
                && StringUtils.hasLength(request.getCategory().trim())
                && StringUtils.hasLength(request.getLanguage().trim())
        ) {

            Movie movie = Movie.builder()
                    .title(request.getTitle())
                    .director(request.getDirector())
                    .year(request.getYear())
                    .synopsis(request.getSynopsis())
                    .trailerUrl(request.getTrailerUrl())
                    .actors(request.getActors())
                    .category(request.getCategory())
                    .language(request.getLanguage())
                    .build();

            return repository.save(movie);
        } else {
            return null;
        }
    }


    @Override
    public Movie updateMovie(String movieId, String updateRequest) {

        Movie movie = repository.getById(Long.valueOf(movieId));
        if (movie != null) {
            try {
                JsonMergePatch jsonMergePatch = JsonMergePatch.fromJson(objectMapper.readTree(updateRequest));
                JsonNode target = jsonMergePatch.apply(objectMapper.readTree(objectMapper.writeValueAsString(movie)));
                Movie patched = objectMapper.treeToValue(target, Movie.class);
                repository.save(patched);
                return patched;
            } catch (JsonProcessingException | JsonPatchException e) {
                log.error("Error updating movie {}", movieId, e);
                return null;
            }
        } else {
            return null;
        }
    }

    @Override
    public Movie updateMovie(String movieId, CreateMovieRequest request) {
        Movie movie = repository.getById(Long.valueOf(movieId));
        if (movie != null && request != null
                && StringUtils.hasLength(request.getTitle().trim())
                && StringUtils.hasLength(request.getDirector().trim())
                && request.getYear() > 0
                && StringUtils.hasLength(request.getSynopsis().trim())
                && StringUtils.hasLength(request.getTrailerUrl().trim())
                && StringUtils.hasLength(request.getActors().trim())
                && StringUtils.hasLength(request.getCategory().trim())
                && StringUtils.hasLength(request.getLanguage().trim())
        ) {
            movie.setTitle(request.getTitle());
            movie.setDirector(request.getDirector());
            movie.setYear(request.getYear());
            movie.setSynopsis(request.getSynopsis());
            movie.setTrailerUrl(request.getTrailerUrl());
            movie.setActors(request.getActors());
            movie.setCategory(request.getCategory());
            movie.setLanguage(request.getLanguage());

            repository.save(movie);
            return movie;
        } else {
            return null;
        }
    }

    @Override
    public List<Movie> getMovies(String title, String director, String year, String synopsis, String actors, String category, String language) {

        if (StringUtils.hasLength(title) || StringUtils.hasLength(director) || StringUtils.hasLength(year) || StringUtils.hasLength(synopsis) || StringUtils.hasLength(actors) || StringUtils.hasLength(category) || StringUtils.hasLength(language)) {
            return repository.search(title, director, year, synopsis, actors, category, language);
        }

        List<Movie> movies = repository.getMovies();
        return movies.isEmpty() ? null : movies;
    }
}
