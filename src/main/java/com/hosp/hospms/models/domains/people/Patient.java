package com.hosp.hospms.models.domains.people;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class Patient extends People {

    @JsonFormat
    private LocalDateTime clientSince;
    @DBRef
    private People emmergencyContact;
    @DBRef
    List<MedicalRecord> medicalRecordList;
    private String alergicTo;
    private BloodType bloodType;
    private boolean rh;

}
