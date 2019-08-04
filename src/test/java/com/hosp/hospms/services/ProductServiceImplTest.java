package com.hosp.hospms.services;

import com.hosp.hospms.exceptions.ResourceNotFoundException;
import com.hosp.hospms.models.domains.product.Medicine;
import com.hosp.hospms.models.domains.product.MedicineCategory;
import com.hosp.hospms.models.domains.product.Product;
import com.hosp.hospms.models.domains.product.ProductType;
import com.hosp.hospms.repositories.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductServiceImplTest {

    @InjectMocks
    private ProductServiceImpl service;

    @Mock
    private ProductRepository repository;

    private String id;

    @BeforeEach
    void setUp() {
        id = "123";
    }

    @Test
    void shouldExceptionWhenNoFindProductById() {
        when(repository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            service.find(id);
        });
    }


    @Test
    void shouldExceptionWhenNoRemoveById() {
        when(repository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            service.remove(id);
        });
    }


    @Test
    void shouldExceptionWhenNoUpdateById() {
        Product product = new Medicine();
        when(repository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            service.update(id, product);
        });
    }

    @Test
    void shouldInactivateWhenRemoveProduct() {
        Product product = new Medicine();
        when(repository.findById(id)).thenReturn(Optional.of(product));

        assertTrue(product.isActive());

        service.remove(id);

        assertFalse(product.isActive());
    }


    @Test
    void shouldUpdateWhenUpdateProduct() {
        Medicine product = getProductMock();
        Medicine prodUpdate = getMedicineUpdate();

        when(repository.findById(id)).thenReturn(Optional.of(product));
        when(repository.save(any())).thenReturn(product);

        Medicine update = (Medicine) service.update(id, prodUpdate);

        assertEquals(prodUpdate.getName(), update.getName());
        assertEquals(prodUpdate.getQuantity(), update.getQuantity());
        assertEquals(prodUpdate.getSubstanceName(), update.getSubstanceName());
        assertEquals(prodUpdate.getLabName(), update.getLabName());
        assertEquals(3L, update.getPresentations().size());
        assertEquals(prodUpdate.getPresentations().get(0), update.getPresentations().get(0));
        assertEquals(prodUpdate.getPresentations().get(1), update.getPresentations().get(1));
        assertEquals(prodUpdate.getPresentations().get(2), update.getPresentations().get(2));
        assertEquals(prodUpdate.getCategory(), update.getCategory());

    }

    @Test
    void shouldNotUpdateType() {
        Medicine product = getProductMock();
        product.setType(ProductType.MEDICINE);
        Medicine prodUpdate = getMedicineUpdate();
        prodUpdate.setType(ProductType.EQUIPMENT);

        when(repository.findById(id)).thenReturn(Optional.of(product));
        when(repository.save(any())).thenReturn(product);

        Medicine update = (Medicine) service.update(id, prodUpdate);

        assertNotEquals(prodUpdate.getType(), update.getType());
    }

    private Medicine getProductMock() {
        Medicine medicine = new Medicine();
        medicine.setName("Name");
        medicine.setQuantity(1);
        medicine.setSubstanceName("substance name");
        medicine.setLabName("lab name");
        medicine.setPresentation("presentation a");
        medicine.setPresentation("presentation b");
        medicine.setCategory(MedicineCategory.GENERIC);
        return medicine;
    }

    private Medicine getMedicineUpdate() {
        Medicine medUpdate = new Medicine();
        medUpdate.setName("Name Update");
        medUpdate.setQuantity(2);
        medUpdate.setSubstanceName("substance name update");
        medUpdate.setLabName("lab name update");
        medUpdate.setPresentation("presentation a update");
        medUpdate.setPresentation("presentation b update");
        medUpdate.setPresentation("presentation c update");
        medUpdate.setCategory(MedicineCategory.REFERENCE);
        return medUpdate;
    }
}
