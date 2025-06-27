package tn.esprit.spring.kaddem.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import tn.esprit.spring.kaddem.entities.Equipe;
import tn.esprit.spring.kaddem.entities.Niveau;
import tn.esprit.spring.kaddem.repositories.EquipeRepository;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EquipeServiceTest {

    @InjectMocks
    private EquipeServiceImpl equipeService;

    @Mock
    private EquipeRepository equipeRepository;

    private Equipe equipe;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        equipe = new Equipe();
        equipe.setIdEquipe(1);
        equipe.setNomEquipe("Equipe Test");
        equipe.setNiveau(Niveau.JUNIOR);
    }

    @Test
    void testRetrieveAllEquipes() {
        List<Equipe> equipes = List.of(equipe);
        when(equipeRepository.findAll()).thenReturn(equipes);

        List<Equipe> result = equipeService.retrieveAllEquipes();

        assertEquals(1, result.size());
        assertEquals("Equipe Test", result.get(0).getNomEquipe());
        verify(equipeRepository, times(1)).findAll();
    }

    @Test
    void testAddEquipe() {
        when(equipeRepository.save(equipe)).thenReturn(equipe);

        Equipe result = equipeService.addEquipe(equipe);

        assertNotNull(result);
        assertEquals("Equipe Test", result.getNomEquipe());
        verify(equipeRepository, times(1)).save(equipe);
    }

    @Test
    void testDeleteEquipe() {
        when(equipeRepository.findById(1)).thenReturn(Optional.of(equipe));
        doNothing().when(equipeRepository).delete(equipe);

        equipeService.deleteEquipe(1);

        verify(equipeRepository, times(1)).delete(equipe);
    }

    @Test
    void testUpdateEquipe() {
        equipe.setNomEquipe("Updated Team");
        when(equipeRepository.save(equipe)).thenReturn(equipe);

        Equipe result = equipeService.updateEquipe(equipe);

        assertEquals("Updated Team", result.getNomEquipe());
        verify(equipeRepository, times(1)).save(equipe);
    }

    @Test
    void testRetrieveEquipe() {
        when(equipeRepository.findById(1)).thenReturn(Optional.of(equipe));

        Equipe result = equipeService.retrieveEquipe(1);

        assertNotNull(result);
        assertEquals(1, result.getIdEquipe());
        verify(equipeRepository, times(1)).findById(1);
    }
}
