package tn.esprit.spring.kaddem.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.spring.kaddem.entities.Departement;
import tn.esprit.spring.kaddem.services.IDepartementService;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DepartementRestControllerTest {

    @Mock
    private IDepartementService departementService;

    @InjectMocks
    private DepartementRestController departementRestController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldRetrieveAllDepartements() {
        // Arrange
        Departement dep1 = new Departement(1, "Aouina");
        Departement dep2 = new Departement(2, "Nabeul");
        when(departementService.retrieveAllDepartements()).thenReturn(Arrays.asList(dep1, dep2));

        // Act
        List<Departement> result = departementRestController.getDepartements();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Aouina", result.get(0).getNomDepart());
        assertEquals("Nabeul", result.get(1).getNomDepart());
        verify(departementService, times(1)).retrieveAllDepartements();
    }

    @Test
    void shouldRetrieveDepartementById() {
        // Arrange
        Departement dep = new Departement(1, "Aouina");
        when(departementService.retrieveDepartement(1)).thenReturn(dep);

        // Act
        Departement result = departementRestController.retrieveDepartement(1);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.getIdDepart());
        assertEquals("Aouina", result.getNomDepart());
        verify(departementService, times(1)).retrieveDepartement(1);
    }

    @Test
    void shouldAddDepartement() {
        // Arrange
        Departement dep = new Departement("Aouina");
        Departement savedDep = new Departement(1, "Aouina");
        when(departementService.addDepartement(dep)).thenReturn(savedDep);

        // Act
        Departement result = departementRestController.addDepartement(dep);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.getIdDepart());
        assertEquals("Aouina", result.getNomDepart());
        verify(departementService, times(1)).addDepartement(dep);
    }

    @Test
    void shouldUpdateDepartement() {
        // Arrange
        Departement dep = new Departement(1, "Aouina");
        when(departementService.updateDepartement(dep)).thenReturn(dep);

        // Act
        Departement result = departementRestController.updateDepartement(dep);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.getIdDepart());
        assertEquals("Aouina", result.getNomDepart());
        verify(departementService, times(1)).updateDepartement(dep);
    }

    @Test
    void shouldDeleteDepartement() {
        // Act
        departementRestController.removeDepartement(1);

        // Assert
        verify(departementService, times(1)).deleteDepartement(1);
    }
}