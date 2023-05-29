package com.illutech.advertsite.service.userservice;

import com.illutech.advertsite.config.customconfigs.CustomUserDetails;
import com.illutech.advertsite.config.jwtconfig.JwtService;
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
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

import static com.illutech.advertsite.controller.utilitymethods.UsersUtilityMethods.handleUserType;

@Service
@RequiredArgsConstructor
public class UsersService implements IUsersService{
    private final UsersRepository repository; //this + @RequiredArgsConstructor = @Autowired
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
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
        var tmp = Users.builder()
                        .password(passwordEncoder.encode(model.getPassword()))
                        .userName(model.getUserName())
                        .userType(handleUserType("USER")).build();
        save(tmp);
        var jwtToken = jwtService.generateToken(new CustomUserDetails(tmp));

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                authenticationRequest.getUsername(),
                authenticationRequest.getPassword()
        ));
        var user = repository.findByUserName(authenticationRequest.getUsername());

        var jwtToken = jwtService.generateToken(new CustomUserDetails(user));

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
    }

}
