package com.unir.rents.service;

import com.unir.rents.data.RentJpaRepository;
import com.unir.rents.model.Movie;
import com.unir.rents.model.db.Rent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    List<Movie> movies = request.getMovies().stream().map(moviesFacade::getMovie).filter(Objects::nonNull).toList();

    if(movies.size() != request.getMovies().size() || movies.stream().anyMatch(movie -> !movie.getVisible())) {
      return null;
    } else {
      Rent rent = Rent.builder().movies(movies.stream().map(Movie::getId).collect(Collectors.toList())).build();
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
