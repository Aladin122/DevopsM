package tn.esprit.spring.kaddem.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import tn.esprit.spring.kaddem.entities.Departement;
import tn.esprit.spring.kaddem.entities.Universite;
import tn.esprit.spring.kaddem.repositories.DepartementRepository;
import tn.esprit.spring.kaddem.repositories.UniversiteRepository;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UniversiteServiceTest {

    @InjectMocks
    private UniversiteServiceImpl universiteService;

    @Mock
    private UniversiteRepository universiteRepository;

    @Mock
    private DepartementRepository departementRepository;

    private Universite universite;
    private Departement departement;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        universite = new Universite();
        universite.setIdUniv(1);
        universite.setNomUniv("Université Test");
        universite.setDepartements(new HashSet<>());

        departement = new Departement();
        departement.setIdDepart(1);
        departement.setNomDepart("Informatique");
    }

    @Test
    void testRetrieveAllUniversites() {
        List<Universite> list = List.of(universite);
        when(universiteRepository.findAll()).thenReturn(list);

        List<Universite> result = universiteService.retrieveAllUniversites();

        assertEquals(1, result.size());
        verify(universiteRepository, times(1)).findAll();
    }

    @Test
    void testAddUniversite() {
        when(universiteRepository.save(universite)).thenReturn(universite);

        Universite result = universiteService.addUniversite(universite);

        assertNotNull(result);
        assertEquals("Université Test", result.getNomUniv());
        verify(universiteRepository, times(1)).save(universite);
    }

    @Test
    void testUpdateUniversite() {
        universite.setNomUniv("Updated Name");
        when(universiteRepository.save(universite)).thenReturn(universite);

        Universite result = universiteService.updateUniversite(universite);

        assertEquals("Updated Name", result.getNomUniv());
        verify(universiteRepository, times(1)).save(universite);
    }

    @Test
    void testRetrieveUniversite() {
        when(universiteRepository.findById(1)).thenReturn(Optional.of(universite));

        Universite result = universiteService.retrieveUniversite(1);

        assertNotNull(result);
        assertEquals(1, result.getIdUniv());
        verify(universiteRepository, times(1)).findById(1);
    }

    @Test
    void testDeleteUniversite() {
        when(universiteRepository.findById(1)).thenReturn(Optional.of(universite));
        doNothing().when(universiteRepository).delete(universite);

        universiteService.deleteUniversite(1);

        verify(universiteRepository, times(1)).delete(universite);
    }

    @Test
    void testAssignUniversiteToDepartement() {
        when(universiteRepository.findById(1)).thenReturn(Optional.of(universite));
        when(departementRepository.findById(1)).thenReturn(Optional.of(departement));
        when(universiteRepository.save(universite)).thenReturn(universite);

        universiteService.assignUniversiteToDepartement(1, 1);

        assertTrue(universite.getDepartements().contains(departement));
        verify(universiteRepository, times(1)).save(universite);
    }

    @Test
    void testRetrieveDepartementsByUniversite() {
        universite.getDepartements().add(departement);
        when(universiteRepository.findById(1)).thenReturn(Optional.of(universite));

        Set<Departement> result = universiteService.retrieveDepartementsByUniversite(1);

        assertEquals(1, result.size());
        verify(universiteRepository, times(1)).findById(1);
    }
}
