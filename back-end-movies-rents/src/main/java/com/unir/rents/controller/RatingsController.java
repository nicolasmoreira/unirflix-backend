package com.unir.rents.controller;

import com.unir.rents.model.db.Rating;
import com.unir.rents.model.request.PatchRatingRequest;
import com.unir.rents.model.request.RatingRequest;
import com.unir.rents.model.request.ReturnRequest;
import com.unir.rents.service.RatingsService;
import com.unir.rents.service.RatingsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class RatingsController {

    private final RatingsService service; //Inyeccion por constructor mediante @RequiredArgsConstructor. Y, también es inyección por interfaz.

    @PostMapping("/ratings")
    public ResponseEntity<Rating> createRating(@RequestBody @Valid RatingRequest request) { //Se valida con Jakarta Validation API

        log.info("Creating a new rating...");
        Rating created = service.createRating(request);

        if (created != null) {
            return ResponseEntity.ok(created);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PatchMapping("/ratings/{id}")
    public ResponseEntity<Rating> editRating(@RequestBody @Valid PatchRatingRequest request, @PathVariable String id) { //Se valida con Jakarta Validation API

        Rating rating = service.editRating(id, request);

        if (rating != null) {
            return ResponseEntity.ok(rating);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/ratings")
    public ResponseEntity<List<Rating>> getRatings() {

        List<Rating> ratings = service.getRatings();
        if (ratings != null) {
            return ResponseEntity.ok(ratings);
        } else {
            return ResponseEntity.ok(Collections.emptyList());
        }
    }

    @GetMapping("/ratings/{id}")
    public ResponseEntity<Rating> getRating(@PathVariable String id) {

        Rating rating = service.getRating(id);
        if (rating != null) {
            return ResponseEntity.ok(rating);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
