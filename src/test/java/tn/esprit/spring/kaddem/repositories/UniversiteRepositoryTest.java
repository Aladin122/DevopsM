package tn.esprit.spring.kaddem.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import tn.esprit.spring.kaddem.entities.Universite;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
 class UniversiteRepositoryTest {

    @Autowired
    private UniversiteRepository universiteRepository;

    @Test
     void testSaveAndRetrieveUniversite() {
        // Create and save a Universite
        Universite universite = new Universite();
        universite.setNomUniv("Université de Tunis");

        Universite saved = universiteRepository.save(universite);

        assertNotNull(saved.getIdUniv(), "ID should be generated");
        assertEquals("Université de Tunis", saved.getNomUniv());

        // Retrieve by ID
        Optional<Universite> retrieved = universiteRepository.findById(saved.getIdUniv());
        assertTrue(retrieved.isPresent(), "Universite should be found");
        assertEquals("Université de Tunis", retrieved.get().getNomUniv());
    }

    @Test
     void testDeleteUniversite() {
        Universite universite = new Universite();
        universite.setNomUniv("ESPRIT");
        Universite saved = universiteRepository.save(universite);

        Integer id = saved.getIdUniv();
        universiteRepository.deleteById(id);

        assertFalse(universiteRepository.findById(id).isPresent(), "Universite should be deleted");
    }
}
