package com.unir.rents.data;

import com.unir.rents.model.db.Purchase;
import com.unir.rents.model.db.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchasesJpaRepository extends JpaRepository<Purchase, Long> {
    boolean existsByCustomerNameAndMovieId(String customerName, Long id);
}
