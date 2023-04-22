package com.illutech.advertsite.controller.utilitymethods;
import java.util.Objects;
import com.illutech.advertsite.entities.subentitites.UsersType;


public class UsersUtilityMethods {

    public static UsersType handleUserType(String userType) {
        if (Objects.equals(userType, "ADMIN")) return UsersType.ADMIN;
        else return UsersType.USER;
    }
}
