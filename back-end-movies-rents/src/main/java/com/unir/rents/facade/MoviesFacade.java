package com.unir.rents.facade;

import com.unir.rents.model.Movie;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
@Slf4j
public class MoviesFacade {

  @Value("${getMovie.url}")
  private String getMovieUrl;

  private final RestTemplate restTemplate;

  public Movie getMovie(String id) {

    try {
      String url = String.format(getMovieUrl, id);
      log.info("Getting movie with ID {}. Request to {}", id, url);
      return restTemplate.getForObject(url, Movie.class);
    } catch (HttpClientErrorException e) {
      log.error("Client Error: {}, Movie with ID {}", e.getStatusCode(), id);
      return null;
    } catch (HttpServerErrorException e) {
      log.error("Server Error: {}, Movie with ID {}", e.getStatusCode(), id);
      return null;
    } catch (Exception e) {
      log.error("Error: {}, Movie with ID {}", e.getMessage(), id);
      return null;
    }
  }

}
