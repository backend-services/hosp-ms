package com.hosp.hospms.controllers.v1;

import com.hosp.hospms.models.DTOs.product.ProductDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CRUDConroller<T> {

    void create(T t);
    Page<T> getAll(Pageable page);
}
