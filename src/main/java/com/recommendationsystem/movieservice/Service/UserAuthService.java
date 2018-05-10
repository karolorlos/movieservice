package com.recommendationsystem.movieservice.Service;

import com.recommendationsystem.movieservice.Model.RateForm;
import com.recommendationsystem.movieservice.Model.Rating;
import com.recommendationsystem.movieservice.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserAuthService implements UserDetailsService{

    private final UserRepository repository;
    Long currUserId;

    public Long getCurrUserId() {
        return currUserId;
    }


    @Autowired
    public UserAuthService(UserRepository repository){this.repository = repository;}

    @Autowired
    private RateForm rateForm;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{

        User curruser = repository.findByUsername(username);

        UserDetails user = new org.springframework.security.core.userdetails.User(username,curruser.getPasswordHash(),true,
                true,true,true, AuthorityUtils.createAuthorityList(curruser.getRole()));

        System.out.println("ROLE: " + curruser.getRole());
        currUserId = curruser.getUserId();
        System.out.println("User ID: "+ currUserId);
        rateForm.setUserId(currUserId);
        showUserId();
        return user;
    }
    public Long showUserId(){

        return currUserId;
    }
}
