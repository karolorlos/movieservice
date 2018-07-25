package com.recommendationsystem.movieservice.Service;

import com.recommendationsystem.movieservice.Model.Movie;
import com.recommendationsystem.movieservice.Model.Rating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private RatingService ratingService;

    public List<Movie> findAllMoviesWithRatings(Long userId){

        List<Rating> listRat = ratingService.findAllRatingsUser(userId);
        List<Movie> listMoviesAndRates = new ArrayList<>();
        for (Movie movie: movieRepository.findAll()
             ) {
            for (Rating rating: listRat
                 ) {
                if (movie.getMovieId() == rating.getMovieId()){
                    movie.setRating(rating.getRating());
                    listMoviesAndRates.add(movie);
                }
            }
        }
        return listMoviesAndRates;
    }


    public List<Long> createListOfMoviesId(){

        List<Long> listOfMoviesId = new ArrayList<>();
        Long  currMovieId = null;
        for (Movie movie: movieRepository.findAll()
             ) {
            currMovieId = movie.getMovieId();
            listOfMoviesId.add(currMovieId);
        }
        return listOfMoviesId;
    }

    public String findTitleByMovieId(Long movieId){
        String title;
        Movie movie = movieRepository.findByMovieId(movieId);
        title = movie.getTitle();
        return title;
    }

}
