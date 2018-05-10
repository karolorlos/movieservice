package com.recommendationsystem.movieservice.Controller;


import com.recommendationsystem.movieservice.Service.RatingService;

import com.recommendationsystem.movieservice.Service.SlopeOneService;
import com.recommendationsystem.movieservice.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
public class SlopeOneController {


    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    private RatingService ratingService;
    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    private UserService userService;
    @Autowired
    private SlopeOneService slopeOneService;


    @RequestMapping(value = "addRecommendations", method = RequestMethod.GET)
    public String addRecommendation(HttpServletRequest request, Model model){
        Map<Long, Map<Long, Double>> mData = ratingService.createMapUsersWithMoviesAndRatings();
        Long currUser = userService.getUserId(request.getUserPrincipal().getName());
        Map<Long, Double> user = ratingService.createMapWithCurrentUserAndRatings(currUser);

        slopeOneService.buildDiffMatrix(mData);

        slopeOneService.printData(mData);

        System.out.println("This is database with userId, moviesId and ratings");

        System.out.println("This is rates from current user");
        slopeOneService.print(user);

        System.out.println("Receive prediction: ");
        slopeOneService.print(slopeOneService.predict(user));

        System.out.println("Recommend movie for you is: ");
        slopeOneService.printBestOption(slopeOneService.predict(user));

        model.addAttribute("predictions",slopeOneService.findAllPredictions(slopeOneService.predict(user),currUser));




        return "recommendations";
    }
}
