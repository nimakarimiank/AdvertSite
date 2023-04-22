package com.illutech.advertsite.controller;

import com.illutech.advertsite.controller.utilitymethods.AdvertUtilityClass;
import com.illutech.advertsite.entities.Advert;
import com.illutech.advertsite.entities.subentitites.UsersType;
import com.illutech.advertsite.models.AdvertModel;
import com.illutech.advertsite.service.advertservice.IAdvertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RequestMapping("/advert")
@RestController
public class AdvertController {
    //TODO: GET nonVALIDATED ADVERT LIST
    @Autowired
    IAdvertService advertService;



    @GetMapping("/pending/{status}")
    public Collection<Advert> getPendingAdverts(@PathVariable boolean status){
        //TODO: validate current user role
        if (UsersType.ADMIN==AdvertUtilityClass.getCurrentUserRole(true))
        {
          return  advertService.getPendingAdverts(status);
        }
        else return new ArrayList<>();
    }
    @PostMapping("/validate/{id}")
    public Advert validateAdvert(@PathVariable Long id){
        if (advertService.exists(id)){
            Advert tmp = advertService.findById(id);
            tmp.setValidated(true);
            advertService.save(tmp);
            return tmp;
        }
        return null;
    }
    @PostMapping("/batchValidate")
    public Collection<Advert> batchValidateAdvertList(@RequestBody List<Long> Ids){
        Collection<Advert> advertList = new ArrayList<>();
        for (Long id:Ids
             ) {

            advertList.add(validateAdvert(id));
        }
        return advertList;
    }
    @PostMapping("/unValidate/{id}")
    public Advert unValidateAdvert(@PathVariable Long id){
        if (advertService.exists(id)){
            Advert tmp = advertService.findById(id);
            tmp.setValidated(false);
            advertService.save(tmp);
            return tmp;
        }
        return null;
    }
    @PostMapping("/addAdvert")
    public Advert createNewAdvert(@RequestBody AdvertModel model){
        Advert tmp = new Advert(
                model.getAdvertText(),
                false,false
        );
         return advertService.save(tmp);

    }
    @PostMapping("/virtualDelete/{id}")
    public Advert virtualDelete(@PathVariable("id") Long id){

        if (advertService.exists(id))
        {
            return advertService.virtualDeleteById(id);
        }
        return null;
    }
    @PostMapping("/restoreAdvert/{id}")
    public Advert restoreVirtualDeletedAdvert(@PathVariable("id") Long id){
        if (advertService.exists(id)) return advertService.undoDelete(id);
        return null;
    }
    @PostMapping("/hardDelete/{id}")
    public Advert hardDelete(@PathVariable Long id){
        return (advertService.exists(id) ? advertService.hardDelete(id) : null);
    }
    @GetMapping("/userView")
    public Collection<Advert> getValidatedAdvertList(){
        return advertService.getValidatedAdvertList();
    }
    @GetMapping("/adminView")
    public Collection<Advert> getAllAdvertList(){
        return advertService.getAdvertList();
    }
}
