package tn.esprit.spring.kaddem.Enteties;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tn.esprit.spring.kaddem.entities.DetailEquipe;
import tn.esprit.spring.kaddem.entities.Equipe;
import tn.esprit.spring.kaddem.entities.Etudiant;
import tn.esprit.spring.kaddem.entities.Niveau;

import java.util.HashSet;

import static org.assertj.core.api.Assertions.assertThat;

class EquipeTest {

    private Equipe equipe;

    @BeforeEach
    void setUp() {
        equipe = new Equipe();
    }

    @Test
    void testNoArgsConstructor() {
        assertThat(equipe).isNotNull();
    }

    @Test
    void testSettersAndGetters() {
        Integer id = 10;
        String nom = "Equipe DevOps";
        Niveau niveau = Niveau.EXPERT;
        DetailEquipe detailEquipe = new DetailEquipe();
        var etudiants = new HashSet<Etudiant>();
        etudiants.add(new Etudiant());

        equipe.setIdEquipe(id);
        equipe.setNomEquipe(nom);
        equipe.setNiveau(niveau);
        equipe.setDetailEquipe(detailEquipe);
        equipe.setEtudiants(etudiants);

        assertThat(equipe.getIdEquipe()).isEqualTo(id);
        assertThat(equipe.getNomEquipe()).isEqualTo(nom);
        assertThat(equipe.getNiveau()).isEqualTo(niveau);
        assertThat(equipe.getDetailEquipe()).isEqualTo(detailEquipe);
        assertThat(equipe.getEtudiants()).hasSize(1);
    }

    @Test
    void testConstructorWithNameAndNiveau() {
        var e = new Equipe("Equipe AI", Niveau.JUNIOR);

        assertThat(e.getNomEquipe()).isEqualTo("Equipe AI");
        assertThat(e.getNiveau()).isEqualTo(Niveau.JUNIOR);
    }

    @Test
    void testConstructorWithIdNameAndNiveau() {
        var e = new Equipe(5, "Equipe Cloud", Niveau.SENIOR);

        assertThat(e.getIdEquipe()).isEqualTo(5);
        assertThat(e.getNomEquipe()).isEqualTo("Equipe Cloud");
        assertThat(e.getNiveau()).isEqualTo(Niveau.SENIOR);
    }

    @Test
    void testConstructorWithAllFieldsWithoutId() {
        var etudiants = new HashSet<Etudiant>();
        etudiants.add(new Etudiant());
        var detailEquipe = new DetailEquipe();
        var e = new Equipe("Equipe Data", Niveau.EXPERT, etudiants, detailEquipe);

        assertThat(e.getNomEquipe()).isEqualTo("Equipe Data");
        assertThat(e.getNiveau()).isEqualTo(Niveau.EXPERT);
        assertThat(e.getEtudiants()).hasSize(1);
        assertThat(e.getDetailEquipe()).isEqualTo(detailEquipe);
    }

    @Test
    void testConstructorWithAllFieldsWithId() {
        var etudiants = new HashSet<Etudiant>();
        var detailEquipe = new DetailEquipe();
        var e = new Equipe(8, "Equipe Dev", Niveau.SENIOR, etudiants, detailEquipe);

        assertThat(e.getIdEquipe()).isEqualTo(8);
        assertThat(e.getNomEquipe()).isEqualTo("Equipe Dev");
        assertThat(e.getNiveau()).isEqualTo(Niveau.SENIOR);
        assertThat(e.getEtudiants()).isSameAs(etudiants);
        assertThat(e.getDetailEquipe()).isEqualTo(detailEquipe);
    }
}
