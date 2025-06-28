package tn.esprit.spring.kaddem.Enteties;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tn.esprit.spring.kaddem.entities.Departement;
import tn.esprit.spring.kaddem.entities.Etudiant;

import java.util.HashSet;

import static org.assertj.core.api.Assertions.assertThat;

class DepartementTest {

    private Departement departement;

    @BeforeEach
    void setUp() {
        departement = new Departement();
    }

    @Test
    void testNoArgsConstructor() {
        assertThat(departement).isNotNull();
    }

    @Test
    void testSettersAndGetters() {
        Integer id = 1;
        String name = "Informatique";

        departement.setIdDepart(id);
        departement.setNomDepart(name);

        assertThat(departement.getIdDepart()).isEqualTo(id);
        assertThat(departement.getNomDepart()).isEqualTo(name);
    }

    @Test
    void testEtudiantsSet() {
        var etudiants = new HashSet<Etudiant>();
        etudiants.add(new Etudiant());

        departement.setEtudiants(etudiants);

        assertThat(departement.getEtudiants()).hasSize(1);
    }

    @Test
    void testConstructorWithName() {
        var d = new Departement("Electrique");
        assertThat(d.getNomDepart()).isEqualTo("Electrique");
    }

    @Test
    void testConstructorWithIdAndName() {
        var d = new Departement(2, "Mécanique");
        assertThat(d.getIdDepart()).isEqualTo(2);
        assertThat(d.getNomDepart()).isEqualTo("Mécanique");
    }
}

