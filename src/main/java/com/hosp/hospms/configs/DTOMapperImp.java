package com.hosp.hospms.configs;

import com.hosp.hospms.models.DTOs.EquipamentDTO;
import com.hosp.hospms.models.DTOs.MedicineDTO;
import com.hosp.hospms.models.DTOs.ProductDTO;
import com.hosp.hospms.models.domains.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DTOMapperImp {


    @Autowired
    private DTOMapper mapper;


    public Product toProduct(ProductDTO productDTO){

        mapper.toMedicine((MedicineDTO) productDTO);

        mapper.toEquipament((EquipamentDTO) productDTO);

        return  null;
    }
}
