package com.illutech.advertsite.service.adrateservice;

import org.springframework.stereotype.Service;

@Service
public interface IAdRateService {
    Long calculateAverageRatePerAdvertById();
    Long calculateAverageRateForEachAdvert();
}
