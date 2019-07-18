package com.hosp.hospms.repositories;

import com.hosp.hospms.models.domains.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {
    Page<Product> findByActiveTrue(Pageable page);
}
