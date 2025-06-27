package tn.esprit.spring.kaddem.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import tn.esprit.spring.kaddem.entities.*;

import tn.esprit.spring.kaddem.repositories.EquipeRepository;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EquipeServiceImplTest {

    @InjectMocks
    private EquipeServiceImpl equipeService;

    @Mock
    private EquipeRepository equipeRepository;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRetrieveAllEquipes() {
        List<Equipe> equipes = Arrays.asList(new Equipe(), new Equipe());
        when(equipeRepository.findAll()).thenReturn(equipes);

        List<Equipe> result = equipeService.retrieveAllEquipes();

        assertEquals(2, result.size());
        verify(equipeRepository).findAll();
    }

    @Test
    void testAddEquipe() {
        Equipe equipe = new Equipe();
        when(equipeRepository.save(equipe)).thenReturn(equipe);

        Equipe result = equipeService.addEquipe(equipe);

        assertEquals(equipe, result);
        verify(equipeRepository).save(equipe);
    }

    @Test
    void testDeleteEquipe() {
        Equipe equipe = new Equipe();
        equipe.setIdEquipe(1);
        when(equipeRepository.findById(1)).thenReturn(Optional.of(equipe));

        equipeService.deleteEquipe(1);

        verify(equipeRepository).delete(equipe);
    }

    @Test
    void testRetrieveEquipe() {
        Equipe equipe = new Equipe();
        equipe.setIdEquipe(1);
        when(equipeRepository.findById(1)).thenReturn(Optional.of(equipe));

        Equipe result = equipeService.retrieveEquipe(1);

        assertEquals(1, result.getIdEquipe());
    }

    @Test
    void testUpdateEquipe() {
        Equipe equipe = new Equipe();
        when(equipeRepository.save(equipe)).thenReturn(equipe);

        Equipe result = equipeService.updateEquipe(equipe);

        assertEquals(equipe, result);
        verify(equipeRepository).save(equipe);
    }

}
