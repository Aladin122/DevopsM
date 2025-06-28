package tn.esprit.spring.kaddem.Enteties;

import org.junit.jupiter.api.Test;
import tn.esprit.spring.kaddem.entities.Niveau;

import static org.assertj.core.api.Assertions.assertThat;

class NiveauTest {

    @Test
    void testEnumValues() {
        Niveau[] niveaux = Niveau.values();

        assertThat(niveaux).containsExactly(Niveau.JUNIOR, Niveau.SENIOR, Niveau.EXPERT);
    }

    @Test
    void testValueOf() {
        assertThat(Niveau.valueOf("JUNIOR")).isEqualTo(Niveau.JUNIOR);
        assertThat(Niveau.valueOf("SENIOR")).isEqualTo(Niveau.SENIOR);
        assertThat(Niveau.valueOf("EXPERT")).isEqualTo(Niveau.EXPERT);
    }
}
