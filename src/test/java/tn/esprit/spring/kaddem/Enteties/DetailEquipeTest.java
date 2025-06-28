package tn.esprit.spring.kaddem.Enteties;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tn.esprit.spring.kaddem.entities.DetailEquipe;
import tn.esprit.spring.kaddem.entities.Equipe;

import static org.assertj.core.api.Assertions.assertThat;

class DetailEquipeTest {

    private DetailEquipe detail;

    @BeforeEach
    void setUp() {
        detail = new DetailEquipe();
    }

    @Test
    void testNoArgsConstructor() {
        assertThat(detail).isNotNull();
    }

    @Test
    void testSettersAndGetters() {
        Integer id = 1;
        Integer salle = 102;
        String thematique = "IA avancée";
        Equipe equipe = new Equipe();

        detail.setIdDetailEquipe(id);
        detail.setSalle(salle);
        detail.setThematique(thematique);
        detail.setEquipe(equipe);

        assertThat(detail.getIdDetailEquipe()).isEqualTo(id);
        assertThat(detail.getSalle()).isEqualTo(salle);
        assertThat(detail.getThematique()).isEqualTo(thematique);
        assertThat(detail.getEquipe()).isEqualTo(equipe);
    }

    @Test
    void testConstructorWithSalleAndThematique() {
        var d = new DetailEquipe(301, "Big Data");

        assertThat(d.getSalle()).isEqualTo(301);
        assertThat(d.getThematique()).isEqualTo("Big Data");
    }

    @Test
    void testConstructorWithIdSalleThematique() {
        var d = new DetailEquipe(2, 402, "Cloud");

        assertThat(d.getIdDetailEquipe()).isEqualTo(2);
        assertThat(d.getSalle()).isEqualTo(402);
        assertThat(d.getThematique()).isEqualTo("Cloud");
    }
}
