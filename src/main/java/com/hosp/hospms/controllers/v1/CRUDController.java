package com.hosp.hospms.controllers.v1;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CRUDController<T> {

    T create(T t);
    Page<T> getAll(Pageable page);
    void remove(String id);
    T update(String id, T t);
    T find(String id);
}
