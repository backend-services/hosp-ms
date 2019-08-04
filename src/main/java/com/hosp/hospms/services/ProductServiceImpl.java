package com.hosp.hospms.services;

import com.hosp.hospms.exceptions.ResourceNotFoundException;
import com.hosp.hospms.models.domains.product.Product;
import com.hosp.hospms.repositories.ProductRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;

    @Autowired
    public ProductServiceImpl(ProductRepository repository) {
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
}
