package com.unir.rents.service;

import com.unir.rents.model.db.Rating;
import com.unir.rents.model.request.PatchRatingRequest;
import com.unir.rents.model.request.RatingRequest;

import java.util.List;
import java.util.Optional;

public interface RatingsService {

	Rating createRating(RatingRequest request);

	Rating editRating(String id, PatchRatingRequest request);

	Rating getRating(String id);

	List<Rating> getRatings();

}
