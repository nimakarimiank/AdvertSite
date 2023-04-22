package com.illutech.advertsite.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsersModel {
    private String userName;
    private String password;
    private String userType;
}
