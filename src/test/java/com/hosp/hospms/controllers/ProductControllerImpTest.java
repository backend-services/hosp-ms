package com.hosp.hospms.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.hosp.hospms.controllers.v1.ProductControllerImp;
import com.hosp.hospms.databuilder.ProductBuilder;
import com.hosp.hospms.models.DTOs.product.MedicineDTO;
import com.hosp.hospms.services.ProductServiceImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ProductControllerImpTest extends AbstractTest {

    private static final String BASE_URL = "/v1/products";
    private static final String PRODUCT_ID = "/2";


    @Mock
    private ProductServiceImp service;

    @InjectMocks
    private ProductControllerImp controller;

    @BeforeEach
    void setUp() {
        mvc = MockMvcBuilders.standaloneSetup(controller)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .build();
    }

    @Test
    void shouldStatus200WhenFindAll() throws Exception {
        findAllMock();
        String uri = BASE_URL + "/";

        mvc.perform(MockMvcRequestBuilders
                .get(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().is(HTTP_STATUS_OK));
    }

    @Test
    void shouldStatus201WhenCreate() throws Exception {
        String uri = BASE_URL + "/";

        mvc.perform(MockMvcRequestBuilders
                .post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(getProductJson()))
                .andExpect(status().is(HTTP_STATUS_CREATED));
    }

    @Test
    void shouldStatus200WhenUpdate() throws Exception {
        String uri = BASE_URL + PRODUCT_ID;

        mvc.perform(MockMvcRequestBuilders
                .put(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(getProductJson()))
                .andExpect(status().is(HTTP_STATUS_OK));
    }

    @Test
    void shouldStatus204WhenRemove() throws Exception {
        String uri = BASE_URL + PRODUCT_ID;

        mvc.perform(MockMvcRequestBuilders
                .delete(uri))
                .andExpect(status().is(HTTP_STATUS_NO_CONTENT));
    }

    @Test
    void shouldStatus200WhenFindLowStock() throws Exception {
        findLowStockMock();
        String uri = BASE_URL + "/low-stock";

        mvc.perform(MockMvcRequestBuilders
                .get(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().is(HTTP_STATUS_OK));
    }

    private void findLowStockMock() {
        when(service.findLowStock(any())).thenReturn(new PageImpl<>(Collections.emptyList()));
        mapperMock();
    }

    private String getProductJson() throws JsonProcessingException {
        return super.mapToJson(ProductBuilder.getDTO());
    }


    private void findAllMock() {
        when(service.findAll(any())).thenReturn(new PageImpl<>(Collections.emptyList()));
        mapperMock();
    }

    private void mapperMock() {
        when(mapper.toProductDTO(any())).thenReturn(new MedicineDTO());
    }

}
