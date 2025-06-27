package tn.esprit.spring.kaddem.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.spring.kaddem.entities.Contrat;
import tn.esprit.spring.kaddem.services.IContratService;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ContratRestControllerTest {

    @Mock
    private IContratService contratService;

    @InjectMocks
    private ContratRestController contratRestController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetContrats() {
        Contrat c1 = new Contrat();
        Contrat c2 = new Contrat();
        when(contratService.retrieveAllContrats()).thenReturn(Arrays.asList(c1, c2));

        List<Contrat> result = contratRestController.getContrats();

        assertEquals(2, result.size());
        verify(contratService).retrieveAllContrats();
    }

    @Test
    void testRetrieveContrat() {
        Contrat c = new Contrat();
        c.setIdContrat(1);
        when(contratService.retrieveContrat(1)).thenReturn(c);

        Contrat result = contratRestController.retrieveContrat(1);

        assertNotNull(result);
        assertEquals(1, result.getIdContrat());
        verify(contratService).retrieveContrat(1);
    }

    @Test
    void testAddContrat() {
        Contrat input = new Contrat();
        Contrat saved = new Contrat();
        saved.setIdContrat(1);
        when(contratService.addContrat(input)).thenReturn(saved);

        Contrat result = contratRestController.addContrat(input);

        assertEquals(1, result.getIdContrat());
        verify(contratService).addContrat(input);
    }

    @Test
    void testUpdateContrat() {
        Contrat input = new Contrat();
        input.setIdContrat(1);
        when(contratService.updateContrat(input)).thenReturn(input);

        Contrat result = contratRestController.updateContrat(input);

        assertEquals(1, result.getIdContrat());
        verify(contratService).updateContrat(input);
    }

    @Test
    void testRemoveContrat() {
        contratRestController.removeContrat(1);
        verify(contratService).removeContrat(1);
    }

    @Test
    void testAssignContratToEtudiant() {
        Contrat contrat = new Contrat();
        when(contratService.affectContratToEtudiant(1, "Ali", "Ben Salah")).thenReturn(contrat);

        Contrat result = contratRestController.assignContratToEtudiant(1, "Ali", "Ben Salah");

        assertNotNull(result);
        verify(contratService).affectContratToEtudiant(1, "Ali", "Ben Salah");
    }

    @Test
    void testGetNbContratsValides() throws Exception {
        Date start = new SimpleDateFormat("yyyy-MM-dd").parse("2023-01-01");
        Date end = new SimpleDateFormat("yyyy-MM-dd").parse("2023-12-31");
        when(contratService.nbContratsValides(start, end)).thenReturn(5);

        Integer count = contratRestController.getnbContratsValides(start, end);

        assertEquals(5, count);
        verify(contratService).nbContratsValides(start, end);
    }

    @Test
    void testCalculChiffreAffaireEntreDeuxDates() throws Exception {
        Date start = new SimpleDateFormat("yyyy-MM-dd").parse("2023-01-01");
        Date end = new SimpleDateFormat("yyyy-MM-dd").parse("2023-12-31");
        when(contratService.getChiffreAffaireEntreDeuxDates(start, end)).thenReturn(1234.5f);

        float result = contratRestController.calculChiffreAffaireEntreDeuxDates(start, end);

        assertEquals(1234.5f, result);
        verify(contratService).getChiffreAffaireEntreDeuxDates(start, end);
    }

    @Test
    void testMajStatusContrat() {
        contratRestController.majStatusContrat();
        verify(contratService).retrieveAndUpdateStatusContrat();
    }
}
