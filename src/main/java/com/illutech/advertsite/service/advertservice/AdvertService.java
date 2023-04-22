package com.illutech.advertsite.service.advertservice;

import com.illutech.advertsite.entities.Advert;
import com.illutech.advertsite.repository.advertrepository.AdvertRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class AdvertService implements IAdvertService{
    @Autowired
    AdvertRepository repository;

    @Override
    public Collection<Advert> getPendingAdverts(boolean status) {
        return repository.findAllByValidated(status);
    }

    @Override
    public Advert save(Advert tmp) {
       return repository.save(tmp);

    }

    @Override
    public boolean exists(Long id) {
        return repository.existsById(id);
    }

    @Override
    public Advert virtualDeleteById(Long id) {
        Advert tmp =  repository.findById(id).get();
        tmp.setDeleted(true);
        repository.save(tmp);
        return tmp;
    }

    @Override
    public Advert hardDelete(Long id) {
        Advert tmp = repository.findById(id).get();
        repository.deleteById(id);
        return tmp;
    }

    @Override
    public Advert findById(Long id) {
        return repository.findById(id).get();
    }

    @Override
    public Collection<Advert> getValidatedAdvertList() {
        return repository.findAllByValidated(true);
    }

    @Override
    public Collection<Advert> getAdvertList() {
        return repository.findAllByDeleted(false);
    }

    @Override
    public Advert undoDelete(Long id) {
        if (repository.existsById(id))
        {
            Advert tmp = repository.findById(id).get();
            tmp.setDeleted(false);
            return repository.save(tmp);
        }
        else return null;
    }
}
