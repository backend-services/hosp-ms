package com.hosp.hospms.controllers.v1;

import com.hosp.hospms.configs.DTOMapperImp;
import com.hosp.hospms.models.DTOs.product.ProductDTO;
import com.hosp.hospms.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/products")
@CrossOrigin( origins = "*")
public class ProductController {

    private final ProductService service;
    private final DTOMapperImp mapper;

    @Autowired
    public ProductController(ProductService service, DTOMapperImp mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody ProductDTO productDTO){
        service.createOrUpdate(mapper.toProduct(productDTO));
    }

}
