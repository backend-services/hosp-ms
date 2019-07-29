package com.hosp.hospms.models.DTOs.product;

import lombok.Data;

@Data
public class EquipmentDTO extends ProductDTO {

    private String provider;
    private double price;
}
