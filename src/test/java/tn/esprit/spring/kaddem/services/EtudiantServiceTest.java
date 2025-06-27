package tn.esprit.spring.kaddem.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import tn.esprit.spring.kaddem.entities.Etudiant;
import tn.esprit.spring.kaddem.entities.Departement;
import tn.esprit.spring.kaddem.entities.Equipe;
import tn.esprit.spring.kaddem.repositories.EtudiantRepository;
import tn.esprit.spring.kaddem.repositories.DepartementRepository;
import tn.esprit.spring.kaddem.repositories.EquipeRepository;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EtudiantServiceTest {

    @InjectMocks
    private EtudiantServiceImpl etudiantService;

    @Mock
    private EtudiantRepository etudiantRepository;

    @Mock
    private DepartementRepository departementRepository;

    @Mock
    private EquipeRepository equipeRepository;

    private Etudiant etudiant;
    private Departement departement;
    private Equipe equipe;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        etudiant = new Etudiant();
        etudiant.setIdEtudiant(1);
        etudiant.setNomE("John");
        etudiant.setPrenomE("Doe");

        departement = new Departement();
        departement.setIdDepart(1);
        departement.setNomDepart("Informatique");

        equipe = new Equipe();
        equipe.setIdEquipe(1);
        equipe.setNomEquipe("Equipe Test");
    }

    @Test
    void testRetrieveAllEtudiants() {
        List<Etudiant> etudiants = List.of(etudiant);
        when(etudiantRepository.findAll()).thenReturn(etudiants);

        List<Etudiant> result = etudiantService.retrieveAllEtudiants();

        assertEquals(1, result.size());
        assertEquals("John", result.get(0).getNomE());
        verify(etudiantRepository, times(1)).findAll();
    }

    @Test
    void testAddEtudiant() {
        when(etudiantRepository.save(etudiant)).thenReturn(etudiant);

        Etudiant result = etudiantService.addEtudiant(etudiant);

        assertNotNull(result);
        assertEquals("John", result.getNomE());
        verify(etudiantRepository, times(1)).save(etudiant);
    }

    @Test
    void testUpdateEtudiant() {
        etudiant.setNomE("Jane");
        when(etudiantRepository.save(etudiant)).thenReturn(etudiant);

        Etudiant result = etudiantService.updateEtudiant(etudiant);

        assertEquals("Jane", result.getNomE());
        verify(etudiantRepository, times(1)).save(etudiant);
    }

    @Test
    void testRetrieveEtudiant() {
        when(etudiantRepository.findById(1)).thenReturn(Optional.of(etudiant));

        Etudiant result = etudiantService.retrieveEtudiant(1);

        assertNotNull(result);
        assertEquals(1, result.getIdEtudiant());
        verify(etudiantRepository, times(1)).findById(1);
    }

    @Test
    void testRemoveEtudiant() {
        when(etudiantRepository.findById(1)).thenReturn(Optional.of(etudiant));
        doNothing().when(etudiantRepository).delete(etudiant);

        etudiantService.removeEtudiant(1);

        verify(etudiantRepository, times(1)).delete(etudiant);
    }

    @Test
    void testAssignEtudiantToDepartement() {
        when(etudiantRepository.findById(1)).thenReturn(Optional.of(etudiant));
        when(departementRepository.findById(1)).thenReturn(Optional.of(departement));
        when(etudiantRepository.save(etudiant)).thenReturn(etudiant);

        etudiantService.assignEtudiantToDepartement(1, 1);

        assertEquals(departement, etudiant.getDepartement());
        verify(etudiantRepository, times(1)).save(etudiant);
    }


    @Test
    void testGetEtudiantsByDepartement() {
        List<Etudiant> etudiants = List.of(etudiant);
        when(etudiantRepository.findEtudiantsByDepartement_IdDepart(1)).thenReturn(etudiants);

        List<Etudiant> result = etudiantService.getEtudiantsByDepartement(1);

        assertEquals(1, result.size());
        verify(etudiantRepository, times(1)).findEtudiantsByDepartement_IdDepart(1);
    }
}
