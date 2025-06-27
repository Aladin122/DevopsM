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

class UniversiteServiceImplTest {

    @InjectMocks
    private UniversiteServiceImpl universiteService;

    @Mock
    private UniversiteRepository universiteRepository;

    @Mock
    private DepartementRepository departementRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRetrieveAllUniversites() {
        List<Universite> universites = Arrays.asList(new Universite(), new Universite());
        when(universiteRepository.findAll()).thenReturn(universites);

        List<Universite> result = universiteService.retrieveAllUniversites();

        assertEquals(2, result.size());
        verify(universiteRepository).findAll();
    }

    @Test
    void testAddUniversite() {
        Universite universite = new Universite();
        when(universiteRepository.save(universite)).thenReturn(universite);

        Universite result = universiteService.addUniversite(universite);

        assertEquals(universite, result);
        verify(universiteRepository).save(universite);
    }

    @Test
    void testUpdateUniversite() {
        Universite universite = new Universite();
        when(universiteRepository.save(universite)).thenReturn(universite);

        Universite result = universiteService.updateUniversite(universite);

        assertEquals(universite, result);
        verify(universiteRepository).save(universite);
    }

    @Test
    void testRetrieveUniversite() {
        Universite u = new Universite();
        u.setIdUniv(1);
        when(universiteRepository.findById(1)).thenReturn(Optional.of(u));

        Universite result = universiteService.retrieveUniversite(1);

        assertEquals(1, result.getIdUniv());
        verify(universiteRepository).findById(1);
    }

    @Test
    void testDeleteUniversite() {
        Universite u = new Universite();
        when(universiteRepository.findById(1)).thenReturn(Optional.of(u));

        universiteService.deleteUniversite(1);

        verify(universiteRepository).delete(u);
    }

    @Test
    void testAssignUniversiteToDepartement() {
        Universite universite = new Universite();
        universite.setDepartements(new HashSet<>());
        Departement departement = new Departement();

        when(universiteRepository.findById(1)).thenReturn(Optional.of(universite));
        when(departementRepository.findById(2)).thenReturn(Optional.of(departement));

        universiteService.assignUniversiteToDepartement(1, 2);

        assertTrue(universite.getDepartements().contains(departement));
        verify(universiteRepository).save(universite);
    }

    @Test
    void testRetrieveDepartementsByUniversite() {
        Universite universite = new Universite();
        Set<Departement> departements = new HashSet<>();
        departements.add(new Departement());
        universite.setDepartements(departements);

        when(universiteRepository.findById(1)).thenReturn(Optional.of(universite));

        Set<Departement> result = universiteService.retrieveDepartementsByUniversite(1);

        assertEquals(1, result.size());
        verify(universiteRepository).findById(1);
    }
}
