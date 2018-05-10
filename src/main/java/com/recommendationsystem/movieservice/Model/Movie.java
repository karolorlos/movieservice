package com.recommendationsystem.movieservice.Model;

import org.springframework.data.jpa.repository.Query;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "movie")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "movie_id", nullable = false)
    private Long movieId;
    @Column(name = "title")
    private String title;
    @Column(name = "category")
    private String category;
    @Column(name = "year")
    private int year;
    @Column(name = "description")
    private String description;
    @Column(name = "image")
    private String image;

    private Double rating;



    private Double averageRating;

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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }
    public double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Movie() {
    }

    public Movie(String title, String category, int year) {
        this.title = title;
        this.category = category;
        this.year = year;
    }

    public Movie(String title, String category, int year, String description, String image) {
        this.title = title;
        this.category = category;
        this.year = year;
        this.description = description;
        this.image = image;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + movieId +
                ", title='" + title + '\'' +
                ", category='" + category + '\'' +
                ", year=" + year +
                '}';
    }
}
