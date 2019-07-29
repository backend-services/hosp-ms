package com.hosp.hospms.services;

import com.hosp.hospms.exceptions.ResourceNotFoundException;
import com.hosp.hospms.helpers.OptionalConsumer;
import com.hosp.hospms.models.domains.Product;
import com.hosp.hospms.repositories.ProductRepository;
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
    public Product createOrUpdate(Product product) {
        return repository.save(product);
    }

    @Override
    public void remove(String id) {
        Optional<Product> productOpt = repository.findById(id);

        OptionalConsumer
                .of(productOpt)
                    .ifPresent(product -> {
                        product.setActive(false);
                        createOrUpdate(product);
                    })
                    .ifNotPresent(ResourceNotFoundException::new);

    }

    @Override
    public Page<Product> findAll(Pageable page) {
        return repository.findByActiveTrue(page);
    }

    @Override
    public Product find(String id) {
        Optional<Product> productOpt = repository.findById(id);
        return productOpt.orElseThrow(ResourceNotFoundException::new);
    }
}
