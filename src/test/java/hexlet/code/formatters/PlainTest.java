package hexlet.code.formatters;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

import hexlet.code.Differ;
import static hexlet.code.Utils.getFileContent;
import static hexlet.code.Utils.getPathAsString;


class PlainTest {
    @Test
    void applyPlain() {
        String expected = getFileContent("result6.txt");
        assertThat(Differ.generate(getPathAsString("nested1.yaml"),
                getPathAsString("nested2.yaml"), "plain")).isEqualTo(expected);
    }
}
