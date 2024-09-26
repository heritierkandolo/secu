package com.ipiecoles.java.java350.model;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;


public class EmployeeTest {
    @Test
    public void getNombreAnneeAnciennete0() {
        //Given
        Employe employee =new Employe();
        employee.setDateEmbauche(LocalDate.now());


        //When
        Integer nbAnneeAnciennete =employee.getNombreAnneeAnciennete();
        //Then
            //Assertions.assertThat(nbAnneeAnciennete).isEqualTo(5);
        Assertions.assertThat(nbAnneeAnciennete).isEqualTo(0);
    }
    @Test
    public void getNombreAnneeAnciennete1() {
        //Given
        Employe employee =new Employe();
            LocalDate d=LocalDate.of(2016,2,4);
            employee.setDateEmbauche(d);


        //When:
        Integer nbAnneeAnciennete =employee.getNombreAnneeAnciennete();
        //Then
            Assertions.assertThat(nbAnneeAnciennete).isEqualTo(5);
    }

}
