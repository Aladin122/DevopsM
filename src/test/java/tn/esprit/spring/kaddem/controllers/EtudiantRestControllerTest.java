package tn.esprit.spring.kaddem.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.spring.kaddem.entities.Etudiant;
import tn.esprit.spring.kaddem.services.IEtudiantService;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EtudiantRestControllerTest {

    @Mock
    private IEtudiantService etudiantService;

    @InjectMocks
    private EtudiantRestController etudiantRestController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetEtudiants() {
        Etudiant e1 = new Etudiant();
        Etudiant e2 = new Etudiant();
        when(etudiantService.retrieveAllEtudiants()).thenReturn(Arrays.asList(e1, e2));

        List<Etudiant> result = etudiantRestController.getEtudiants();

        assertEquals(2, result.size());
        verify(etudiantService, times(1)).retrieveAllEtudiants();
    }

    @Test
    void testRetrieveEtudiant() {
        Etudiant e = new Etudiant();
        e.setIdEtudiant(1);
        when(etudiantService.retrieveEtudiant(1)).thenReturn(e);

        Etudiant result = etudiantRestController.retrieveEtudiant(1);

        assertNotNull(result);
        assertEquals(1, result.getIdEtudiant());
        verify(etudiantService, times(1)).retrieveEtudiant(1);
    }

    @Test
    void testAddEtudiant() {
        Etudiant input = new Etudiant();
        Etudiant saved = new Etudiant();
        saved.setIdEtudiant(1);
        when(etudiantService.addEtudiant(input)).thenReturn(saved);

        Etudiant result = etudiantRestController.addEtudiant(input);

        assertNotNull(result);
        assertEquals(1, result.getIdEtudiant());
        verify(etudiantService, times(1)).addEtudiant(input);
    }

    @Test
    void testRemoveEtudiant() {
        etudiantRestController.removeEtudiant(1);
        verify(etudiantService, times(1)).removeEtudiant(1);
    }

    @Test
    void testUpdateEtudiant() {
        Etudiant input = new Etudiant();
        input.setIdEtudiant(1);
        when(etudiantService.updateEtudiant(input)).thenReturn(input);

        Etudiant result = etudiantRestController.updateEtudiant(input);

        assertNotNull(result);
        assertEquals(1, result.getIdEtudiant());
        verify(etudiantService, times(1)).updateEtudiant(input);
    }

    @Test
    void testAffecterEtudiantToDepartement() {
        etudiantRestController.affecterEtudiantToDepartement(1, 2);
        verify(etudiantService, times(1)).assignEtudiantToDepartement(1, 2);
    }

    @Test
    void testAddEtudiantWithEquipeAndContract() {
        Etudiant e = new Etudiant();
        Etudiant saved = new Etudiant();
        saved.setIdEtudiant(5);
        when(etudiantService.addAndAssignEtudiantToEquipeAndContract(e, 3, 4)).thenReturn(saved);

        Etudiant result = etudiantRestController.addEtudiantWithEquipeAndContract(e, 3, 4);

        assertNotNull(result);
        assertEquals(5, result.getIdEtudiant());
        verify(etudiantService, times(1)).addAndAssignEtudiantToEquipeAndContract(e, 3, 4);
    }

    @Test
    void testGetEtudiantsParDepartement() {
        Etudiant e1 = new Etudiant();
        Etudiant e2 = new Etudiant();
        when(etudiantService.getEtudiantsByDepartement(2)).thenReturn(Arrays.asList(e1, e2));

        List<Etudiant> result = etudiantRestController.getEtudiantsParDepartement(2);

        assertEquals(2, result.size());
        verify(etudiantService, times(1)).getEtudiantsByDepartement(2);
    }
}
