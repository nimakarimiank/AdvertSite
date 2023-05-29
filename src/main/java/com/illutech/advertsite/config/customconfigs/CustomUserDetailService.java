package com.illutech.advertsite.config.customconfigs;

import com.illutech.advertsite.entities.Users;
import com.illutech.advertsite.repository.usersrepository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    UsersRepository repository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users tmp = repository.findByUserName(username);
        if (tmp==null) throw new UsernameNotFoundException("User has not been found");
        return new CustomUserDetails(tmp);
    }
}
