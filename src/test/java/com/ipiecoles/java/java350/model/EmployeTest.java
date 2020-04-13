package com.ipiecoles.java.java350.model;
import com.ipiecoles.java.java350.exception.EmployeException;
import com.ipiecoles.java.java350.service.EmployeService;
import io.cucumber.java8.Da;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;

class EmployeTest {

    @Test
    void testAnneeAncinneteNow(){
        //Given Envoie de la class Employe
        Employe employe = new Employe();
        employe.setDateEmbauche(LocalDate.now());

        //When  recupéreration du nombre d'année d'ancienneté
        Integer nbAnneEmploye = employe.getNombreAnneeAnciennete();

        //Then : test du nombre d'année d'ancienneté
        Assertions.assertThat(nbAnneEmploye).isEqualTo(0);
    }

    @Test
    void getNombreAnneeAncienneteNminus2(){
        //Given
        Employe e = new Employe();
        e.setDateEmbauche(LocalDate.now().minusYears(2L));

        //When
        Integer anneeAnciennete = e.getNombreAnneeAnciennete();

        //Then
        Assertions.assertThat(anneeAnciennete).isEqualTo(2);
    }

    @Test
    void getNombreAnneeAncienneteNull(){
        //Given
        Employe e = new Employe();
        e.setDateEmbauche(null);

        //When
        Integer anneeAnciennete = e.getNombreAnneeAnciennete();

        //Then
        Assertions.assertThat(anneeAnciennete).isEqualTo(0);
    }

    @Test
    void getNombreAnneeAncienneteNplus2(){
        //Given
        Employe e = new Employe();
        e.setDateEmbauche(LocalDate.now().plusYears(2L));

        //When
        Integer anneeAnciennete = e.getNombreAnneeAnciennete();

        //Then
        Assertions.assertThat(anneeAnciennete).isEqualTo(0);
    }

    @ParameterizedTest
    @CsvSource({
            "1, 'T12345', 0, 1.0, 1000.0",
            "1, 'T12345', 2, 0.5, 600.0",
            "1, 'T12345', 2, 1.0, 1200.0",
            "2, 'T12345', 0, 1.0, 2300.0",
            "2, 'T12345', 1, 1.0, 2400.0",
            "1, 'M12345', 0, 1.0, 1700.0",
            "1, 'M12345', 5, 1.0, 2200.0",
            "2, 'M12345', 0, 1.0, 1700.0",
            "2, 'M12345', 8, 1.0, 2500.0",
            " , 'M12345',4, 2.0, 4200.0",
    })
    void getPrimeAnnuelle(Integer performance, String matricule, Long nbAnneeAnciennete, Double tempsPartiel, Double primeCalculee){
        //Given
        Employe employe = new Employe("Bouve", "Steve", "C00004", LocalDate.now(), 3000.0, 1, 3.0 );
        employe.setMatricule(matricule);
        employe.setTempsPartiel(tempsPartiel);
        employe.setDateEmbauche(LocalDate.now().minusYears(nbAnneeAnciennete));
        employe.setPerformance(performance);
        //When
        Double prime = employe.getPrimeAnnuelle();

        //Then
        Assertions.assertThat(prime).isEqualTo(primeCalculee);

    }

    @ParameterizedTest
    @CsvSource({
            " , 'M12345',4, 1.0, 2100.0",
    })
    void getPrimeAnnuelle2(Integer performance, String matricule, Long nbAnneeAnciennete, Double tempsPartiel, Double primeCalculee){
        //Given
        Employe employe = new Employe();
        employe.setMatricule(matricule);
        employe.setTempsPartiel(tempsPartiel);
        employe.setDateEmbauche(LocalDate.now().minusYears(nbAnneeAnciennete));
        employe.setPerformance(performance);
        //When
        Double prime = employe.getPrimeAnnuelle();
        //Then
        Assertions.assertThat(prime).isEqualTo(primeCalculee);
    }

    @ParameterizedTest
    @CsvSource(
            "2, 'M67890', 9, 1.7, 2800.0"
    )
    void getPrimeannuelle(){

    }

    @Test
    void testNbAnneeAncienneteNowPlus3(){
        //Given
        Employe employe = new Employe();
        employe.setDateEmbauche(LocalDate.now().plusYears(3));

        //When
        Integer nbAnnees = employe.getNombreAnneeAnciennete();

        //Then
        Assertions.assertThat(nbAnnees).isEqualTo(0);
    }

    @Test
    void testNbAnneeAncienneteNull(){
        //Given
        Employe employe = new Employe();
        employe.setDateEmbauche(null);

        //When
        Integer nbAnnees = employe.getNombreAnneeAnciennete();

        //Then
        Assertions.assertThat(nbAnnees).isEqualTo(0);
    }

    /*
     * Partie Evaluation
     */

    /*
     * Test unitaire sur la méthode augmenter salaire à 10%
     */

    @Test
    void testaugmenterSalaireNegatif(){
        //Given
        Employe employe = new Employe("Bouve", "Steve", "C00004", LocalDate.now(), 3000.0, 1, 3.0 );
        double pourcentage = -20D;
        Assertions.assertThatThrownBy(() -> {
                    //Then
                    employe.augmenterSalaire(pourcentage);
                }
        )//When
                .isInstanceOf(EmployeException.class)
                .hasMessage("Le pourcentage ne peut pas être négatif");
    }

    @Test
    void testaugmenterSalaire10() throws EmployeException {
        //Given
        Employe employe = new Employe("Pose", "Kevin", "C00001", LocalDate.now(), 1000.0, 2, 2.0 );
        double pourcentage = 10D;
        //When
        employe.augmenterSalaire(pourcentage);
        //Then
        Assertions.assertThat(employe.getSalaire()).isEqualTo(1100);
    }
    @Test
    void testaugmenterSalaireNull() throws EmployeException {
        //Given
        Employe employe = new Employe("deLaCompta", "Roger", "C00002", LocalDate.now(), null, 3, 7.0 );
        double pourcentage = 10D;
        Assertions.assertThatThrownBy(() -> {
                    //Then
                    employe.augmenterSalaire(pourcentage);
                }
        )//When
                .isInstanceOf(EmployeException.class)
                .hasMessage("Le salaire est null");

    }


    //Test paramètré sur la méthode getNbRTT

    @Test
    void getNbRtt(){
        //Given
        Employe employe = new Employe();
        employe.setDateEmbauche(LocalDate.now());
        //When
        Integer nombreRTT = employe.getNbRtt();
        //Then
        Assertions.assertThat(nombreRTT).isEqualTo(9);
    }

    @ParameterizedTest
    @CsvSource({
            "2020-01-01, 0.0, 0",
            "2020-04-12, 7.0, 63",
            "2019-01-01, 7.0, 63",
            "2020-01-02, 7.0, 63",
            "2020-01-03, 6.0, 63",
            "2020-04-12, 7.0, 63",
            "2019-01-01, 7.0, 63",
            "2020-01-02, 7.0, 63",
            "2020-01-03, 6.0, 63",
            "2019-01-04, 6.0, 63"
    })
    void getNbRtt2(LocalDate d){
        //When
        Employe employe = new Employe("Jacques", "Roger", "C00002",LocalDate.of(d.getYear(),1,1), 3000.0, 3, 7.0 );
        Integer NbRtt = employe.getNbRtt(d);

        //When
        Integer nbDayWeekend = 104;

        //Then
        if (DayOfWeek.THURSDAY.equals(employe.getDateEmbauche())) {
            if (d.isLeapYear()) Assertions.assertThat(nbDayWeekend).isEqualTo(105);
        } else if (DayOfWeek.FRIDAY.equals(employe.getDateEmbauche())) {
            if (d.isLeapYear()) Assertions.assertThat(nbDayWeekend).isEqualTo(106);
            else Assertions.assertThat(NbRtt).isEqualTo(105);
        } else if (DayOfWeek.SATURDAY.equals(employe.getDateEmbauche())) {
            Assertions.assertThat(nbDayWeekend).isEqualTo(104);
        }
    }

    //Coverage sur la méthode des congés
    @Test
    void getNbConges(){
        //Given
        Employe employe = new Employe();
        //When
        Integer nbConges = employe.getNbConges();
        //Then
        Assertions.assertThat(nbConges).isEqualTo(25);
    }

    @Test
    void getNombreAnneeAnciennete(){
        //Given
        Employe employe = new Employe();
        employe.setDateEmbauche(employe.getDateEmbauche());

        //when
        Integer nbAnneeEmploye = employe.getNombreAnneeAnciennete();

        //then
        Assertions.assertThat(nbAnneeEmploye).isEqualTo(0);
    }

    @Test
    void testsetid(){
        //Given
        Employe employe = new Employe("deLaCompta", "Roger", "C00002", LocalDate.now(), 4000.0, 3, 7.0 );
        //When
        Long id = 15L;
        employe.setId(id);
        //Then
        Assertions.assertThat(employe.getId()).isEqualTo(15L);
    }
    @Test
    void testSetNom(){
        //Given
        Employe employe = new Employe("Mac", "Brigitte", "C00007", LocalDate.now(), 2000.0, 3, 7.0 );
        //When
        String nom = "Mac";
        employe.setNom(nom);
        //Then
        Assertions.assertThat(employe.getNom()).isEqualTo(nom);
    }
    @Test
    void testsetPrenom(){
        //Given
        Employe employe = new Employe("Mac", "Xave", "C00007", LocalDate.now(), 2000.0, 3, 7.0 );
        //When
        String prenom = "Xave";
        employe.setPrenom(prenom);
        //Then
        Assertions.assertThat(employe.getPrenom()).isEqualTo(prenom);
    }
    @Test
    void testsetSalaire(){
        //Given
        Employe employe = new Employe("Jose", "Patrick", "C00010", LocalDate.now(), 5000.0, 3, 7.0 );
        //When
        Double salaire = 2000D;
        employe.setSalaire(salaire);
        //Then
        Assertions.assertThat(employe.getSalaire()).isEqualTo(salaire);
    }
    @Test
    void testgetPerformance(){
        //Given
        Employe employe = new Employe("Black", "David", "C00012", LocalDate.now(), 8000.0, 3, 7.0 );
        //When
        Integer perf = employe.getPerformance();
        //Then
        Assertions.assertThat(perf).isEqualTo(3);
    }

    @Test
    void equalsHashCodeContracts() {
        EqualsVerifier.forClass(Employe.class).verify();
    }

}
