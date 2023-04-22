package com.illutech.advertsite.service.userservice;

import com.illutech.advertsite.entities.Users;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public interface IUsersService {
    void save(Users tmp);

    Users DeleteById(Long id);

    Collection<Users> getUsersListByRole(String user);
}
