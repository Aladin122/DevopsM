package tn.esprit.spring.kaddem.Enteties;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tn.esprit.spring.kaddem.entities.Contrat;
import tn.esprit.spring.kaddem.entities.Etudiant;
import tn.esprit.spring.kaddem.entities.Specialite;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

 class ContratTest {

    private Contrat contrat;

    @BeforeEach
     void setUp() {
        contrat = new Contrat();
    }

    @Test
     void testNoArgsConstructor() {
        assertThat(contrat).isNotNull();
    }

    @Test
     void testSettersAndGetters() {
        Integer id = 1;
        Date startDate = new Date();
        Date endDate = new Date();
        Specialite specialite = Specialite.IA; // Replace with valid enum from your project
        Boolean archived = true;
        Integer montant = 5000;
        Etudiant etudiant = new Etudiant(); // Assuming default constructor is available

        contrat.setIdContrat(id);
        contrat.setDateDebutContrat(startDate);
        contrat.setDateFinContrat(endDate);
        contrat.setSpecialite(specialite);
        contrat.setArchive(archived);
        contrat.setMontantContrat(montant);
        contrat.setEtudiant(etudiant);

        assertThat(contrat.getIdContrat()).isEqualTo(id);
        assertThat(contrat.getDateDebutContrat()).isEqualTo(startDate);
        assertThat(contrat.getDateFinContrat()).isEqualTo(endDate);
        assertThat(contrat.getSpecialite()).isEqualTo(specialite);
        assertThat(contrat.getArchive()).isEqualTo(archived);
        assertThat(contrat.getMontantContrat()).isEqualTo(montant);
        assertThat(contrat.getEtudiant()).isEqualTo(etudiant);
    }

    @Test
     void testAllArgsConstructor() {
        Date startDate = new Date();
        Date endDate = new Date();
        Specialite specialite = Specialite.RESEAUX;
        Boolean archived = false;
        Integer montant = 4000;

        Contrat c = new Contrat(startDate, endDate, specialite, archived, montant);

        assertThat(c.getDateDebutContrat()).isEqualTo(startDate);
        assertThat(c.getDateFinContrat()).isEqualTo(endDate);
        assertThat(c.getSpecialite()).isEqualTo(specialite);
        assertThat(c.getArchive()).isEqualTo(archived);
        assertThat(c.getMontantContrat()).isEqualTo(montant);
    }
}
