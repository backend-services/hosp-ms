package com.hosp.hospms.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CRUDService<T> {

    T createOrUpdate(T t);
    void remove(String id);
    Page<T> findAll(Pageable page);
    T find(String id);
}
