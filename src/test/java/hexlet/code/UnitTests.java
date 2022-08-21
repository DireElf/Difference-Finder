package hexlet.code;


import org.junit.jupiter.api.Test;
import picocli.CommandLine;
import java.util.Map;
import java.util.TreeMap;

import hexlet.code.formatters.JSON;
import static hexlet.code.Utils.getFileContent;
import static hexlet.code.Utils.getPathAsString;
import static org.assertj.core.api.Assertions.assertThat;

public class UnitTests {

    @Test
    void testApp() {
        String[] args = {};
        assertThat(new CommandLine(new App()).execute(args)).isEqualTo(0);
    }

    @Test
    void testParserParseMap() {
        TreeMap<String, Object> expected = new TreeMap<>();
        final int sampleNumber = 50;
        expected.put("host", "hexlet.io");
        expected.put("timeout", sampleNumber);
        expected.put("proxy", "123.234.53.22");
        expected.put("follow", false);
        String path = getPathAsString("file1.json");
        assertThat(Parser.parseMap(path)).isEqualTo(expected);
        String pathToYAML = getPathAsString("file1.yaml");
        assertThat(Parser.parseMap(pathToYAML)).isEqualTo(expected);
        String pathToYML = getPathAsString("file1.yml");
        assertThat(Parser.parseMap(pathToYML)).isEqualTo(expected);
        String pathToEmptyJSON = getPathAsString("empty.json");
        assertThat(Parser.parseMap(pathToEmptyJSON)).isEqualTo(new TreeMap<>());
        String pathToEmptyYAML = getPathAsString("empty.yaml");
        assertThat(Parser.parseMap(pathToEmptyYAML)).isEqualTo(new TreeMap<>());
    }

    @Test
    void testDifferGenerateWithTwoParams() {
        String expected = getFileContent("result1.txt");
        assertThat(Differ.generate(getPathAsString("file1.json"),
                getPathAsString("file2.json"))).isEqualTo(expected);
    }

    @Test
    void testDifferGenerateWithThreeParams() {
        String expected = getFileContent("result1.txt");
        assertThat(Differ.generate(getPathAsString("file1.json"),
                getPathAsString("file2.json"), "stylish")).isEqualTo(expected);
    }

    @Test
    void testWhenValuesContainObjects() {
        String expected = getFileContent("result5.txt");
        assertThat(Differ.generate(getPathAsString("nested1.yaml"),
                getPathAsString("nested2.yaml"))).isEqualTo(expected);
    }

    @Test
    void testDifferGetDifferences() {
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
    void testDifferGetDifferencesWhen1stMapIsEmpty() {
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
    void testDifferGetDifferencesWhen2ndMapIsEmpty() {
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
    void testDifferGetDifferencesWhenBothMapsAreEmpty() {
        TreeMap<String, Object> map1 = Parser.parseMap(getPathAsString("empty.yaml"));
        TreeMap<String, Object> map2 = Parser.parseMap(getPathAsString("empty.json"));
        assertThat(Differ.getDifferences(map1, map2)).isEqualTo(new TreeMap<>());
    }

    @Test
    void testApplyJSON() {
        String expected = "{" + "\"follow\":\"removed\","
                + "\"host\":\"unchanged\","
                + "\"proxy\":\"removed\","
                + "\"timeout\":\"changed\","
                + "\"verbose\":\"added\"" + "}";
        TreeMap<String, Object> map1 = Parser.parseMap(getPathAsString("file1.json"));
        TreeMap<String, Object> map2 = Parser.parseMap(getPathAsString("file2.json"));
        Map<String, String> differences = Differ.getDifferences(map1, map2);
        assertThat(JSON.applyJSON(differences)).isEqualTo(expected);
    }

    @Test
    void testApplyJSONtoEmptyMap() {
        assertThat(JSON.applyJSON(new TreeMap<>())).isEqualTo("{}");
    }

    @Test
    void testApplyPlain() {
        String expected = getFileContent("result6.txt");
        assertThat(Differ.generate(getPathAsString("nested1.yaml"),
                getPathAsString("nested2.yaml"), "plain")).isEqualTo(expected);
    }

    @Test
    void applyStylish() {
        String expected = getFileContent("result1.txt");
        assertThat(Differ.generate(getPathAsString("file1.json"),
                getPathAsString("file2.json"), "stylish")).isEqualTo(expected);
    }
}
