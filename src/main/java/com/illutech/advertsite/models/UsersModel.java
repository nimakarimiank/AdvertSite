package com.illutech.advertsite.models;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UsersModel {
    private String userName;
    private String password;
    private String matchingPassword;
    private String emailAddress;
    private String userType;
}
