package tn.esprit.spring.kaddem.Enteties;

import org.junit.jupiter.api.Test;
import tn.esprit.spring.kaddem.entities.Specialite;

import static org.assertj.core.api.Assertions.assertThat;

class SpecialiteTest {

    @Test
    void testEnumValues() {
        Specialite[] expected = {Specialite.IA, Specialite.RESEAUX, Specialite.CLOUD, Specialite.SECURITE};

        assertThat(Specialite.values()).containsExactly(expected);
    }

    @Test
    void testValueOf() {
        assertThat(Specialite.valueOf("IA")).isEqualTo(Specialite.IA);
        assertThat(Specialite.valueOf("RESEAUX")).isEqualTo(Specialite.RESEAUX);
        assertThat(Specialite.valueOf("CLOUD")).isEqualTo(Specialite.CLOUD);
        assertThat(Specialite.valueOf("SECURITE")).isEqualTo(Specialite.SECURITE);
    }
}
