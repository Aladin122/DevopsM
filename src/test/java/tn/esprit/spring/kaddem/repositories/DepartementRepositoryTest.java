package tn.esprit.spring.kaddem.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import tn.esprit.spring.kaddem.entities.Departement;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class DepartementRepositoryTest {

    @Autowired
    private DepartementRepository departementRepository;

    @Test
    void shouldGetAllDepartments() {
        // Exemple simple, à adapter si tu as des données en base
        var departements = departementRepository.findAll();
        assertNotNull(departements);
        // assertEquals(expectedSize, departements.size()); // décommente et ajuste si tu as des données
    }

    @Test
    void shouldGetDepartementById() {
        // Arrange : créer et sauvegarder un nouveau département
        Departement dep = new Departement();
        dep.setNomDepart("TestDep");
        Departement savedDep = departementRepository.save(dep); // L'id est généré automatiquement

        // Act : récupérer ce département par son id
        Optional<Departement> optionalDep = departementRepository.findById(savedDep.getIdDepart());

        // Assert : vérifier que l'on trouve bien le département
        assertTrue(optionalDep.isPresent(), "Departement should be found by ID");
        assertEquals("TestDep", optionalDep.get().getNomDepart());
    }


    @Test
    void shouldSaveDepartement() {
        Departement dep = new Departement();
        // Ne fixe pas l'id manuellement si c'est auto-généré
        dep.setNomDepart("soukra");
        Departement savedDep = departementRepository.save(dep);

        assertNotNull(savedDep.getIdDepart());
        assertEquals("soukra", savedDep.getNomDepart());
    }

    @Test
    void shouldUpdateDepartement() {
        Departement dep = new Departement();
        dep.setNomDepart("Old Name");
        Departement savedDep = departementRepository.save(dep);

        savedDep.setNomDepart("Aouina");
        Departement updatedDep = departementRepository.save(savedDep);

        assertEquals("Aouina", updatedDep.getNomDepart());
    }

    @Test
    void shouldDeleteDepartement() {
        Departement dep = new Departement();
        dep.setNomDepart("ToDelete");
        Departement savedDep = departementRepository.save(dep);

        departementRepository.deleteById(savedDep.getIdDepart());
        assertFalse(departementRepository.findById(savedDep.getIdDepart()).isPresent());
    }
}
