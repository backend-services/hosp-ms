package com.hosp.hospms.models.domains.product;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MedicineTest {

    private Medicine medicine;

    @BeforeEach
    void setUp() {
        medicine = new Medicine();
    }

    @Test
    public void shouldReturnEmptyLitWhenPresentationsNull() {
        assertNotNull(medicine.getPresentations());
        assertEquals(0L, medicine.getPresentations().size());
    }

    @Test
    public void shouldReturnUnmodifiableListWhenPresentationsNotNull() {
        medicine.setPresentation("presentation a");
        assertNotNull(medicine.getPresentations());
        assertEquals(1L, medicine.getPresentations().size());

        assertThrows(UnsupportedOperationException.class, () ->
                medicine.getPresentations().add("presentation b")
        );
    }

    @Test
    public void shouldSetPresentationWhenPresentationsNull() {
        medicine.setPresentation("presentation a");
        assertNotNull(medicine.getPresentations());
        assertEquals(1L, medicine.getPresentations().size());
    }

    @Test
    public void shouldSetPresentationWhenPresentationsNotNull() {
        medicine.setPresentation("presentation a");
        medicine.setPresentation("presentation b");
        assertNotNull(medicine.getPresentations());
        assertEquals(2L, medicine.getPresentations().size());
    }

    @Test
    public void shouldMedicalPrescriptionFalseWhenCreateMedicine() {
        assertFalse(medicine.isMedicalPrescription());
    }


    @Test
    void shouldUpdateMedicineWhenNewValues() {
        setDefaultValues();
        Medicine medUpdate = getMedicineUpdate();

        medicine.update(medUpdate);

        assertEquals(medUpdate.getSubstanceName(), medicine.getSubstanceName());
        assertEquals(medUpdate.getLabName(), medicine.getLabName());
        assertEquals(3L, medicine.getPresentations().size());
        assertEquals(medUpdate.getPresentations().get(0), medicine.getPresentations().get(0));
        assertEquals(medUpdate.getPresentations().get(1), medicine.getPresentations().get(1));
        assertEquals(medUpdate.getPresentations().get(2), medicine.getPresentations().get(2));
        assertEquals(medUpdate.getCategory(), medicine.getCategory());
    }

    private void setDefaultValues() {
        medicine.setSubstanceName("substance name");
        medicine.setLabName("lab name");
        medicine.setPresentation("presentation a");
        medicine.setPresentation("presentation b");
        medicine.setCategory(MedicineCategory.GENERIC);
    }

    private Medicine getMedicineUpdate() {
        Medicine medUpdate = new Medicine();
        medUpdate.setSubstanceName("substance name update");
        medUpdate.setLabName("lab name update");
        medUpdate.setPresentation("presentation a update");
        medUpdate.setPresentation("presentation b update");
        medUpdate.setPresentation("presentation c update");
        medUpdate.setCategory(MedicineCategory.REFERENCE);
        return medUpdate;
    }
}
