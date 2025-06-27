package tn.esprit.spring.kaddem.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import tn.esprit.spring.kaddem.entities.*;
import tn.esprit.spring.kaddem.repositories.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EtudiantServiceImplTest {

    @InjectMocks
    private EtudiantServiceImpl etudiantService;

    @Mock
    private EtudiantRepository etudiantRepository;

    @Mock
    private ContratRepository contratRepository;

    @Mock
    private EquipeRepository equipeRepository;

    @Mock
    private DepartementRepository departementRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRetrieveAllEtudiants() {
        List<Etudiant> etudiants = Arrays.asList(new Etudiant(), new Etudiant());
        when(etudiantRepository.findAll()).thenReturn(etudiants);

        List<Etudiant> result = etudiantService.retrieveAllEtudiants();

        assertEquals(2, result.size());
        verify(etudiantRepository).findAll();
    }

    @Test
    void testAddEtudiant() {
        Etudiant etudiant = new Etudiant();
        when(etudiantRepository.save(etudiant)).thenReturn(etudiant);

        Etudiant result = etudiantService.addEtudiant(etudiant);

        assertEquals(etudiant, result);
        verify(etudiantRepository).save(etudiant);
    }

    @Test
    void testUpdateEtudiant() {
        Etudiant etudiant = new Etudiant();
        when(etudiantRepository.save(etudiant)).thenReturn(etudiant);

        Etudiant result = etudiantService.updateEtudiant(etudiant);

        assertEquals(etudiant, result);
        verify(etudiantRepository).save(etudiant);
    }

    @Test
    void testRetrieveEtudiant() {
        Etudiant etudiant = new Etudiant();
        etudiant.setIdEtudiant(1);
        when(etudiantRepository.findById(1)).thenReturn(Optional.of(etudiant));

        Etudiant result = etudiantService.retrieveEtudiant(1);

        assertEquals(1, result.getIdEtudiant());
        verify(etudiantRepository).findById(1);
    }

    @Test
    void testRemoveEtudiant() {
        Etudiant etudiant = new Etudiant();
        when(etudiantRepository.findById(1)).thenReturn(Optional.of(etudiant));

        etudiantService.removeEtudiant(1);

        verify(etudiantRepository).delete(etudiant);
    }

    @Test
    void testAssignEtudiantToDepartement() {
        Etudiant etudiant = new Etudiant();
        Departement departement = new Departement();
        when(etudiantRepository.findById(1)).thenReturn(Optional.of(etudiant));
        when(departementRepository.findById(2)).thenReturn(Optional.of(departement));

        etudiantService.assignEtudiantToDepartement(1, 2);

        assertEquals(departement, etudiant.getDepartement());
        verify(etudiantRepository).save(etudiant);
    }

    @Test
    void testAddAndAssignEtudiantToEquipeAndContract() {
        Etudiant etudiant = new Etudiant();
        Contrat contrat = new Contrat();
        Equipe equipe = new Equipe();
        equipe.setEtudiants(new HashSet<>());

        when(contratRepository.findById(1)).thenReturn(Optional.of(contrat));
        when(equipeRepository.findById(2)).thenReturn(Optional.of(equipe));

        Etudiant result = etudiantService.addAndAssignEtudiantToEquipeAndContract(etudiant, 1, 2);

        assertEquals(etudiant, contrat.getEtudiant());
        assertTrue(equipe.getEtudiants().contains(etudiant));
    }

    @Test
    void testGetEtudiantsByDepartement() {
        List<Etudiant> list = Arrays.asList(new Etudiant(), new Etudiant());
        when(etudiantRepository.findEtudiantsByDepartement_IdDepart(1)).thenReturn(list);

        List<Etudiant> result = etudiantService.getEtudiantsByDepartement(1);

        assertEquals(2, result.size());
        verify(etudiantRepository).findEtudiantsByDepartement_IdDepart(1);
    }
}
