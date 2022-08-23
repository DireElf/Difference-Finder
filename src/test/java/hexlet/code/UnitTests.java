package hexlet.code;

import org.junit.jupiter.api.Test;

import static hexlet.code.Utils.getFileContent;
import static hexlet.code.Utils.getPathAsString;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class UnitTests {

//    @BeforeAll
//    static void writer() throws IOException {
//        String toWrite = Differ.generate(getPathAsString("valid_file1.json"),
//                getPathAsString("valid_file2.json"), "json");
//        Files.createFile(Paths.get(getPathAsString("expected_json_formatted.txt")));
//        Files.writeString(Paths.get(getPathAsString("expected_json_formatted.txt")), toWrite);
//    }

    @Test
    void formatDefault() {
        String expected = getFileContent("expected_stylish_formatted.txt");
        assertThat(Differ.generate(getPathAsString("valid_file1.json"),
                getPathAsString("valid_file2.json"))).isEqualTo(expected);
    }

    @Test
    void formatStylish() {
        String expected = getFileContent("expected_stylish_formatted.txt");
        assertThat(Differ.generate(getPathAsString("valid_file1.json"),
                getPathAsString("valid_file2.json"), "stylish")).isEqualTo(expected);
    }

    @Test
    void formatPlain() {
        String expected = getFileContent("expected_plain_formatted.txt");
        assertThat(Differ.generate(getPathAsString("valid_file_with_complex_values1.yml"),
                getPathAsString("valid_file_with_complex_values2.yml"), "plain")).isEqualTo(expected);
    }

    @Test
    void formatJson() {
        String expected = getFileContent("expected_json_formatted.txt");
        assertThat(Differ.generate(getPathAsString("valid_file1.json"),
                getPathAsString("valid_file2.json"), "json")).isEqualTo(expected);
    }

    @Test
    void valuesContainObjects() {
        String expected = getFileContent("expected_with_complex_values.txt");
        assertThat(Differ.generate(getPathAsString("valid_file_with_complex_values1.yml"),
                getPathAsString("valid_file_with_complex_values2.yml"))).isEqualTo(expected);
    }

    @Test
    void filesHaveYamlExtension() {
        String expected = getFileContent("expected_stylish_formatted.txt");
        assertThat(Differ.generate(getPathAsString("valid_file_yaml_extension1.yaml"),
                getPathAsString("valid_file_yaml_extension2.yaml"))).isEqualTo(expected);
    }

    @Test
    void firstFileWithWrongExtension() {
        assertThatThrownBy(() -> Differ.generate(getPathAsString("expected_stylish_formatted.txt"),
                getPathAsString("valid_file2.json")))
                .isInstanceOf(Error.class)
                .hasMessage("Invalid file format. Available extensions are: .json, .yml, .yaml");
    }

    @Test
    void secondFileWithWrongExtension() {
        assertThatThrownBy(() -> Differ.generate(getPathAsString("valid_file1.json"),
                getPathAsString("expected_stylish_formatted.txt")))
                .isInstanceOf(Error.class)
                .hasMessage("Invalid file format. Available extensions are: .json, .yml, .yaml");
    }

    @Test
    void firstFileUnavailable() {
        assertThatThrownBy(() -> Differ.generate(getPathAsString("unavailable.json"),
                getPathAsString("valid_file2.json")))
                .isInstanceOf(Error.class)
                .hasMessage("File \"unavailable.json\" not found");
    }
}
