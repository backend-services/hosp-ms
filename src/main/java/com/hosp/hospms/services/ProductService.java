package com.hosp.hospms.services;

import com.hosp.hospms.models.domains.product.Product;
import org.springframework.data.domain.Page;

public interface ProductService extends CRUDService<Product> {

    Page<Product> findLowStock();
}
