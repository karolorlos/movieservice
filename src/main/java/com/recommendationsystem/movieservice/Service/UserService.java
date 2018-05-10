package com.recommendationsystem.movieservice.Service;

import com.recommendationsystem.movieservice.Model.User;
import com.recommendationsystem.movieservice.SecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService  {
    @Autowired
    private UserRepository userRepository;



    public Long getUserId(String username){

        User currUser = userRepository.findByUsername(username);

        return currUser.getUserId();
    }


    public List<Long> createListOfUsersId(){
        List<Long> listOfUsersId = new ArrayList<>();
        Long currUserId = null;
        for (User user: userRepository.findAll()
             ) {
            currUserId = user.getUserId();
            listOfUsersId.add(currUserId);
        }
        return listOfUsersId;
    }




}
