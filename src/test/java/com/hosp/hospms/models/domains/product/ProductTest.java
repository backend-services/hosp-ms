package com.hosp.hospms.models.domains.product;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class ProductTest {


    private Product productAbs;
    private  Product prodUpdate;

    @BeforeEach
    void setUp() {
        productAbs = Mockito.mock(Product.class, Mockito.CALLS_REAL_METHODS);
        prodUpdate = Mockito.mock(Product.class, Mockito.CALLS_REAL_METHODS);
    }

    @Test
    void shouldUpdateMedicineWhenNewValues() {
        productAbs.setName("Name");
        productAbs.setQuantity(1);

        prodUpdate.setName("Name Update");
        prodUpdate.setQuantity(2);

        productAbs.update(prodUpdate);

        assertEquals(prodUpdate.getName(), productAbs.getName());
        assertEquals(prodUpdate.getQuantity(), productAbs.getQuantity());
    }


    @Test
    void shouldNotUpdateType() {
        productAbs.setType(ProductType.MEDICINE);
        prodUpdate.setType(ProductType.EQUIPMENT);

        productAbs.update(prodUpdate);

        assertNotEquals(prodUpdate.getType(), productAbs.getType());
    }
}

