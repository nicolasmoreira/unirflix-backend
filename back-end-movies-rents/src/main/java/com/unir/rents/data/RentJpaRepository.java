package com.unir.rents.data;

import com.unir.rents.model.db.Rent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentJpaRepository extends JpaRepository<Rent, Long> {
    boolean existsByCustomerNameAndMovieIdAndReturnedOnIsNull(String customerName, Long movieId);

    boolean existsByIdAndReturnedOnIsNotNull(Long rentId);
}
