package com.unir.rents.service;

import com.unir.rents.data.PurchasesJpaRepository;
import com.unir.rents.data.RentJpaRepository;
import com.unir.rents.exceptions.MovieAlreadyPurchasedException;
import com.unir.rents.exceptions.MovieAlreadyRentedException;
import com.unir.rents.exceptions.MovieAlreadyReturnedException;
import com.unir.rents.exceptions.RentNotFoundException;
import com.unir.rents.facade.MoviesFacade;
import com.unir.rents.model.Movie;
import com.unir.rents.model.db.Purchase;
import com.unir.rents.model.db.Rent;
import com.unir.rents.model.request.PurchaseRequest;
import com.unir.rents.model.request.RentRequest;
import com.unir.rents.model.request.ReturnRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class PurchasesServiceImpl implements PurchasesService {

  @Autowired //Inyeccion por campo (field injection). Es la menos recomendada.
  private MoviesFacade moviesFacade;

  @Autowired //Inyeccion por campo (field injection). Es la menos recomendada.
  private PurchasesJpaRepository repository;

  @Override
  public Purchase purchase(PurchaseRequest request) {

    Movie movie = moviesFacade.getMovie(String.valueOf(request.getMovieId()));

    if( repository.existsByCustomerNameAndMovieId(request.getCustomerName(), movie.getId())) {
      throw new MovieAlreadyPurchasedException("The movie is already purchased.");
    } else {
      Purchase purchase = new Purchase();
      purchase.setCustomerName(request.getCustomerName());
      purchase.setMovieId(movie.getId());
      return repository.save(purchase);
    }
  }

  @Override
  public List<Purchase> getPurchases() {
    List<Purchase> purchases = repository.findAll();
    return purchases.isEmpty() ? null : purchases;
  }

  @Override
  public Purchase getPurchase(String id) {
    return repository.findById(Long.valueOf(id)).orElse(null);
  }
}
