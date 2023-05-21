package com.illutech.advertsite.entities;

import com.illutech.advertsite.entities.subentitites.UsersType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Entity
@Data
@Builder
@AllArgsConstructor
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String emailAddress;
    private String userName;
    private String password;
    @Enumerated(EnumType.STRING)
    @Column(name = "users_type")
    private UsersType userType;

    public Users(String userName, String password, UsersType userType) {
        this.userName = userName;
        this.password = password;
        this.userType = userType;
    }

    public String getUserType() {
        return userType.toString();
    }

    public void setUserType(UsersType userType) {
        this.userType = userType;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Long getId() {
        return id;
    }


    public Users() {
    }
    public String getEmailAddress() {
        return emailAddress;
    }
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
