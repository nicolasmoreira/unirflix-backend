package com.unir.movies.data;

import com.unir.movies.data.utils.SearchCriteria;
import com.unir.movies.data.utils.SearchOperation;
import com.unir.movies.data.utils.SearchStatement;
import com.unir.movies.model.pojo.Movie;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MovieRepository {

    private final MovieJpaRepository repository;

    public List<Movie> getMovies() {
        return repository.findAll();
    }

    public Movie getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Movie save(Movie movie) {
        return repository.save(movie);
    }

    public void delete(Movie movie) {
        repository.delete(movie);
    }

    public List<Movie> search(String title, String director, String year, String synopsis, String actors, String category, String language) {
        SearchCriteria<Movie> spec = new SearchCriteria<>();

        if (StringUtils.isNotBlank(title)) {
            spec.add(new SearchStatement("title", title, SearchOperation.MATCH));
        }

        if (StringUtils.isNotBlank(director)) {
            spec.add(new SearchStatement("director", director, SearchOperation.MATCH));
        }

        if (StringUtils.isNotBlank(year)) {
            spec.add(new SearchStatement("year", year, SearchOperation.EQUAL));
        }

        if (StringUtils.isNotBlank(synopsis)) {
            spec.add(new SearchStatement("synopsis", synopsis, SearchOperation.MATCH));
        }

        if (StringUtils.isNotBlank(category)) {
            spec.add(new SearchStatement("category", category, SearchOperation.MATCH));
        }

        if (StringUtils.isNotBlank(actors)) {
            spec.add(new SearchStatement("actors", actors, SearchOperation.MATCH));
        }

        if (StringUtils.isNotBlank(language)) {
            spec.add(new SearchStatement("language", language, SearchOperation.MATCH));
        }

        return repository.findAll(spec);
    }
}
