package com.hosp.hospms.models.DTOs.people;

import com.hosp.hospms.models.domains.people.MedicalRecord;
import com.hosp.hospms.models.domains.people.People;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class PatientDTO extends PeopleDTO {

    private LocalDateTime clientSince;

    @DBRef
    private People emmergencyContact;

    @DBRef
    List<MedicalRecord> medicalRecordList;
}
