package com.hosp.hospms.models.domains.people;

import com.hosp.hospms.models.domains.Product;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class MedicalRecord {

    LocalDateTime entryDate;
    LocalDateTime departureDate;
    PatientType patientType;
    List<Product> productsUsed;

}


