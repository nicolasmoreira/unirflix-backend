package com.unir.rents.service;

import com.unir.rents.model.db.Rent;
import com.unir.rents.model.request.RentRequest;
import java.util.List;

public interface RentsService {

	Rent createRent(RentRequest request);

	Rent getRent(String id);

	List<Rent> getRents();

}
