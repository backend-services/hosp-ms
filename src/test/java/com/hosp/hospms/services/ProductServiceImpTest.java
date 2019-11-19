package com.hosp.hospms.services;

import com.hosp.hospms.databuilder.ProductBuilder;
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
import org.springframework.data.domain.*;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceImpTest {

    @InjectMocks
    private ProductServiceImp service;

    @Mock
    private ProductRepository repository;

    private String id;
    private Product product;
    private Product productResult;

    @BeforeEach
    void setUp() {
        id = "123";
    }

    @Test
    void shouldExceptionWhenNoFindProductById() {
        findByIdMock();

        assertThrows(ResourceNotFoundException.class, () -> service.find(id));
    }

    @Test
    void shouldExceptionWhenNoRemoveById() {
        findByIdMock();

        assertThrows(ResourceNotFoundException.class, () -> service.remove(id));
    }

    @Test
    void shouldExceptionWhenNoUpdateById() {
        product = new Medicine();
        findByIdMock();

        assertThrows(ResourceNotFoundException.class, () -> service.update(id, product));
    }

    @Test
    void shouldInactivateWhenRemoveProduct() {
        product = new Medicine();
        findByIdOptionalMock(product);

        assertTrue(product.isActive());

        service.remove(id);

        assertFalse(product.isActive());
    }

    @Test
    void shouldUpdateWhenUpdateProduct() {
        Medicine toUpdate = toUpdate();

        repositoryMock();

        Medicine updated = (Medicine) service.update(id, toUpdate);

        assertProductUpdate(toUpdate, updated);
    }

    @Test
    void shouldNotUpdateType() {
        product = ProductBuilder.get();
        product.setType(ProductType.MEDICINE);
        Product prodUpdate = ProductBuilder.get();
        prodUpdate.setType(ProductType.EQUIPMENT);

        findByIdOptionalMock(product);
        when(repository.save(any())).thenReturn(product);

        Medicine update = (Medicine) service.update(id, prodUpdate);

        assertNotEquals(prodUpdate.getType(), update.getType());
    }

    @Test
    void shouldFindAllWhenNoPassFilter() {
        Pageable page = Pageable.unpaged();
        List<Product> list = ProductBuilder.getList();
        Page<Product> productList = new PageImpl<>(list);
        when(repository.findByActiveTrue(page)).thenReturn(productList);

        Page<Product> allProducts = service.findAll(page);

        assertProductList(list , allProducts.getContent());
    }


    @Test
    void shouldFindOneWhenNoPassId() {
        product = ProductBuilder.get();
        findByIdOptionalMock(product);

        productResult = service.find(id);

        assertProduct();
    }

    @Test
    void shouldCreateProduct() {
        product = ProductBuilder.get();
        when(repository.save(product)).thenReturn(product);

        productResult = service.create(product);

        assertProduct();
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

    private void findByIdMock() {
        when(repository.findById(id)).thenReturn(Optional.empty());
    }

    private void findByIdOptionalMock(Product product) {
        when(repository.findById(id)).thenReturn(Optional.of(product));
    }

    private void assertProduct() {
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

    private void assertProductUpdate(Medicine toUpdate, Medicine updated) {
        assertEquals(toUpdate.getName(), updated.getName());
        assertEquals(toUpdate.getQuantity(), updated.getQuantity());
        assertEquals(toUpdate.getSubstanceName(), updated.getSubstanceName());
        assertEquals(toUpdate.getLabName(), updated.getLabName());
        assertPresentations(toUpdate.getPresentations(), updated.getPresentations());
        assertEquals(toUpdate.getCategory(), updated.getCategory());
    }

    private void repositoryMock() {
        product = ProductBuilder.get();
        findByIdOptionalMock(product);
        when(repository.save(any())).thenReturn(product);
    }

    private void assertPresentations(List<String>  toUpdates, List<String>  updateds) {
        assertEquals(3L, updateds.size());
        assertEquals(3L, toUpdates.size());

        for(int i = 0; i < updateds.size(); i++){
            assertEquals(toUpdates.get(i), updateds.get(i));
        }
    }
}
