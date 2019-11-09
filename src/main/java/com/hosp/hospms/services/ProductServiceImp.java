package com.hosp.hospms.services;

import com.hosp.hospms.exceptions.ResourceNotFoundException;
import com.hosp.hospms.models.domains.product.Product;
import com.hosp.hospms.repositories.ProductRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductServiceImp implements ProductService {

    private static final String QUANTITY = "quantity";
    private static final int INIT_PAGE = 0;
    private final ProductRepository repository;

    @Autowired
    public ProductServiceImp(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public Product create(@NonNull Product product) {
        return repository.save(product);
    }

    @Override
    public Product update(@NonNull String id, @NonNull Product product) {
        Optional<Product> productOpt = repository.findById(id);
        Product productSave = productOpt.orElseThrow(ResourceNotFoundException::new);
        productSave.update(product);
        return repository.save(productSave);
    }


    @Override
    public void remove(String id) {
        Optional<Product> productOpt = repository.findById(id);
        Product product = productOpt.orElseThrow(ResourceNotFoundException::new);
        product.setActive(false);
        repository.save(product);
    }

    @Override
    public Page<Product> findAll(@NonNull Pageable page) {
        return repository.findByActiveTrue(page);
    }

    @Override
    public Product find(@NonNull String id) {
        Optional<Product> productOpt = repository.findById(id);
        return productOpt.orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public Page<Product> findLowStock(@NonNull Pageable page) {
        Pageable pageQuery = PageRequest.of(INIT_PAGE, page.getPageSize(), new Sort(Sort.Direction.ASC, QUANTITY));
        return repository.findAll (pageQuery);
    }
}
