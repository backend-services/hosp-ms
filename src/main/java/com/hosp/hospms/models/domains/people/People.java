package com.hosp.hospms.models.domains.people;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document
public abstract class People {

    @Id
    @Setter(AccessLevel.NONE)
    private String id;
    private String cpf;
    private Gender gender;
    private String phoneNumber;
    private String email;
    @JsonFormat
    private LocalDateTime birthDate;
}
