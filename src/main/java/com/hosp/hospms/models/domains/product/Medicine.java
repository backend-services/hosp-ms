package com.hosp.hospms.models.domains.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Medicine extends Product {

    private String  substanceName;
    private String labName;
    private boolean medicalPrescription = false;
    private List<String> presentations;
    private MedicineCategory category;


    public List<String> getPresentations() {
        if (CollectionUtils.isEmpty(presentations)) return Collections.emptyList();
        return Collections.unmodifiableList(presentations);
    }


    public void setPresentation(String presentation){
        if (CollectionUtils.isEmpty(presentations)) presentations = new ArrayList<>();
        presentations.add(presentation);
    }

    @Override
    public void update(Product productUpdate) {
        super.update(productUpdate);
        Medicine medicineUpdate = (Medicine) productUpdate;
        this.substanceName = medicineUpdate.getSubstanceName();
        this.labName = medicineUpdate.getLabName();
        this.medicalPrescription =  medicineUpdate.isMedicalPrescription();
        this.presentations = medicineUpdate.getPresentations();
        this.category = medicineUpdate.getCategory();
    }
}
