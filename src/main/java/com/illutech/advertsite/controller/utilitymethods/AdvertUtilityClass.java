package com.illutech.advertsite.controller.utilitymethods;

import com.illutech.advertsite.entities.subentitites.UsersType;
import com.illutech.advertsite.service.advertservice.IAdvertService;
import org.springframework.beans.factory.annotation.Autowired;

public class AdvertUtilityClass {
    @Autowired
    static
    IAdvertService advertService;
    public static UsersType getCurrentUserRole(
            //temporary
            boolean roleNo
            //
    ){

        //to be implemented
        //
        return(roleNo? UsersType.ADMIN:UsersType.USER);
    }
    public static boolean checkRecords(Long id){
       return advertService.exists(id);
    }
}
