package com.hosp.hospms.models.domains.product;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MedicineTest {

    @Test
    public void shouldReturnEmptyLitWhenPresentationsNull() {
        Medicine medicine = new Medicine();
        assertNotNull(medicine.getPresentations());
        assertEquals(0L, medicine.getPresentations().size());
    }

    @Test
    public void shouldReturnUnmodifiableListWhenPresentationsNotNull() {
        Medicine medicine = new Medicine();
        medicine.setPresentation("presentation a");
        assertNotNull(medicine.getPresentations());
        assertEquals(1L, medicine.getPresentations().size());

        assertThrows(UnsupportedOperationException.class, () -> {
            medicine.getPresentations().add("presentation b");
        });
    }

    @Test
    public void shouldSetPresentationWhenPresentationsNull() {
        Medicine medicine = new Medicine();
        medicine.setPresentation("presentation a");
        assertNotNull(medicine.getPresentations());
        assertEquals(1L, medicine.getPresentations().size());
    }

    @Test
    public void shouldSetPresentationWhenPresentationsNotNull() {
        Medicine medicine = new Medicine();
        medicine.setPresentation("presentation a");
        medicine.setPresentation("presentation b");
        assertNotNull(medicine.getPresentations());
        assertEquals(2L, medicine.getPresentations().size());
    }

    @Test
    public void shouldMedicalPrescriptionFalseWhenCreateMedicine() {
        Medicine medicine = new Medicine();
        assertFalse(medicine.isMedicalPrescription());
    }


}
