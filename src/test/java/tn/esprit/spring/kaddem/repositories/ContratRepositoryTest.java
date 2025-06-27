package tn.esprit.spring.kaddem.repositories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import tn.esprit.spring.kaddem.entities.Contrat;
import tn.esprit.spring.kaddem.entities.Specialite;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ContratRepositoryTest {

    @Autowired
    private ContratRepository contratRepository;

    private Contrat contrat1;
    private Contrat contrat2;
    private Contrat contrat3;

    @BeforeEach
    void setUp() {
        Calendar cal = Calendar.getInstance();

        cal.add(Calendar.DAY_OF_MONTH, -10);
        Date start = cal.getTime();

        cal.add(Calendar.DAY_OF_MONTH, 5);
        Date mid = cal.getTime();

        cal.add(Calendar.DAY_OF_MONTH, 5);
        Date end = cal.getTime();

        contrat1 = new Contrat(null, start, mid, Specialite.IA, true, 1000);
        contrat2 = new Contrat(null, start, end, Specialite.RESEAUX, false, 2000);
        contrat3 = new Contrat(null, mid, end, Specialite.CLOUD, true, 1500);

        contratRepository.save(contrat1);
        contratRepository.save(contrat2);
        contratRepository.save(contrat3);
    }

    @Test
    void testFindAll() {
        List<Contrat> contrats = contratRepository.findAll();
        assertEquals(3, contrats.size());
    }

    @Test
    void testFindByIdContrat() {
        Contrat saved = contratRepository.save(
                new Contrat(new Date(), new Date(), Specialite.IA, false, 500)
        );
        Contrat found = contratRepository.findByIdContrat(saved.getIdContrat());
        assertNotNull(found);
        assertEquals(saved.getIdContrat(), found.getIdContrat());
    }

    @Test
    void testGetNbContratsValides() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, -20);
        Date startRange = cal.getTime();

        cal.add(Calendar.DAY_OF_MONTH, 40);
        Date endRange = cal.getTime();

        Integer count = contratRepository.getnbContratsValides(startRange, endRange);
        assertEquals(2, count); // contrat1 and contrat3 are archived and in range
    }
}
