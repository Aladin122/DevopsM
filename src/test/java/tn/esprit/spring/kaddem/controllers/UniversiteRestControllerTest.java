package tn.esprit.spring.kaddem.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.spring.kaddem.entities.Departement;
import tn.esprit.spring.kaddem.entities.Universite;
import tn.esprit.spring.kaddem.services.IUniversiteService;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UniversiteRestControllerTest {

    @Mock
    private IUniversiteService universiteService;

    @InjectMocks
    private UniversiteRestController universiteRestController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldGetAllUniversites() {
        Universite u1 = new Universite(1, "ESPRIT", null);
        Universite u2 = new Universite(2, "ENIT", null);
        when(universiteService.retrieveAllUniversites()).thenReturn(Arrays.asList(u1, u2));

        List<Universite> result = universiteRestController.getUniversites();

        assertEquals(2, result.size());
        verify(universiteService).retrieveAllUniversites();
    }

    @Test
    void shouldGetUniversiteById() {
        Universite u = new Universite(1, "INSAT", null);
        when(universiteService.retrieveUniversite(1)).thenReturn(u);

        Universite result = universiteRestController.retrieveUniversite(1);

        assertEquals("INSAT", result.getNomUniv());
        verify(universiteService).retrieveUniversite(1);
    }

    @Test
    void shouldDeleteUniversite() {
        universiteRestController.removeUniversite(2);
        verify(universiteService).deleteUniversite(2);
    }

    @Test
    void shouldUpdateUniversite() {
        Universite u = new Universite(3, "IHEC", null);
        when(universiteService.updateUniversite(u)).thenReturn(u);

        Universite result = universiteRestController.updateUniversite(u);

        assertEquals("IHEC", result.getNomUniv());
        verify(universiteService).updateUniversite(u);
    }

    @Test
    void shouldAssignUniversiteToDepartement() {
        universiteRestController.affectertUniversiteToDepartement(1, 10);
        verify(universiteService).assignUniversiteToDepartement(1, 10);
    }

    @Test
    void shouldListDepartementsByUniversite() {
        Set<Departement> deps = new HashSet<>();
        deps.add(new Departement(1, "Informatique"));
        deps.add(new Departement(2, "Mathématiques"));

        when(universiteService.retrieveDepartementsByUniversite(5)).thenReturn(deps);

        Set<Departement> result = universiteRestController.listerDepartementsUniversite(5);

        assertEquals(2, result.size());
        verify(universiteService).retrieveDepartementsByUniversite(5);
    }
}
