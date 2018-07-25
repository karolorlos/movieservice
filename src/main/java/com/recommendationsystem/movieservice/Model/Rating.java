package com.recommendationsystem.movieservice.Model;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "rating")
public class Rating {

    @Id
    @GeneratedValue
    @Column(name = "rating_id", nullable = false)
    private Long ratingId;

    @Column(name = "rating_value")
    private Double ratingValue;

    @Column(name = "movie_id")
    private Long movieId;


    @Column(name = "user_id")
    private Long userId;

    private String movieTitle;

    @ManyToMany(mappedBy = "userRatings")
    private Set<User> uRatings;

    public Rating() {
    }

    public Rating(Double ratingValue) {
        this.ratingValue = ratingValue;
    }

    public Rating(Double ratingValue, Long movieId, Long userId) {
        super();

        this.ratingValue = ratingValue;
        this.movieId = movieId;
        this.userId = userId;
    }

    public Long getRatingId() {
        return ratingId;
    }

    public void setRatingId(Long ratingId) {
        this.ratingId = ratingId;
    }

    public double getRating() {
        return ratingValue;
    }

    public void setRating(double ratingValue) {
        this.ratingValue = ratingValue;
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

    public Set<User> getuRatings() {
        return uRatings;
    }

    public void setuRatings(Set<User> uRatings) {
        this.uRatings = uRatings;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }
}
