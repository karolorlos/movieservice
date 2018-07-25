package com.recommendationsystem.movieservice.Controller;


import com.recommendationsystem.movieservice.Model.Movie;
import com.recommendationsystem.movieservice.Model.Rating;
import com.recommendationsystem.movieservice.Model.User;
import com.recommendationsystem.movieservice.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@Controller
public class MovieController {

    @Autowired
    private MovieRepository movieRepository;


    @Autowired
    private RatingRepository ratingRepository;
    @Autowired
    private UserService userService;

    @Autowired
    private RatingService ratingService;

    @Autowired
    private MovieService movieService;

    @RequestMapping(value = "/login")
    public String login(){return "login";}

    @RequestMapping(value = "/")
    public String indexPage(Model model){

        List<Movie> moviesLastAdded = movieRepository.findTop4ByOrderByMovieIdDesc();
        model.addAttribute("movies", moviesLastAdded);
        return "index";
    }
    @RequestMapping(value = "/yourRatings")
    public String findUserRatings(Model model, HttpServletRequest request){

        List<Movie> moviesWithRatings = movieService.findAllMoviesWithRatings(userService.getUserId(request.getUserPrincipal().getName()));
        model.addAttribute("movies",moviesWithRatings);
        return "ratingMovies";

    }

    @RequestMapping(value = "/movies")
    public String findAllMovies(Model model){
//        List<Movie> movies = (List<Movie>) movieRepository.findAll();
         List<Movie> movies = ratingService.createListMoviesWithAvgRatings();
        model.addAttribute("movies",movies);

        return "movies";

    }

    @RequestMapping(value = "/showDetails/{id}")
    public String showMovieDetails(@PathVariable("id") Long movieId, Model model){
        Movie movie = movieRepository.findByMovieId(movieId);

        model.addAttribute("movie", movie);
        return "movieDetails";
    }

    @RequestMapping(value ="addMovieRating/{id}",method = RequestMethod.GET)
    public String addRate(@PathVariable("id") Long movieId,@RequestParam Long ratingId, Model model){

        model.addAttribute("rating", ratingRepository.findById(ratingId));
        model.addAttribute("movie", movieRepository.findById(movieId).get());
        return "addMovieRating";
    }


    @RequestMapping(value = "/movie/{id}/rating",method = RequestMethod.GET)
    public String rateMovie(@RequestParam(value = "action") String action, @PathVariable Long id, @RequestParam Long ratingId, Model model){
        Optional<Rating> rating = ratingRepository.findById(ratingId);
        Optional<Movie> movie = movieRepository.findById(id);

        if (movie.isPresent() && action.equalsIgnoreCase("save")) {

            movieRepository.save(movie.get());
            model.addAttribute("rating", ratingRepository.findById(ratingId));
            model.addAttribute("movie", movieRepository.findAll());
            return "redirect:/movies";
        }
        return "redirect:/movies";
    }
}
