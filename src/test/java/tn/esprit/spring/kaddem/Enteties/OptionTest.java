package tn.esprit.spring.kaddem.Enteties;

import org.junit.jupiter.api.Test;
import tn.esprit.spring.kaddem.entities.Option;

import static org.assertj.core.api.Assertions.assertThat;

class OptionTest {

    @Test
    void testEnumValues() {
        Option[] expected = {Option.GAMIX, Option.SE, Option.SIM, Option.NIDS};

        assertThat(Option.values()).containsExactly(expected);
    }

    @Test
    void testValueOf() {
        assertThat(Option.valueOf("GAMIX")).isEqualTo(Option.GAMIX);
        assertThat(Option.valueOf("SE")).isEqualTo(Option.SE);
        assertThat(Option.valueOf("SIM")).isEqualTo(Option.SIM);
        assertThat(Option.valueOf("NIDS")).isEqualTo(Option.NIDS);
    }
}
