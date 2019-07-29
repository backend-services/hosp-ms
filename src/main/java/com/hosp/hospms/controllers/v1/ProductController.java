package com.hosp.hospms.controllers.v1;

import com.hosp.hospms.configs.DTOMapperImp;
import com.hosp.hospms.models.DTOs.product.ProductDTO;
import com.hosp.hospms.models.domains.product.Product;
import com.hosp.hospms.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/products")
@CrossOrigin( origins = "*")
public class ProductController implements CRUDConroller<ProductDTO> {

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

    @GetMapping
    public Page<ProductDTO> getAll(Pageable page){
        Page<Product> products = service.findAll(page);

        List<ProductDTO> productDTOS = products
                .stream()
                .map(mapper::toProductDTO)
                .collect(Collectors.toList());

        return new PageImpl(productDTOS, page, products.getTotalElements());
    }

}
