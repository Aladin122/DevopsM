package tn.esprit.spring.kaddem.repositories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import tn.esprit.spring.kaddem.entities.Departement;
import tn.esprit.spring.kaddem.entities.Etudiant;
import tn.esprit.spring.kaddem.entities.Option;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class EtudiantRepositoryTest {

    @Autowired
    private EtudiantRepository etudiantRepository;

    @Autowired
    private DepartementRepository departementRepository;

    private Departement departement;

    @BeforeEach
    void setup() {
        // Create and save a sample Departement
        departement = new Departement();
        departement.setNomDepart("Informatique");
        departement = departementRepository.save(departement);

        // Create and save sample Etudiants
        Etudiant et1 = new Etudiant("Ben", "Ali", Option.GAMIX);
        et1.setDepartement(departement);

        Etudiant et2 = new Etudiant("Ben", "Salah", Option.SE);
        et2.setDepartement(departement);

        etudiantRepository.save(et1);
        etudiantRepository.save(et2);
    }

    @Test
    void testFindEtudiantsByDepartement_IdDepart() {
        List<Etudiant> result = etudiantRepository.findEtudiantsByDepartement_IdDepart(departement.getIdDepart());

        assertNotNull(result);
        assertEquals(2, result.size());
        assertTrue(result.stream().anyMatch(e -> e.getNomE().equals("Ben")));
    }

    @Test
    void testFindByNomEAndPrenomEExists() {
        Etudiant found = etudiantRepository.findByNomEAndPrenomE("Ben", "Ali");

        assertNotNull(found);
        assertEquals(Option.GAMIX, found.getOp());
    }

    @Test
    void testFindByNomEAndPrenomENotFound() {
        Etudiant found = etudiantRepository.findByNomEAndPrenomE("NonExistent", "User");
        assertNull(found);
    }
}
