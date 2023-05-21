package com.illutech.advertsite.controller.utilitymethods;

import com.illutech.advertsite.entities.Advert;
import com.illutech.advertsite.entities.subentitites.UsersType;
import com.illutech.advertsite.service.advertservice.IAdvertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class AdvertUtilityClass {
    @Autowired
    static IAdvertService advertService;
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
    public static Collection<Advert> getCurrentUSDPrice(Collection<Advert> advertList){
        int currentUSDPrice= Integer.parseInt(getUSD());
        Collection<Advert> updatedList = new ArrayList<>();
        for (Advert item:
             advertList) {
            int tmp = Integer.parseInt(item.getPrice())*currentUSDPrice;
            item.setPrice(Integer.toString(tmp));
            updatedList.add(item);
        }
        return updatedList;

    }
    private static String getUSD(){
        String URI = "http://api.navasan.tech/latest/?item=usd_sell&api_key=freekGHudeXXPV03kaYFXFpjpOI8otml";
        RestTemplate tmp = new RestTemplate();
        HashMap<String, Map> result = tmp.getForObject(URI, new HashMap<>().getClass());
        String price =
                result.get("usd_sell").get("value").toString();

        return price;
    }
}
