package com.illutech.advertsite.service.userservice;

import com.illutech.advertsite.dto.responses.AuthenticationResponse;
import com.illutech.advertsite.entities.Users;
import com.illutech.advertsite.models.AuthenticationRequest;
import com.illutech.advertsite.models.UsersModel;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public interface IUsersService {
    void save(Users tmp);


    Users DeleteById(Long id);

    Collection<Users> getUsersListByRole(String user);

    boolean exists(Long id);

    AuthenticationResponse register(UsersModel model);

    AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest);
}
