package com.unir.rents.service;

import com.unir.rents.data.RatingsJpaRepository;
import com.unir.rents.exceptions.AlreadyRatedException;
import com.unir.rents.exceptions.MovieAlreadyRentedException;
import com.unir.rents.exceptions.MovieNotFoundException;
import com.unir.rents.exceptions.RatingNotFoundException;
import com.unir.rents.facade.MoviesFacade;
import com.unir.rents.model.Movie;
import com.unir.rents.model.db.Rating;
import com.unir.rents.model.db.Rent;
import com.unir.rents.model.request.PatchRatingRequest;
import com.unir.rents.model.request.RatingRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class RatingsServiceImpl implements RatingsService{

  @Autowired //Inyeccion por campo (field injection). Es la menos recomendada.
  private MoviesFacade moviesFacade;

  @Autowired //Inyeccion por campo (field injection). Es la menos recomendada.
  private RatingsJpaRepository repository;

  @Override
  public Rating createRating(RatingRequest request) {

    Movie movie = moviesFacade.getMovie(String.valueOf(request.getMovieId()));

    if( movie == null)
      throw new MovieNotFoundException("Movie not found");

    // Can't rent again without returning it first
    if(repository.existsByCustomerNameAndMovieId(request.getCustomerName(), request.getMovieId())) {
      throw new AlreadyRatedException("This user already rated this movie");
    } else {
      Rating rating = new Rating();
      rating.setCustomerName(request.getCustomerName());
      rating.setMovieId(movie.getId());
      rating.setRated_at(new Timestamp(System.currentTimeMillis()));
      rating.setRating(request.getRating());
      repository.save(rating);
      return rating;
    }

  }

  @Override
  public Rating editRating(String id, PatchRatingRequest request) {
    Rating rating = repository.findById(Long.valueOf(id)).orElse(null);
    if(rating == null)
      throw new RatingNotFoundException("Rating not found");
    rating.setRating(request.getRating());
    return repository.save(rating);
  }

  @Override
  public Rating getRating(String id) {
    return repository.findById(Long.valueOf(id)).orElse(null);
  }

  @Override
  public List<Rating> getRatings() {
    List<Rating> ratings = repository.findAll();
    return ratings.isEmpty() ? null : ratings;
  }
}
