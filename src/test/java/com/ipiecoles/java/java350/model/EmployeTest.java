package com.ipiecoles.java.java350.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class EmployeTest {

    @Test
    void getNombreAnneeAncienneteNow() {
        //given
        Employe e = new Employe();
        e.setDateEmbauche(LocalDate.now());
        //when
        Integer nbAnnee = e.getNombreAnneeAnciennete();
        //then
        Assertions.assertEquals( 0, (int)nbAnnee);
    }
    @Test
    void getNombreAnneeAncienneteMinus2() {
        //given
        Employe e = new Employe();
        e.setDateEmbauche(LocalDate.now().minusYears(2L));
        //when
        Integer nbAnnee = e.getNombreAnneeAnciennete();
        //then
        Assertions.assertEquals( 2, (int)nbAnnee);
    }
    @Test
    void getNombreAnneeAncienneteNull() {
        //given
        Employe e = new Employe();
        e.setDateEmbauche(null);
        //when
        Integer nbAnnee = e.getNombreAnneeAnciennete();
        //then
        Assertions.assertEquals( 0, (int)nbAnnee);
    }
    @Test
    void getNombreAnneeAnciennetePlus2() {
        //given
        Employe e = new Employe();
        e.setDateEmbauche(LocalDate.now().plusYears(2L));
        //when
        Integer nbAnnee = e.getNombreAnneeAnciennete();
        //then
        Assertions.assertEquals( 0, (int)nbAnnee);
    }

    @ParameterizedTest()
    @CsvSource({
            "1, 'T12345', 0, 1.0, 1000.0",
            "1, 'T12345', 2, 0.5, 600.0",
    })
    void testGetPrimeAnnuelle(Integer performance, String matricule, Long anciennete, Double tempsPartiel, Double primeAnnuelle){
        //given
        Double prime = 0d;
        Employe e = new Employe("boby", "le grain de riz", matricule, LocalDate.now().minusYears(anciennete), Entreprise.SALAIRE_BASE, performance, tempsPartiel);
        //when
        prime = e.getPrimeAnnuelle();
        //then
        Assertions.assertEquals(primeAnnuelle, prime);
    }

}