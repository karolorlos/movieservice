package com.recommendationsystem.movieservice.Service;

import com.recommendationsystem.movieservice.Model.Movie;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;
@Repository
public interface MovieRepository extends CrudRepository<Movie,Long> {

    Movie findByMovieId(Long movieId);


    List<Movie> findTop4ByOrderByMovieIdDesc();

 }
