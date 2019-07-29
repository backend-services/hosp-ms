package com.hosp.hospms.configs;

import com.hosp.hospms.models.DTOs.product.EquipmentDTO;
import com.hosp.hospms.models.DTOs.product.MedicineDTO;
import com.hosp.hospms.models.domains.product.Equipment;
import com.hosp.hospms.models.domains.product.Medicine;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface DTOMapper {

    DTOMapper INSTANCE = Mappers.getMapper( DTOMapper.class );

    @Mapping(target = "type", constant = "MEDICINE")
    @Mapping(source = "labName", target = "lab")
    MedicineDTO toMedicineDTO(Medicine entity);

    @Mapping(source = "lab", target = "labName")
    Medicine toMedicine(MedicineDTO dto);

    Equipment toEquipament(EquipmentDTO dto);
}
