package com.illutech.advertsite.service.advertservice;

import com.illutech.advertsite.entities.Advert;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public interface IAdvertService {
    Collection<Advert> getPendingAdverts(boolean status);

    Advert save(Advert tmp);

    boolean exists (Long id);

    Advert virtualDeleteById(Long id);

    Advert hardDelete(Long id);

    Advert findById(Long id);

    Collection<Advert> getValidatedAdvertList();

    Collection<Advert> getAdvertList();

    Advert undoDelete(Long id);
}
