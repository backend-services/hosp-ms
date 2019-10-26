package com.hosp.hospms.databuilder;

import com.hosp.hospms.models.domains.product.Medicine;
import com.hosp.hospms.models.domains.product.MedicineCategory;
import com.hosp.hospms.models.domains.product.Product;

import java.util.Arrays;
import java.util.List;

public class MedicineBuilder {

    public static Product get() {

        Product product= Medicine
                .builder()
                .substanceName("Acetato de Abiraterona")
                .labName("Sun pharma")
                .presentations(getPresentations())
                .category(MedicineCategory.SIMILAR)
                .build();


        product.setName("Abba");
        product.setQuantity(1);
        return product;

    }

    private static List<String> getPresentations() {
        return Arrays.asList("Abba 250mg", "Abba 200mg","Abba 150mg");
    }


    public static List<Product> getList() {

        Product product1 = Medicine
                .builder()
                .substanceName("Acetato de Abiraterona")
                .labName("Sun pharma")
                .presentations(getPresentations())
                .category(MedicineCategory.SIMILAR)
                .build();
        product1.setName("Abba");
        product1.setQuantity(1);

        Product product2 = Medicine
                .builder()
                .substanceName("Acetato de Abiraterona")
                .labName("Sun pharma")
                .presentations(getPresentations())
                .category(MedicineCategory.SIMILAR)
                .build();
        product2.setName("Abba");
        product2.setQuantity(1);

        Product product3 = Medicine
                .builder()
                .substanceName("Acetato de Abiraterona")
                .labName("Sun pharma")
                .presentations(getPresentations())
                .category(MedicineCategory.SIMILAR)
                .build();
        product3.setName("Abba");
        product3.setQuantity(1);

        Product product4 = Medicine
                .builder()
                .substanceName("Acetato de Abiraterona")
                .labName("Sun pharma")
                .presentations(getPresentations())
                .category(MedicineCategory.SIMILAR)
                .build();
        product4.setName("Abba");
        product4.setQuantity(1);

        Product product5 = Medicine
                .builder()
                .substanceName("Acetato de Abiraterona")
                .labName("Sun pharma")
                .presentations(getPresentations())
                .category(MedicineCategory.SIMILAR)
                .build();
        product5.setName("Abba");
        product5.setQuantity(1);

        return Arrays.asList(product1, product2, product3, product4, product5);
    }

}
