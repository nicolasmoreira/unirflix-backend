package com.unir.rents.service;

import com.unir.rents.data.RentJpaRepository;
import com.unir.rents.exceptions.MovieAlreadyRentedException;
import com.unir.rents.exceptions.MovieAlreadyReturnedException;
import com.unir.rents.exceptions.RentNotFoundException;
import com.unir.rents.model.Movie;
import com.unir.rents.model.db.Rent;
import com.unir.rents.model.request.ReturnRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.unir.rents.facade.MoviesFacade;
import com.unir.rents.model.request.RentRequest;

@Service
public class RentsServiceImpl implements RentsService {

  @Autowired //Inyeccion por campo (field injection). Es la menos recomendada.
  private MoviesFacade moviesFacade;

  @Autowired //Inyeccion por campo (field injection). Es la menos recomendada.
  private RentJpaRepository repository;

  @Override
  public Rent createRent(RentRequest request) {

    Movie movie = moviesFacade.getMovie(String.valueOf(request.getMovieId()));

    // Can't rent again without returning it first
    if( repository.existsByCustomerNameAndMovieIdAndReturnedOnIsNull(request.getCustomerName(), movie.getId())) {
      throw new MovieAlreadyRentedException("The movie is already rented and has not been returned.");
    } else {
      Rent rent = new Rent();
      rent.setCustomerName(request.getCustomerName());
      rent.setMovieId(movie.getId());
      rent.setRentedOn(new Timestamp(System.currentTimeMillis()));
      repository.save(rent);
      return rent;
    }
  }

  @Override
  public Rent returnRented(ReturnRequest request) {

    Rent rent = repository.findById(request.getRentId()).orElse(null);

    if (rent == null)
      throw new RentNotFoundException("Rent not found.");

    // Can't rent again without returning it first
    if( repository.existsByIdAndReturnedOnIsNotNull(request.getRentId()) ) {
      throw new MovieAlreadyReturnedException("The movie is already returned.");
    } else {
      rent.setReturnedOn(new Timestamp(System.currentTimeMillis()));
      repository.save(rent);
      return rent;
    }
  }

  @Override
  public Rent getRent(String id) {
    return repository.findById(Long.valueOf(id)).orElse(null);
  }

  @Override
  public List<Rent> getRents() {
    List<Rent> rents = repository.findAll();
    return rents.isEmpty() ? null : rents;
  }
}
