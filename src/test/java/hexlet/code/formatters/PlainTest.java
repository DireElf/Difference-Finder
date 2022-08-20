package hexlet.code.formatters;

import hexlet.code.Differ;
import org.junit.jupiter.api.Test;


import static hexlet.code.Utils.getFileContent;
import static hexlet.code.Utils.getPathAsString;
import static org.assertj.core.api.Assertions.assertThat;

class PlainTest {

    @Test
    void applyPlain() {
        String expected = getFileContent("result6.txt");
        assertThat(Differ.generate(getPathAsString("nested1.yaml"),
                getPathAsString("nested2.yaml"), "plain")).isEqualTo(expected);
    }
}
