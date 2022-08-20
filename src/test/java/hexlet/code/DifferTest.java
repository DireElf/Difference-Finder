package hexlet.code;

import org.junit.jupiter.api.Test;

import java.util.TreeMap;

import static hexlet.code.Utils.getFileContent;
import static hexlet.code.Utils.getPathAsString;
import static org.assertj.core.api.Assertions.assertThat;

class DifferTest {
    @Test
    void generateWithDefaultFormat() {
        String expected = getFileContent("result1.txt");
        assertThat(Differ.generate(getPathAsString("file1.json"),
                        getPathAsString("file2.json"), "stylish")).isEqualTo(expected);
    }

    @Test
    void generateWithTwoParameters() {
        String expected = getFileContent("result1.txt");
        assertThat(Differ.generate(getPathAsString("file1.json"),
                getPathAsString("file2.json"))).isEqualTo(expected);
    }

    @Test
    void generateWhenValuesContainObjects() {
        String expected = getFileContent("result5.txt");
        assertThat(Differ.generate(getPathAsString("nested1.yaml"),
                getPathAsString("nested2.yaml"))).isEqualTo(expected);
    }

    @Test
    void getDifferences() {
        TreeMap<String, String> expected = new TreeMap<>();
        expected.put("follow", "removed");
        expected.put("host", "unchanged");
        expected.put("proxy", "removed");
        expected.put("timeout", "changed");
        expected.put("verbose", "added");
        TreeMap<String, Object> map1 = Parser.parseMap(getPathAsString("file1.json"));
        TreeMap<String, Object> map2 = Parser.parseMap(getPathAsString("file2.json"));
        assertThat(Differ.getDifferences(map1, map2)).isEqualTo(expected);
    }

    @Test
    void getDifferencesWhen1stMapIsEmpty() {
        TreeMap<String, String> expected = new TreeMap<>();
        expected.put("follow", "added");
        expected.put("host", "added");
        expected.put("proxy", "added");
        expected.put("timeout", "added");
        TreeMap<String, Object> map1 = Parser.parseMap(getPathAsString("empty.json"));
        TreeMap<String, Object> map2 = Parser.parseMap(getPathAsString("file1.json"));
        assertThat(Differ.getDifferences(map1, map2)).isEqualTo(expected);
    }

    @Test
    void getDifferencesWhen2ndMapIsEmpty() {
        TreeMap<String, String> expected = new TreeMap<>();
        expected.put("follow", "removed");
        expected.put("host", "removed");
        expected.put("proxy", "removed");
        expected.put("timeout", "removed");
        TreeMap<String, Object> map1 = Parser.parseMap(getPathAsString("file1.json"));
        TreeMap<String, Object> map2 = Parser.parseMap(getPathAsString("empty.json"));
        assertThat(Differ.getDifferences(map1, map2)).isEqualTo(expected);
    }

    @Test
    void getDifferencesWhenBothMapsAreEmpty() {
        TreeMap<String, Object> map1 = Parser.parseMap(getPathAsString("empty.yaml"));
        TreeMap<String, Object> map2 = Parser.parseMap(getPathAsString("empty.json"));
        assertThat(Differ.getDifferences(map1, map2)).isEqualTo(new TreeMap<>());
    }
}
