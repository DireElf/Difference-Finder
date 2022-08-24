package hexlet.code;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import static hexlet.code.Utils.getFileContent;
import static hexlet.code.Utils.getPathAsString;

public class UnitTests {

    @Test
    void withDefaultFormat() {
        String expected = getFileContent("expected_stylish_formatted.txt");
        assertThat(Differ.generate(getPathAsString("valid_file1.json"),
                getPathAsString("valid_file2.json"))).isEqualTo(expected);
    }

    @Test
    void withYmlExtension() {
        String expected = getFileContent("expected_stylish_formatted.txt");
        assertThat(Differ.generate(getPathAsString("valid_file1.yml"),
                getPathAsString("valid_file2.yml"))).isEqualTo(expected);
    }

    @Test
    void withStylishFormat() {
        String expected = getFileContent("expected_stylish_formatted.txt");
        assertThat(Differ.generate(getPathAsString("valid_file1.json"),
                getPathAsString("valid_file2.json"), "stylish")).isEqualTo(expected);
    }

    @Test
    void withPlainFormat() {
        String expected = getFileContent("expected_plain_formatted.txt");
        assertThat(Differ.generate(getPathAsString("valid_file_with_complex_values1.yml"),
                getPathAsString("valid_file_with_complex_values2.yml"), "plain")).isEqualTo(expected);
    }

    @Test
    void withJsonFormat() {
        String expected = getFileContent("expected_json_formatted.txt");
        assertThat(Differ.generate(getPathAsString("valid_file1.json"),
                getPathAsString("valid_file2.json"), "json")).isEqualTo(expected);
    }

    @Test
    void whenValuesContainObjects() {
        String expected = getFileContent("expected_with_complex_values.txt");
        assertThat(Differ.generate(getPathAsString("valid_file_with_complex_values1.yml"),
                getPathAsString("valid_file_with_complex_values2.yml"))).isEqualTo(expected);
    }

    @Test
    void withYamlExtension() {
        String expected = getFileContent("expected_stylish_formatted.txt");
        assertThat(Differ.generate(getPathAsString("valid_file_yaml_extension1.yaml"),
                getPathAsString("valid_file_yaml_extension2.yaml"))).isEqualTo(expected);
    }

    @Test
    void withWrongExtension() {
        assertThatThrownBy(() -> Differ.generate(getPathAsString("expected_stylish_formatted.txt"),
                getPathAsString("valid_file2.json")))
                .hasMessage("File \"expected_stylish_formatted.txt\" has wrong format."
                        + " Available extensions are: .json, .yml, .yaml");
    }

    @Test
    void whenFileDoesNotExist() {
        assertThatThrownBy(() -> Differ.generate(getPathAsString("nonexistent_file.json"),
                getPathAsString("valid_file2.json")))
                .hasMessage("File \"nonexistent_file.json\" not found");
    }

    @Test
    void whenFileIsEmpty() {
        assertThatThrownBy(() -> Differ.generate(getPathAsString("empty.json"),
                getPathAsString("valid_file2.json")))
                .hasMessage("File \"empty.json\" is empty");
    }

    @Test
    void whenJsonFileHasNoContent() {
        assertThatThrownBy(() -> Differ.generate(getPathAsString("valid_file_without_content.json"),
                getPathAsString("valid_file2.json")))
                .hasMessage("File \"valid_file_without_content.json\" has no content");
    }

    @Test
    void whenYmlFileHasNoContent() {
        assertThatThrownBy(() -> Differ.generate(getPathAsString("valid_file_without_content.yml"),
                getPathAsString("valid_file2.json")))
                .hasMessage("File \"valid_file_without_content.yml\" has no content");
    }
}
