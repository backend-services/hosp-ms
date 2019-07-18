package com.hosp.hospms.models.domains;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Data
public class Substance extends DocumentBase {

    @Id
    @Setter(AccessLevel.NONE)
    private String id;

    private String name;

}
