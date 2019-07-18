package com.hosp.hospms.models.domains;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Data
public class Lab extends DocumentBase {

    @Id
    @Setter(AccessLevel.NONE)
    private String id;

    private String name;

}
