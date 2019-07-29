package com.hosp.hospms.models.DTOs.product;

import com.hosp.hospms.models.domains.product.MedicineCategory;
import lombok.Data;

import java.util.List;

@Data
public class MedicineDTO extends ProductDTO {

    private String lab;
    private String  substanceName;
    private boolean medicalPrescription = false;
    private List<String> presentations;
    private MedicineCategory category;

}
