package com.hosp.hospms.services;

import com.hosp.hospms.models.domains.product.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService extends CRUDService<Product> {

    Page<Product> findLowStock(Pageable page);
}
