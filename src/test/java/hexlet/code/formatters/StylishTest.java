package hexlet.code.formatters;

import hexlet.code.Differ;
import org.junit.jupiter.api.Test;

import static hexlet.code.Utils.getFileContent;
import static hexlet.code.Utils.getPathAsString;
import static org.assertj.core.api.Assertions.assertThat;

class StylishTest {
    @Test
    void applyStylish() {
        String expected = getFileContent("result1.txt");
        assertThat(Differ.generate(getPathAsString("file1.json"),
                getPathAsString("file2.json"), "stylish")).isEqualTo(expected);
    }
}
