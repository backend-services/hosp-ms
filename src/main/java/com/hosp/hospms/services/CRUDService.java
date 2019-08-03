package com.hosp.hospms.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CRUDService<T> {

    T create(T t);
    void remove(String id);
    Page<T> findAll(Pageable page);
    T find(String id);
    T update (String id, T t);
}
