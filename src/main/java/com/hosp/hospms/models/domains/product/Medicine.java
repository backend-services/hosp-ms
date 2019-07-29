package com.hosp.hospms.models.domains.product;

import lombok.Data;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Data
public class Medicine extends Product {

    private String  substanceName;
    private String labName;
    private boolean medicalPrescription = false;
    private List<String> presentations;
    private MedicineCategory category;


    public List<String> getPresentations() {
        if (CollectionUtils.isEmpty(presentations)) Collections.emptyList();
        return Collections.unmodifiableList(presentations);
    }


    public void setPresentation(String presentation){
        if (CollectionUtils.isEmpty(presentations)) presentations = new ArrayList<>();
        presentations.add(presentation);
    }

}
