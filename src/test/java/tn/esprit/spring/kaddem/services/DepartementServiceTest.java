package tn.esprit.spring.kaddem.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import tn.esprit.spring.kaddem.entities.Departement;
import tn.esprit.spring.kaddem.repositories.DepartementRepository;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DepartementServiceTest {

    @InjectMocks
    private DepartementServiceImpl departementService;

    @Mock
    private DepartementRepository departementRepository;

    private Departement departement;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        // Initialize a sample Departement object
        departement = new Departement();
        departement.setIdDepart(1);
        departement.setNomDepart("Informatique");
    }

    @Test
    void testRetrieveAllDepartements() {
        List<Departement> departements = List.of(departement);
        when(departementRepository.findAll()).thenReturn(departements);

        List<Departement> result = departementService.retrieveAllDepartements();

        assertEquals(1, result.size());
        assertEquals("Informatique", result.get(0).getNomDepart());
        verify(departementRepository, times(1)).findAll();
    }

    @Test
    void testAddDepartement() {
        when(departementRepository.save(departement)).thenReturn(departement);

        Departement result = departementService.addDepartement(departement);

        assertNotNull(result);
        assertEquals("Informatique", result.getNomDepart());
        verify(departementRepository, times(1)).save(departement);
    }

    @Test
    void testUpdateDepartement() {
        when(departementRepository.save(departement)).thenReturn(departement);

        Departement result = departementService.updateDepartement(departement);

        assertNotNull(result);
        assertEquals("Informatique", result.getNomDepart());
        verify(departementRepository, times(1)).save(departement);
    }

    @Test
    void testRetrieveDepartement() {
        when(departementRepository.findById(1)).thenReturn(Optional.of(departement));

        Departement result = departementService.retrieveDepartement(1);

        assertNotNull(result);
        assertEquals(1, result.getIdDepart());
        assertEquals("Informatique", result.getNomDepart());
        verify(departementRepository, times(1)).findById(1);
    }

    @Test
    void testDeleteDepartement() {
        when(departementRepository.findById(1)).thenReturn(Optional.of(departement));
        doNothing().when(departementRepository).delete(departement);

        departementService.deleteDepartement(1);

        verify(departementRepository, times(1)).delete(departement);
    }
}
