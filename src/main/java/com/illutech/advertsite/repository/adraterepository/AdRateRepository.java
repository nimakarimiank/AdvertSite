package com.illutech.advertsite.repository.adraterepository;

import com.illutech.advertsite.entities.UserAdRate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdRateRepository extends JpaRepository<UserAdRate,Long> {
}
