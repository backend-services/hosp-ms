package com.hosp.hospms.models.domains.product;

import lombok.Data;

@Data
public class Equipment extends Product{

    private String provider;
    private double price;
}
