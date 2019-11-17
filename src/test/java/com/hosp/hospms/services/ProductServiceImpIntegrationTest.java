package com.hosp.hospms.services;

import com.hosp.hospms.databuilder.ProductBuilder;
import com.hosp.hospms.models.domains.product.Product;
import com.hosp.hospms.repositories.ProductRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.hosp.hospms.HospMsApplication;
import com.hosp.hospms.config.FakeMongo;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { HospMsApplication.class})
@Import(FakeMongo.class)
class ProductServiceImpIntegrationTest {

    @Autowired
    private ProductRepository repository;

    @Autowired
    private ProductServiceImp service;

    private Product product;
    private Product productResult;

    @BeforeEach
    void setUp() {
        repository.saveAll(ProductBuilder.getList());
    }

    @AfterEach
    void tearDown() {
        repository.deleteAll();
    }

    @Test
    void shouldFindProductsWhenLowStock() {
        List<Product> originalList = ProductBuilder.getList();
        originalList.sort(Comparator.comparing(Product::getQuantity));
        Pageable page = PageRequest.of(0, 3);
        Page<Product> lowStock = service.findLowStock(page);

        assertProductList(originalList.subList(1, 4) , lowStock.getContent());
    }

    private void assertProductList(List<Product> productsExpected, List<Product> productsResult) {

        assertNotNull(productsResult);
        assertEquals(productsExpected.size(), productsResult.size());

        for(int i = 0; i < productsExpected.size(); i++){
            product = productsExpected.get(i);
            productResult = productsResult.get(i);
            assertProduct();
        }
    }

    private void assertProduct() {
        assertEquals(product.getName(), productResult.getName());
        assertEquals(product.getType(), productResult.getType());
    }

}
