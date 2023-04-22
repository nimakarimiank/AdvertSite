package com.illutech.advertsite.repository.advertrepository;

import com.illutech.advertsite.entities.Advert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface AdvertRepository extends JpaRepository<Advert,Long> {
    Collection<Advert> findAllByValidated(boolean validated);

    Collection<Advert> findAllByDeleted(boolean b);
}
