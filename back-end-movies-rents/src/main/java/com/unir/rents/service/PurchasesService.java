package com.unir.rents.service;

import com.unir.rents.model.db.Purchase;
import com.unir.rents.model.request.PurchaseRequest;
import com.unir.rents.model.request.RentRequest;

import java.util.List;

public interface PurchasesService {

	Purchase purchase(PurchaseRequest request);

	List<Purchase> getPurchases();

	Purchase getPurchase(String request);

}
