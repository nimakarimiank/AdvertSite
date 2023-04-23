package com.illutech.advertsite.controller;

import com.illutech.advertsite.controller.utilitymethods.AdvertUtilityClass;
import com.illutech.advertsite.entities.Advert;
import com.illutech.advertsite.entities.subentitites.UsersType;
import com.illutech.advertsite.error.AdvertNotFoundException;
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
    @Autowired
    IAdvertService advertService;



    @GetMapping("/pending/{status}")
    public Collection<Advert> getPendingAdverts(@PathVariable boolean status)
    throws AdvertNotFoundException {
        //TODO: validate current user role
        if (UsersType.ADMIN==AdvertUtilityClass.getCurrentUserRole(true))
        {
          return  advertService.getPendingAdverts(status);
        }
        else throw new AdvertNotFoundException("تبلیغی در لیست انتظار تایید نیست");
    }
    @PostMapping("/validate/{id}")
    public Advert validateAdvert(@PathVariable Long id)
    throws AdvertNotFoundException{
        if (advertService.exists(id)){
            Advert tmp = advertService.findById(id);
            tmp.setValidated(true);
            advertService.save(tmp);
            return tmp;
        }
        else throw new AdvertNotFoundException("تبلیغ مورد نظر وجود ندارد");
    }
    @PostMapping("/batchValidate")
    public Collection<Advert> batchValidateAdvertList(@RequestBody List<Long> Ids)
            throws AdvertNotFoundException {
        Collection<Advert> advertList = new ArrayList<>();
        for (Long id:Ids
             ) {
            if (advertService.exists(id)) advertList.add(validateAdvert(id));
            else throw new AdvertNotFoundException("تبلیغ مورد نظر شما وجود ندارد. شماره آی دی تبلیغ{id}$");
        }
        return advertList;
    }
    @PostMapping("/unValidate/{id}")
    public Advert unValidateAdvert(@PathVariable Long id)
    throws AdvertNotFoundException{
        if (advertService.exists(id)){
            Advert tmp = advertService.findById(id);
            tmp.setValidated(false);
            advertService.save(tmp);
            return tmp;
        }
        else throw new AdvertNotFoundException("ابلیغ مورد نظر وجود ندارد");
    }
    @PostMapping("/addAdvert")
    public Advert createNewAdvert(@RequestBody AdvertModel model) {

        Advert tmp = new Advert(
                model.getAdvertText(),
                false,false
        );
         return advertService.save(tmp);

    }
    @PostMapping("/virtualDelete/{id}")
    public Advert virtualDelete(@PathVariable("id") Long id)
    throws AdvertNotFoundException{

        if (advertService.exists(id))
        {
            return advertService.virtualDeleteById(id);
        }
        else throw new AdvertNotFoundException("آی دی پیدا نشد");
    }
    @PostMapping("/restoreAdvert/{id}")
    public Advert restoreVirtualDeletedAdvert(@PathVariable("id") Long id)
    throws AdvertNotFoundException{
        if (advertService.exists(id)) return advertService.undoDelete(id);
        else throw new AdvertNotFoundException("آی دی مورد نظر اشتباه است.");
    }
    @PostMapping("/hardDelete/{id}")
    public Advert hardDelete(@PathVariable Long id)throws AdvertNotFoundException{

        if (advertService.exists(id)) return advertService.hardDelete(id);
        else throw new AdvertNotFoundException("تبلیغ مورد نظر وجود ندارد");
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
