package com.hosp.hospms.services;

import com.hosp.hospms.databuilder.MedicineBuilder;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

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

        assertThrows(ResourceNotFoundException.class, () -> service.find(id));
    }


    @Test
    void shouldExceptionWhenNoRemoveById() {
        when(repository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> service.remove(id));
    }


    @Test
    void shouldExceptionWhenNoUpdateById() {
        Product product = new Medicine();
        when(repository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> service.update(id, product));
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
        Product product = MedicineBuilder.get();
        Medicine prodUpdate = toUpdate();

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
        Product product = MedicineBuilder.get();
        product.setType(ProductType.MEDICINE);
        Product prodUpdate = MedicineBuilder.get();
        prodUpdate.setType(ProductType.EQUIPMENT);

        when(repository.findById(id)).thenReturn(Optional.of(product));
        when(repository.save(any())).thenReturn(product);

        Medicine update = (Medicine) service.update(id, prodUpdate);

        assertNotEquals(prodUpdate.getType(), update.getType());
    }

    @Test
    void shouldFindAllWhenNoPassFilter() {
        Pageable page = Pageable.unpaged();
        List<Product> list = MedicineBuilder.getList();
        Page<Product> productList = new PageImpl(list);
        when(repository.findByActiveTrue(page)).thenReturn(productList);

        Page<Product> allProducts = service.findAll(page);

        List<Product> content1 = productList.getContent();
        List<Product> content2 = allProducts.getContent();

        assertEquals(productList.getTotalElements(), allProducts.getTotalElements());

        for(int i = 0; i < content1.size(); i++){
            assertEquals(content1.get(i).getId(), content2.get(i).getId());
            assertEquals(content1.get(i).getName(), content2.get(i).getName());
            assertEquals(content1.get(i).getType(), content2.get(i).getType());
        }
    }


    @Test
    void shouldFindOneWhenNoPassId() {
        Product product = MedicineBuilder.get();
        when(repository.findById(id)).thenReturn(Optional.of(product));

        Product productResult = service.find(id);

        assertEquals(product.getId(), productResult.getId());
        assertEquals(product.getName(), productResult.getName());
        assertEquals(product.getType(), productResult.getType());
    }

    @Test
    void shouldCreateProduct() {
        Product product = MedicineBuilder.get();
        when(repository.save(product)).thenReturn(product)  ;

        Product productResult = service.create(product);

        assertEquals(product.getId(), productResult.getId());
        assertEquals(product.getName(), productResult.getName());
        assertEquals(product.getType(), productResult.getType());

    }


    private Medicine toUpdate() {

        Medicine medUpdate = new Medicine();
        medUpdate.setName("Abba - update");
        medUpdate.setQuantity(2);
        medUpdate.setSubstanceName("Acetato de Abiraterona - update");
        medUpdate.setLabName("Sun pharma - update");
        medUpdate.setPresentation("presentation a update");
        medUpdate.setPresentation("presentation b update");
        medUpdate.setPresentation("presentation c update");
        medUpdate.setCategory(MedicineCategory.REFERENCE);
        return medUpdate;
    }
}
