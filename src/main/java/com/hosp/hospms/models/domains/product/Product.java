package com.hosp.hospms.models.domains.product;

import com.hosp.hospms.models.domains.DocumentBase;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public abstract class Product extends DocumentBase {

    @Id
    @Setter(AccessLevel.NONE)
    private String id;
    private String name;
    private ProductType type;
    private long quantity;

}
