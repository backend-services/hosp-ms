package com.hosp.hospms.models.DTOs;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.hosp.hospms.models.domains.ProductType;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

@Data
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        visible = true,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = MedicineDTO.class, name = "MEDICINE"),
        @JsonSubTypes.Type(value = EquipmentDTO.class, name = "EQUIPMENT"),
})
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public abstract class ProductDTO {

    @Setter(AccessLevel.NONE)
    private String id;
    private String name;
    private ProductType type;
    private long quantity;

}
