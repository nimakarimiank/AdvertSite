package com.illutech.advertsite.service.userservice;

import com.illutech.advertsite.controller.utilitymethods.UsersUtilityMethods;
import com.illutech.advertsite.dto.responses.AuthenticationResponse;
import com.illutech.advertsite.entities.Users;
import com.illutech.advertsite.entities.subentitites.UsersType;
import com.illutech.advertsite.models.AuthenticationRequest;
import com.illutech.advertsite.models.UsersModel;
import com.illutech.advertsite.repository.usersrepository.UsersRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

import static com.illutech.advertsite.controller.utilitymethods.UsersUtilityMethods.handleUserType;

@Service
@RequiredArgsConstructor
public class UsersService implements IUsersService{
    private final UsersRepository repository; //this + @RequiredArgsConstructor = @Autowired
    @Override
    public void save(Users tmp) {
        repository.save(tmp);
    }

    @Override
    public Users DeleteById(Long id) {
        Optional<Users> tmp = Optional.ofNullable(repository.findById(id).orElseThrow(EntityNotFoundException::new));
        if (tmp.isPresent()){
            repository.deleteById(id);
            return tmp.get();
        }
        return null;
    }

    @Override
    public Collection<Users> getUsersListByRole(String user) {
        UsersType type = UsersUtilityMethods.handleUserType(user);
        return repository.findAllByUserType(type);
    }

    @Override
    public boolean exists(Long id) {
        return repository.existsById(id);
    }

    @Override
    public AuthenticationResponse register(UsersModel model) {
        Users tmp = new Users();
        tmp.setPassword(model.getPassword());
        tmp.setUserName(model.getUserName());
        tmp.setUserType(handleUserType("USER"));
        save(tmp);
        return null; //TODO
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) {
        return null;
    }

}
