package com.recommendationsystem.movieservice.Model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "username", nullable = false)
    private String username;
    @Column(name = "password", nullable = false)
    private String passwordHash;
    @Column(name = "role", nullable = false)
    private String role;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "user_rate", joinColumns = @JoinColumn(name="user_id"), inverseJoinColumns = @JoinColumn(name = "rating_value"))
    private Set<Rating> userRatings;


    public User() {
    }

    public User(String username, String passwordHash, String role) {
        super();

        this.username = username;
        this.passwordHash = passwordHash;
        this.role = role;
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

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Set<Rating> getUserRatings() {
        return userRatings;
    }

    public void setUserRatings(Set<Rating> userRatings) {
        this.userRatings = userRatings;
    }




}
