package tn.esprit.spring.kaddem.services;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.*;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.spring.kaddem.entities.Departement;
import tn.esprit.spring.kaddem.repositories.DepartementRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@SpringBootTest
class DepartementServiceImplTest {


    @Mock
    private DepartementRepository departementRepository;

    @InjectMocks
    private DepartementServiceImpl departementService;

    @Test
        // Vérifie que la méthode retrieveAllDepartements retourne tous les départements disponibles.

    void shouldRetrieveAllDepartements() {
        Departement dep1 = new Departement(1, "aouina");
        Departement dep2 = new Departement(2, "nabeul");

        when(departementRepository.findAll()).thenReturn(List.of(dep1, dep2));

        List<Departement> result = departementService.retrieveAllDepartements();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("aouina", result.get(0).getNomDepart());
        assertEquals("nabeul", result.get(1).getNomDepart());
    }

    @Test
        // Vérifie que la méthode retrieveDepartement retourne un département spécifique en fonction de son ID.

    void shouldReturnDepartementById() {
        Departement departement = new Departement(1, "aouina");
        when(departementRepository.findById(1)).thenReturn(Optional.of(departement));

        Departement result = departementService.retrieveDepartement(1);

        assertNotNull(result);
        assertEquals(1, result.getIdDepart());
        assertEquals("aouina", result.getNomDepart());
    }

    @Test
        // Vérifie que la méthode addDepartement ajoute correctement un nouveau département.

    void shouldAddDepartement() {
        Departement departement = new Departement("aouina");
        when(departementRepository.save(departement)).thenReturn(new Departement(1, "aouina"));

        Departement result = departementService.addDepartement(departement);

        assertNotNull(result);
        assertEquals(1, result.getIdDepart());
        assertEquals("aouina", result.getNomDepart());
    }

    @Test
        // Vérifie que la méthode updateDepartement met à jour correctement un département existant.

    void shouldUpdateDepartement() {
        Departement departement = new Departement(1, "aouina");
        when(departementRepository.save(departement)).thenReturn(departement);

        Departement result = departementService.updateDepartement(departement);

        assertNotNull(result);
        assertEquals(1, result.getIdDepart());
        assertEquals("aouina", result.getNomDepart());
    }

    @Test
        // Vérifie que la méthode deleteDepartement supprime correctement un département existant.

    void shouldDeleteDepartement() {
        Departement departement = new Departement(1, "aouina");
        when(departementRepository.findById(1)).thenReturn(Optional.of(departement));
        doNothing().when(departementRepository).delete(departement);

        departementService.deleteDepartement(1);

        verify(departementRepository, times(1)).delete(departement);
    }

    @Test
        // Vérifie que la méthode retrieveDepartement lève une exception lorsque l'ID du département n'existe pas.

    void shouldThrowExceptionWhenDepartementNotFound() {
        when(departementRepository.findById(1)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> {
            departementService.retrieveDepartement(1);
        });

        assertEquals("No value present", exception.getMessage());
    }

}