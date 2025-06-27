package tn.esprit.spring.kaddem.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.spring.kaddem.entities.Equipe;
import tn.esprit.spring.kaddem.services.IEquipeService;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EquipeRestControllerTest {

    @Mock
    private IEquipeService equipeService;

    @InjectMocks
    private EquipeRestController equipeRestController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetEquipes() {
        Equipe eq1 = new Equipe();
        Equipe eq2 = new Equipe();
        when(equipeService.retrieveAllEquipes()).thenReturn(Arrays.asList(eq1, eq2));

        List<Equipe> result = equipeRestController.getEquipes();

        assertEquals(2, result.size());
        verify(equipeService, times(1)).retrieveAllEquipes();
    }

    @Test
    void testRetrieveEquipe() {
        Equipe equipe = new Equipe();
        equipe.setIdEquipe(1);
        when(equipeService.retrieveEquipe(1)).thenReturn(equipe);

        Equipe result = equipeRestController.retrieveEquipe(1);

        assertNotNull(result);
        assertEquals(1, result.getIdEquipe());
        verify(equipeService, times(1)).retrieveEquipe(1);
    }

    @Test
    void testAddEquipe() {
        Equipe input = new Equipe();
        Equipe saved = new Equipe();
        saved.setIdEquipe(1);
        when(equipeService.addEquipe(input)).thenReturn(saved);

        Equipe result = equipeRestController.addEquipe(input);

        assertNotNull(result);
        assertEquals(1, result.getIdEquipe());
        verify(equipeService, times(1)).addEquipe(input);
    }

    @Test
    void testRemoveEquipe() {
        equipeRestController.removeEquipe(1);
        verify(equipeService, times(1)).deleteEquipe(1);
    }

    @Test
    void testUpdateEquipe() {
        Equipe input = new Equipe();
        input.setIdEquipe(1);
        when(equipeService.updateEquipe(input)).thenReturn(input);

        Equipe result = equipeRestController.updateEtudiant(input);

        assertNotNull(result);
        assertEquals(1, result.getIdEquipe());
        verify(equipeService, times(1)).updateEquipe(input);
    }

    @Test
    void testFaireEvoluerEquipes() {
        equipeRestController.faireEvoluerEquipes();
        verify(equipeService, times(1)).evoluerEquipes();
    }
}
