package hexlet.code;

import hexlet.code.formatters.JSON;
import hexlet.code.formatters.Plain;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.TreeMap;

import static org.assertj.core.api.Assertions.assertThat;

public class AppTest {
    static final String PATH_TO_JSON_FILE_1 = "./src/test/resources/file1.json";
    static final String PATH_TO_JSON_FILE_2 = "./src/test/resources/file2.json";
    static final String PATH_TO_YAML_FILE_1 = "./src/test/resources/file1.yaml";
    static final String PATH_TO_YAML_FILE_2 = "./src/test/resources/file2.yaml";
    static final String PATH_TO_YML_FILE = "./src/test/resources/file1.yml";
    static final String PATH_TO_EMPTY_JSON_FILE = "./src/test/resources/empty.json";
    static final String PATH_TO_EMPTY_YAML_FILE = "./src/test/resources/empty.yaml";
    static final String PATH_TO_TEST_RESULT1 = "./src/test/resources/result1.txt";
    static final String PATH_TO_TEST_RESULT2 = "./src/test/resources/result2.txt";
    static final String PATH_TO_TEST_RESULT3 = "./src/test/resources/result3.txt";
    static final String PATH_TO_TEST_RESULT4 = "./src/test/resources/result4.txt";
    static final String PATH_TO_YAML_WITH_NESTED1 = "./src/test/resources/nested1.yaml";
    static final String PATH_TO_YAML_WITH_NESTED2 = "./src/test/resources/nested2.yaml";
    static final String PATH_TO_NESTED_TEST_RESULT = "./src/test/resources/result5.txt";
    static final String PATH_TO_PLAIN_TEST_RESULT = "./src/test/resources/result6.txt";


    private static String getFileContent(String path) throws IOException {
        return Files.readString(Paths.get(path));
    }

    @Test
    void testDiffer() throws IOException {
        assertThat(Differ.generate(PATH_TO_JSON_FILE_1, PATH_TO_JSON_FILE_2, "stylish"))
                .isEqualTo(getFileContent(PATH_TO_TEST_RESULT1));
        assertThat(Differ.generate(PATH_TO_YAML_FILE_1, PATH_TO_YAML_FILE_2, "stylish"))
                .isEqualTo(getFileContent(PATH_TO_TEST_RESULT1));
        assertThat(Differ.generate(PATH_TO_YML_FILE, PATH_TO_YAML_FILE_2, "stylish"))
                .isEqualTo(getFileContent(PATH_TO_TEST_RESULT1));
        assertThat(Differ.generate(PATH_TO_JSON_FILE_1, PATH_TO_EMPTY_JSON_FILE, "stylish"))
                .isEqualTo(getFileContent(PATH_TO_TEST_RESULT2));
        assertThat(Differ.generate(PATH_TO_EMPTY_JSON_FILE, PATH_TO_JSON_FILE_1, "stylish"))
                .isEqualTo(getFileContent(PATH_TO_TEST_RESULT3));
        assertThat(Differ.generate(PATH_TO_EMPTY_JSON_FILE, PATH_TO_EMPTY_JSON_FILE, "stylish"))
                .isEqualTo(getFileContent(PATH_TO_TEST_RESULT4));
        assertThat(Differ.generate(PATH_TO_YAML_WITH_NESTED1, PATH_TO_YAML_WITH_NESTED2, "stylish"))
                .isEqualTo(getFileContent(PATH_TO_NESTED_TEST_RESULT));
    }

    @Test
    void testParser() {
        TreeMap<String, Object> validResult = new TreeMap<>();
        final int sampleNumber = 50;
        validResult.put("host", "hexlet.io");
        validResult.put("timeout", sampleNumber);
        validResult.put("proxy", "123.234.53.22");
        validResult.put("follow", false);
        assertThat(Parser.parseMap(PATH_TO_JSON_FILE_1)).isEqualTo(validResult);
        assertThat(Parser.parseMap(PATH_TO_YAML_FILE_1)).isEqualTo(validResult);
        assertThat(Parser.parseMap(PATH_TO_YML_FILE)).isEqualTo(validResult);
        assertThat(Parser.parseMap(PATH_TO_EMPTY_JSON_FILE)).isEqualTo(new TreeMap<>());
        assertThat(Parser.parseMap(PATH_TO_EMPTY_YAML_FILE)).isEqualTo(new TreeMap<>());
    }

    @Test
    void testGetDiffer() {
        TreeMap<String, String> expected = new TreeMap<>();
        expected.put("follow", "removed");
        expected.put("host", "unchanged");
        expected.put("proxy", "removed");
        expected.put("timeout", "changed");
        expected.put("verbose", "added");
        TreeMap<String, Object> map1 = Parser.parseMap(PATH_TO_JSON_FILE_1);
        TreeMap<String, Object> map2 = Parser.parseMap(PATH_TO_JSON_FILE_2);
        assertThat(Differ.getDifferences(map1, map2)).isEqualTo(expected);
    }

    @Test
    void testApplyPlain() {
        String expected = """
                Property 'chars2' was updated. From [complex value] to false
                Property 'checked' was updated. From false to true
                Property 'default' was updated. From null to [complex value]
                Property 'id' was updated. From 45 to null
                Property 'key1' was removed
                Property 'key2' was added with value: 'value2'
                Property 'numbers2' was updated. From [complex value] to [complex value]
                Property 'numbers3' was removed
                Property 'numbers4' was added with value: [complex value]
                Property 'obj1' was added with value: [complex value]
                Property 'setting1' was updated. From 'Some value' to 'Another value'
                Property 'setting2' was updated. From 200 to 300
                Property 'setting3' was updated. From true to 'none'""";
        TreeMap<String, Object> map1 = Parser.parseMap(PATH_TO_YAML_WITH_NESTED1);
        TreeMap<String, Object> map2 = Parser.parseMap(PATH_TO_YAML_WITH_NESTED2);
        Map<String, String> diff = Differ.getDifferences(map1, map2);
        assertThat(Plain.applyPlain(map1, map2, diff)).isEqualTo(expected);
    }

    @Test
    void testApplyJson() {
        String expected = "{" + "\"follow\":\"removed\","
                + "\"host\":\"unchanged\","
                + "\"proxy\":\"removed\","
                + "\"timeout\":\"changed\","
                + "\"verbose\":\"added\"" + "}";
        TreeMap<String, Object> map1 = Parser.parseMap(PATH_TO_JSON_FILE_1);
        TreeMap<String, Object> map2 = Parser.parseMap(PATH_TO_JSON_FILE_2);
        Map<String, String> diff = Differ.getDifferences(map1, map2);
        assertThat(JSON.applyJSON(diff)).isEqualTo(expected);
    }
}
