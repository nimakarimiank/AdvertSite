package com.illutech.advertsite.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdvertDTO {
    private String advertText;
    private String price;
    private String averageRating;
}
