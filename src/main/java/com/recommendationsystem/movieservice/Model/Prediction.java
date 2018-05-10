package com.recommendationsystem.movieservice.Model;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.persistence.Entity;
import java.util.HashMap;
import java.util.Map;


public class Prediction {



    private Long movieId;

    private String title;

    private Double predictionRating;


    private Map<Long, Map<Long, Double>> data;

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getPredictionRating() {
        return predictionRating;
    }

    public void setPredictionRating(Double predictionRating) {
        this.predictionRating = predictionRating;
    }

    public Map<Long, Map<Long, Double>> getData() {
        return data;
    }

    public void setData(Map<Long, Map<Long, Double>> data) {
        this.data = data;
    }



    public Prediction() {
    }

    public Prediction(Map<Long, Map<Long, Double>> data) {
        this.data = data;
    }
}
