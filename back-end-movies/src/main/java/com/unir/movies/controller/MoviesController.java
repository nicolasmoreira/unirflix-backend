package com.unir.movies.controller;

import com.unir.movies.model.pojo.Movie;
import com.unir.movies.model.request.CreateMovieRequest;
import com.unir.movies.service.MoviesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Slf4j
public class MoviesController {

    @Autowired
    private final MoviesService service;

    @GetMapping("/movies")
    @Operation(
            operationId = "Obtener movies",
            description = "Operacion de lectura",
            summary = "Se devuelve una lista de todas las movies almacenadas en la base de datos.")
    @ApiResponse(
            responseCode = "200",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Movie.class)))
    public ResponseEntity<List<Movie>> getMovies(
            @RequestHeader Map<String, String> headers,
            @Parameter(name = "title", description = "Title de la movie. No tiene por que ser exacto", example = "La vida es bella", required = false)
            @RequestParam(required = false) String title,
            @Parameter(name = "director", description = "Director de la movie. No tiene por que ser exacto", example = "Jhon", required = false)
            @RequestParam(required = false) String director,
            @Parameter(name = "year", description = "Year de la movie. Deb ser exacta", example = "2022", required = false)
            @RequestParam(required = false) String year,
            @Parameter(name = "synopsis", description = "Synopsis de la movie. No tiene por que ser exacto", example = "lorem impsm", required = false)
            @RequestParam(required = false) String synopsis,
            @Parameter(name = "actors", description = "Actors de la movie. No tiene por que ser exacto", example = "Peter", required = false)
            @RequestParam(required = false) String actors,
            @Parameter(name = "category", description = "Category de la movie. No tiene por que ser exacto", example = "Horror", required = false)
            @RequestParam(required = false) String category,
            @Parameter(name = "language", description = "Language de la movie. No tiene por que ser exacto", example = "ES", required = false)
            @RequestParam(required = false) String language) {

        log.info("headers: {}", headers);
        List<Movie> movies = service.getMovies(title, director, year, synopsis, actors, category, language);

        if (movies != null) {
            return ResponseEntity.ok(movies);
        } else {
            return ResponseEntity.ok(Collections.emptyList());
        }
    }

    @GetMapping("/movies/{movieId}")
    public ResponseEntity<Movie> getMovie(@PathVariable String movieId) {

        log.info("Request received for movie {}", movieId);
        Movie movie = service.getMovie(movieId);

        if (movie != null) {
            return ResponseEntity.ok(movie);
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @DeleteMapping("/movies/{movieId}")
    public ResponseEntity<Void> deleteMovie(@PathVariable String movieId) {

        Boolean removed = service.removeMovie(movieId);

        if (Boolean.TRUE.equals(removed)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @PostMapping("/movies")
    public ResponseEntity<Movie> getMovie(@RequestBody CreateMovieRequest request) {

        Movie createdMovie = service.createMovie(request);

        if (createdMovie != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(createdMovie);
        } else {
            return ResponseEntity.badRequest().build();
        }

    }

    @PatchMapping("/movies/{movieId}")
    public ResponseEntity<Movie> patchMovie(@PathVariable String movieId, @RequestBody String patchBody) {

        Movie patched = service.updateMovie(movieId, patchBody);
        if (patched != null) {
            return ResponseEntity.ok(patched);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/movies/{movieId}")
    public ResponseEntity<Movie> updateMovie(@PathVariable String movieId, @RequestBody CreateMovieRequest body) {

        Movie updated = service.updateMovie(movieId, body);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
