package tn.esprit.spring.kaddem.repositories;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import tn.esprit.spring.kaddem.entities.DetailEquipe;
import tn.esprit.spring.kaddem.entities.Equipe;
import tn.esprit.spring.kaddem.entities.Niveau;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class EquipeRepositoryTest {

    @Autowired
    private EquipeRepository equipeRepository;

    private Equipe equipe;

    @BeforeEach
    void setUp() {
        DetailEquipe detailEquipe = new DetailEquipe(42, "IA & Deep Learning");
        equipe = new Equipe("Equipe Alpha", Niveau.EXPERT, null, detailEquipe);
        equipe = equipeRepository.save(equipe);
    }

    @Test
    void testSaveAndFindById() {
        Optional<Equipe> optionalEquipe = equipeRepository.findById(equipe.getIdEquipe());
        assertTrue(optionalEquipe.isPresent());

        Equipe found = optionalEquipe.get();
        assertEquals("Equipe Alpha", found.getNomEquipe());
        assertEquals(Niveau.EXPERT, found.getNiveau());
        assertNotNull(found.getDetailEquipe());
        assertEquals("IA & Deep Learning", found.getDetailEquipe().getThematique());
    }

    @Test
    void testDeleteEquipe() {
        Integer id = equipe.getIdEquipe();
        equipeRepository.deleteById(id);
        assertFalse(equipeRepository.findById(id).isPresent());
    }

    @Test
    void testUpdateEquipe() {
        equipe.setNomEquipe("Equipe Beta");
        equipe.getDetailEquipe().setThematique("Blockchain");
        Equipe updated = equipeRepository.save(equipe);

        assertEquals("Equipe Beta", updated.getNomEquipe());
        assertEquals("Blockchain", updated.getDetailEquipe().getThematique());
    }

    @Test
    void testCountEquipes() {
        long count = equipeRepository.count();
        assertEquals(1, count);
    }
}
