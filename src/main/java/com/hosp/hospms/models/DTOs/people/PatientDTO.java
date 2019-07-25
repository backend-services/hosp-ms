package com.hosp.hospms.models.DTOs.people;

import com.hosp.hospms.models.domains.people.BloodType;
import com.hosp.hospms.models.domains.people.MedicalRecord;
import com.hosp.hospms.models.domains.people.People;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class PatientDTO extends PeopleDTO {

    private LocalDateTime clientSince;
    private People emmergencyContact;
    List<MedicalRecord> medicalRecordList;
    private String alergicTo;
    private BloodType bloodType;
    private boolean rh;
}
