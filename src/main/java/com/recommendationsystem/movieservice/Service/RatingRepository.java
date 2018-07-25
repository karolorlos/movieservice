package com.recommendationsystem.movieservice.Service;

import com.recommendationsystem.movieservice.Model.Rating;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface RatingRepository extends CrudRepository<Rating,Long> {
    void deleteRatingByMovieId(Long movieId);
    Iterable<Rating> findAllByUserId(Long userId);
    Iterable<Rating> findAllByMovieId(Long movieId);

    Boolean existsRatingByMovieIdAndUserId(Long movieId, Long userId);

}
