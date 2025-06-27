package tn.esprit.spring.kaddem.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import tn.esprit.spring.kaddem.entities.Contrat;
import tn.esprit.spring.kaddem.entities.Etudiant;
import tn.esprit.spring.kaddem.entities.Specialite;
import tn.esprit.spring.kaddem.repositories.ContratRepository;
import tn.esprit.spring.kaddem.repositories.EtudiantRepository;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ContratServiceTest {

    @InjectMocks
    private ContratServiceImpl contratService;

    @Mock
    private ContratRepository contratRepository;

    @Mock
    private EtudiantRepository etudiantRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRetrieveAllContrats() {
        List<Contrat> contrats = new ArrayList<>();
        contrats.add(new Contrat());
        when(contratRepository.findAll()).thenReturn(contrats);

        List<Contrat> result = contratService.retrieveAllContrats();

        assertEquals(1, result.size());
        verify(contratRepository, times(1)).findAll();
    }

    @Test
    void testAddContrat() {
        Contrat contrat = new Contrat();
        when(contratRepository.save(contrat)).thenReturn(contrat);

        Contrat result = contratService.addContrat(contrat);

        assertEquals(contrat, result);
        verify(contratRepository, times(1)).save(contrat);
    }

    @Test
    void testUpdateContrat() {
        Contrat contrat = new Contrat();
        when(contratRepository.save(contrat)).thenReturn(contrat);

        Contrat result = contratService.updateContrat(contrat);

        assertEquals(contrat, result);
        verify(contratRepository, times(1)).save(contrat);
    }

    @Test
    void testRetrieveContrat() {
        Contrat contrat = new Contrat();
        contrat.setIdContrat(1);
        when(contratRepository.findById(1)).thenReturn(Optional.of(contrat));

        Contrat result = contratService.retrieveContrat(1);

        assertNotNull(result);
        assertEquals(1, result.getIdContrat());
    }

    @Test
    void testRemoveContrat() {
        Contrat contrat = new Contrat();
        contrat.setIdContrat(1);
        when(contratRepository.findById(1)).thenReturn(Optional.of(contrat));

        contratService.removeContrat(1);

        verify(contratRepository, times(1)).delete(contrat);
    }

    @Test
    void testAffectContratToEtudiant_WhenLessThan5Contracts() {
        Etudiant etudiant = new Etudiant();
        etudiant.setIdEtudiant(1);

        Contrat existingContract = new Contrat();
        existingContract.setArchive(true); // archived contract
        Set<Contrat> existingContracts = new HashSet<>();
        existingContracts.add(existingContract);
        etudiant.setContrats(existingContracts);

        Contrat contrat = new Contrat();
        contrat.setIdContrat(1);

        when(etudiantRepository.findByNomEAndPrenomE("John", "Doe")).thenReturn(etudiant);
        when(contratRepository.findByIdContrat(1)).thenReturn(contrat);
        when(contratRepository.save(any())).thenReturn(contrat);

        Contrat result = contratService.affectContratToEtudiant(1, "John", "Doe");

        assertEquals(etudiant, result.getEtudiant());
        verify(contratRepository).save(contrat);
    }

    @Test
    void testNbContratsValides() {
        Date start = new Date();
        Date end = new Date();
        when(contratRepository.getnbContratsValides(start, end)).thenReturn(5);

        Integer count = contratService.nbContratsValides(start, end);

        assertEquals(5, count);
    }

    @Test
    void testGetChiffreAffaireEntreDeuxDates() {
        Contrat c1 = new Contrat();
        c1.setSpecialite(Specialite.IA);

        Contrat c2 = new Contrat();
        c2.setSpecialite(Specialite.CLOUD);

        List<Contrat> contrats = List.of(c1, c2);

        when(contratRepository.findAll()).thenReturn(contrats);

        Calendar cal = Calendar.getInstance();
        cal.set(2024, Calendar.JANUARY, 1);
        Date startDate = cal.getTime();
        cal.set(2024, Calendar.MARCH, 1);
        Date endDate = cal.getTime();

        float result = contratService.getChiffreAffaireEntreDeuxDates(startDate, endDate);

        assertTrue(result > 0);
    }

}
