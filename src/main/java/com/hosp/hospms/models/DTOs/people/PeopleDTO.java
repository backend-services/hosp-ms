package com.hosp.hospms.models.DTOs.people;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.hosp.hospms.models.domains.people.Gender;
import com.hosp.hospms.models.domains.people.PeopleType;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;

@Data
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        visible = true,
        property = "type"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = PatientDTO.class, name = "PATIENT"),
//        @JsonSubTypes.Type(value = EmployeeDTO.class, name = "EMPLOYEE")
})
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public abstract class PeopleDTO {

    @Id
    @Setter(AccessLevel.NONE)
    private String id;
    private String cpf;
    private String phoneNumber;
    private String email;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private LocalDate birthDate;

    private Gender gender;
    private PeopleType type;
}
