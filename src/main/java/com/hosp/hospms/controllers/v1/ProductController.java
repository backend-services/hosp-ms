package com.hosp.hospms.controllers.v1;

import com.hosp.hospms.models.DTOs.product.ProductDTO;
import org.springframework.data.domain.Page;

public interface ProductController {
    Page<ProductDTO> findLowStock();
}
