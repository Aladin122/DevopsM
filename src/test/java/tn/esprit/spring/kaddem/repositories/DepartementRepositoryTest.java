package tn.esprit.spring.kaddem.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import tn.esprit.spring.kaddem.entities.Departement;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)

class DepartementRepositoryTest {
    @Autowired
   private DepartementRepository departementRepository;


    @Test
     void shouldGellAllDepartments(){
        //arange
       /* List<Departement> departements= (List<Departement>) departementRepository.findAll();

        //act
        //assert
        assertEquals(3,departements.size());*/
    }

    @Test
    void shouldGetDepartementId(){
      //  Departement dept = departementRepository.findById(1).get();
     //   assertNotNull(dept);
    }

    @Test
    void shouldSaveDepartement(){
        Departement dep=new Departement();
        dep.setIdDepart(1);
        dep.setNomDepart("soukra");
      Departement savedDep=  departementRepository.save(dep);
        //dep.setEtudiants();
        assertNotNull(savedDep.getIdDepart());
        assertNotNull(savedDep.getNomDepart());

    }
  void shouldUpdateDepartement(){
        Departement dep=departementRepository.findById(1).get();
        dep.setNomDepart("Aouina");

        Departement savedDep=departementRepository.save(dep);
        assertEquals("Aouina",savedDep.getNomDepart());


  }


  @Test
    void shouldDeleteDepartement(){
   //     departementRepository.deleteById(1);
     // assertFalse(departementRepository.findById(1).isPresent());
  }
}