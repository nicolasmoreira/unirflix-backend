package com.unir.movies.data;

import com.unir.movies.model.pojo.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

interface MovieJpaRepository extends JpaRepository<Movie, Long>, JpaSpecificationExecutor<Movie> {

}