package com.unir.rents.data;

import com.unir.rents.model.db.Rating;
import com.unir.rents.model.db.Rent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RatingsJpaRepository extends JpaRepository<Rating, Long> {
    boolean existsByCustomerNameAndMovieId(String customerName, Long movieId);
}
