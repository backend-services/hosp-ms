package com.hosp.hospms.controllers.v1;

import com.hosp.hospms.models.DTOs.product.ProductDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductController {
    Page<ProductDTO> findLowStock(Pageable page);
}
