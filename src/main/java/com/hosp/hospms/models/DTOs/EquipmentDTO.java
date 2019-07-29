package com.hosp.hospms.models.DTOs;

import lombok.Data;

@Data
public class EquipmentDTO extends ProductDTO {

    private String provider;
    private double price;
}
