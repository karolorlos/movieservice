package com.recommendationsystem.movieservice.Service;

import com.recommendationsystem.movieservice.Model.Movie;
import com.recommendationsystem.movieservice.Model.Rating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RatingService {

    @Autowired
    private RatingRepository ratingRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private UserService userService;





    public RatingService(RatingRepository ratingRepository) { this.ratingRepository = ratingRepository;
    }


    // Rating findRating

    public void updateRating(int id, Rating rating){
        ratingRepository.save(rating);

    }
    //wyswietl chronologiczna liste filmow wedlug movieid i dodaj do listy pole z wartoscia nazwa filmu
    public List<Rating> findAllRatingsUser(Long userId){

        List<Rating> listRating = new ArrayList<>();
        for (Rating r: ratingRepository.findAllByUserId(userId)
             ) {
            listRating.add(r);
        }
        return listRating;
    }


    public List<Rating> findAllRatingWithMovieTitles(Long userId){
        List<Rating> listRatingsWithTitles = new ArrayList<>();


        for (Rating rating: ratingRepository.findAllByUserId(userId)
             ) {
            Movie movie = movieRepository.findByMovieId(rating.getMovieId());
            rating.setMovieTitle(movie.getTitle());
            listRatingsWithTitles.add(rating);
        }
        return listRatingsWithTitles;
    }

    public Map<Long, Map<Long, Double>> createMapUsersWithMoviesAndRatings(){
        Map<Long, Map<Long, Double>> mapUsersWithMoviesAndRatings = new HashMap<>();
        List<Long> listOfUsers = new ArrayList<>();

        listOfUsers = userService.createListOfUsersId();


        for (Long user: listOfUsers
             ) {

                HashMap<Long,Double> moviesAndRatings = new HashMap<>();



            for (Rating rating: ratingRepository.findAllByUserId(user)
                 ) {
                moviesAndRatings.put(rating.getMovieId(),rating.getRating());
            }
            mapUsersWithMoviesAndRatings.put(user,moviesAndRatings);
        }

        return mapUsersWithMoviesAndRatings;
    }
    public Map<Long, Double> createMapWithCurrentUserAndRatings(Long userId){
        Map<Long, Double> mapCurrentUser = new HashMap<>();

        for (Rating rating: ratingRepository.findAllByUserId(userId)
             ) {
            mapCurrentUser.put(rating.getMovieId(),rating.getRating());

        }

        return mapCurrentUser;
    }
    public List<Long> createListWithMovieId(){
        List<Long> listWithMoviesId = new ArrayList<>();
        for (Movie movie: movieRepository.findAll()
             ) {
            listWithMoviesId.add(movie.getMovieId());
        }
        return listWithMoviesId;
    }


    public List<Movie> createListMoviesWithAvgRatings(){
        List<Movie> listMoviesWithAvg = new ArrayList<>();

        Double ratingValue;
        int count;
        Double avgRating;
        for (Long movieId: createListWithMovieId()
             ) {
            ratingValue =0.0;
            count = 0;
            avgRating = 0.0;

            for (Rating r: ratingRepository.findAllByMovieId(movieId)
                 ) {
                ratingValue+= r.getRating();
                count++;
            }
            avgRating = ratingValue/count;
            Movie movie = movieRepository.findByMovieId(movieId);
            //movie.setMovieId(movieId);

            movie.setAverageRating(roundAverageRating(avgRating,2));
            listMoviesWithAvg.add(movie);
        }

        return listMoviesWithAvg;

    }
    public Double roundAverageRating(Double avgRating, int places){
        long factor = (long) Math.pow(10,places);
        avgRating = avgRating * factor;
        long tmp = Math.round(avgRating);

        return (double) tmp/factor;
    }

    public List<Movie> createListRatedMoviesByUser(Long userId){
        List<Movie> listRateMoviesByUser = new ArrayList<>();
        for (Rating rating: ratingRepository.findAllByUserId(userId)
             ) {
            Movie movie = new Movie();
            movie.setMovieId(rating.getMovieId());
            listRateMoviesByUser.add(movie);

        }
        return listRateMoviesByUser;

    }
}
