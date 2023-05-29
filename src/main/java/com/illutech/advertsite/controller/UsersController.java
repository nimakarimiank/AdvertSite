package com.illutech.advertsite.controller;

import com.illutech.advertsite.dto.responses.AuthenticationResponse;
import com.illutech.advertsite.entities.Users;
import com.illutech.advertsite.error.UserNotFoundException;
import com.illutech.advertsite.models.AuthenticationRequest;
import com.illutech.advertsite.models.UsersModel;
import com.illutech.advertsite.service.userservice.IUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

import static com.illutech.advertsite.controller.utilitymethods.UsersUtilityMethods.handleUserType;

@RestController
@RequestMapping("/users/") //DEFAULT PATH
public class UsersController {
    @Autowired
    IUsersService usersService;



    @PostMapping("/set")
    public Users createUsersForAdmins(@RequestBody UsersModel model){
        Users tmp = new Users();
        tmp.setPassword(model.getPassword());
        tmp.setUserName(model.getUserName());
        tmp.setEmailAddress(model.getEmailAddress());
        tmp.setUserType(handleUserType(model.getUserType()));
        usersService.save(tmp);
        return tmp;
    }
    @PostMapping("/setUsers")
    public ResponseEntity<AuthenticationResponse> createUsersAccount(@RequestBody UsersModel model){return ResponseEntity.ok(usersService.register(model));}
    @PostMapping("/setUsersValidate")
    public ResponseEntity<AuthenticationResponse> validateUsers(
            @RequestBody AuthenticationRequest authenticationRequest
    ){
        return ResponseEntity.ok(usersService.authenticate(authenticationRequest));
    }

    @PostMapping("/setAdmins")
    public Users createAdminAccount(@RequestBody UsersModel model){
        Users tmp = new Users();
        tmp.setPassword(model.getPassword());
        tmp.setUserName(model.getUserName());
        tmp.setUserType(handleUserType("ADMIN"));
        usersService.save(tmp);
        return tmp;
    }
    @PostMapping("/DeleteById/{id}")
    public Users DeleteById(@PathVariable Long id) throws UserNotFoundException {
        if (usersService.exists(id)) return usersService.DeleteById(id);
        else throw new UserNotFoundException("کاربر مورد نظر پیدا نشد.");

    }
    @GetMapping("/getUsers")
    public Collection<Users> getUsersList(){
    return usersService.getUsersListByRole("USER");

    }
    @GetMapping("/getAdmins")
    public Collection<Users> getAdminList(){
        return usersService.getUsersListByRole("ADMIN");

    }
}
