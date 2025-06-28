package tn.esprit.spring.kaddem.Enteties;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tn.esprit.spring.kaddem.entities.Departement;
import tn.esprit.spring.kaddem.entities.Universite;

import java.util.HashSet;

import static org.assertj.core.api.Assertions.assertThat;

class UniversiteTest {

    private Universite universite;

    @BeforeEach
    void setUp() {
        universite = new Universite();
    }

    @Test
    void testNoArgsConstructor() {
        assertThat(universite).isNotNull();
    }

    @Test
    void testAllArgsConstructor() {
        var departements = new HashSet<Departement>();
        departements.add(new Departement());

        Universite u = new Universite(1, "ESPRIT", departements);

        assertThat(u.getIdUniv()).isEqualTo(1);
        assertThat(u.getNomUniv()).isEqualTo("ESPRIT");
        assertThat(u.getDepartements()).isSameAs(departements);
    }

    @Test
    void testSettersAndGetters() {
        Integer id = 42;
        String nom = "ISI";
        var departements = new HashSet<Departement>();
        departements.add(new Departement());

        universite.setIdUniv(id);
        universite.setNomUniv(nom);
        universite.setDepartements(departements);

        assertThat(universite.getIdUniv()).isEqualTo(id);
        assertThat(universite.getNomUniv()).isEqualTo(nom);
        assertThat(universite.getDepartements()).hasSize(1);
    }
}
