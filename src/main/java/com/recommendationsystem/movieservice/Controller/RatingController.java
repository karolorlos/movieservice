package com.recommendationsystem.movieservice.Controller;


import com.recommendationsystem.movieservice.Model.*;
import com.recommendationsystem.movieservice.SecurityConfig;
import com.recommendationsystem.movieservice.Service.*;
import org.hibernate.service.spi.InjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
public class RatingController {

    @Autowired
    private RatingRepository ratingRepository;
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private RatingService ratingService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/addRating/{id}", method = RequestMethod.GET)
    public String addRate(@PathVariable("id") Long movieId, Model model){
        model.addAttribute("rateform", new RateForm(movieId));
        model.addAttribute("movie", movieRepository.findById(movieId).get());
        return "rateform";
    }


    @Transactional
    @RequestMapping(value = "saverate", method = RequestMethod.POST)
    public String saveRate(@ModelAttribute("rateform")RateForm rateForm, BindingResult bindingResult,HttpServletRequest request){
        ratingRepository.deleteRatingByMovieId(rateForm.getMovieId());
        Long cUid = userService.getUserId(request.getUserPrincipal().getName());

        if (!bindingResult.hasErrors()){
                Rating newRate = new Rating();
                newRate.setMovieId(rateForm.getMovieId());
                newRate.setRating(rateForm.getRateValue());
                newRate.setUserId(cUid);
                ratingRepository.save(newRate);

                return "redirect:/";
            }
        return "rateform";
    }
}
