package com.recommendationsystem.movieservice.Service;

import com.recommendationsystem.movieservice.Model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
    User findUserByUserId(Long userId);

}
