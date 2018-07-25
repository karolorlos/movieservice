package com.recommendationsystem.movieservice.Model;

import com.recommendationsystem.movieservice.SecurityConfig;
import com.recommendationsystem.movieservice.Service.UserAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.Column;

@Component
public class RateForm {
    private Long rateId;
    private double rateValue;
    private Long movieId;

    private String username;

    private Long userId;



    public Long getRateId() {
        return rateId;
    }

    public void setRateId(Long rateId) {
        this.rateId = rateId;
    }

    public double getRateValue() {
        return rateValue;
    }

    public void setRateValue(double rateValue) {
        this.rateValue = rateValue;
    }

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public RateForm() {
    }

    public RateForm(Long movieId) {
        this.movieId = movieId;
    }

    public RateForm(Long rateId, double rateValue, Long movieId, Long userId) {
        this.rateId = rateId;
        this.rateValue = rateValue;
        this.movieId = movieId;
        this.userId = userId;
    }
}
