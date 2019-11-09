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
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/products")
@CrossOrigin(origins = "*")
public class ProductControllerImp implements CRUDController<ProductDTO>, ProductController {

    private final ProductService service;
    private final DTOMapperImp mapper;

    @Autowired
    public ProductControllerImp(ProductService service, DTOMapperImp mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDTO create(@RequestBody @Valid ProductDTO productDTO) {
        Product product = service.create(mapper.toProduct(productDTO));
        return  mapper.toProductDTO(product);
    }

    @GetMapping
    public Page<ProductDTO> findAll(Pageable page) {
        Page<Product> products = service.findAll(page);
        List<ProductDTO> productDTOS = getProductDTOS(products);
        return new PageImpl<>(productDTOS, page, products.getTotalElements());
    }

    @PutMapping("{id}")
    public ProductDTO update(@PathVariable String id, @RequestBody @Valid ProductDTO productDTO) {
        Product product = service.update(id, mapper.toProduct(productDTO));
        return mapper.toProductDTO(product);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable String id) {
        service.remove(id);
    }

    @GetMapping("{id}")
    public ProductDTO find(@PathVariable String id){
        Product product = service.find(id);
        return  mapper.toProductDTO(product);
    }

    @GetMapping("/low-stock")
    public Page<ProductDTO> findLowStock(Pageable page) {
        Page<Product> products = service.findLowStock(page);
        List<ProductDTO> productDTOS = getProductDTOS(products);
        return new PageImpl<>(productDTOS);
    }

    private List<ProductDTO> getProductDTOS(Page<Product> products) {
        return products
                .stream()
                .map(mapper::toProductDTO)
                .collect(Collectors.toList());
    }
}
