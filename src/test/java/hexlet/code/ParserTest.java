package hexlet.code;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.TreeMap;

import static hexlet.code.AppTest.PATH_TO_EMPTY_JSON_FILE;
import static hexlet.code.AppTest.PATH_TO_EMPTY_YAML_FILE;
import static hexlet.code.AppTest.PATH_TO_JSON_FILE_1;
import static hexlet.code.AppTest.PATH_TO_YAML_FILE_1;
import static hexlet.code.AppTest.PATH_TO_YML_FILE;
import static org.assertj.core.api.Assertions.assertThat;

class ParserTest {
    private static TreeMap<String, Object> expected;

    public static void setExpected(TreeMap<String, Object> map) {
        ParserTest.expected = map;
    }

    @BeforeAll
    static void setUp() {
        TreeMap<String, Object> map = new TreeMap<>();
        final int sampleNumber = 50;
        map.put("host", "hexlet.io");
        map.put("timeout", sampleNumber);
        map.put("proxy", "123.234.53.22");
        map.put("follow", false);
        setExpected(map);
    }

    @Test
    void parseMap() {
        assertThat(Parser.parseMap(PATH_TO_JSON_FILE_1)).isEqualTo(expected);
        assertThat(Parser.parseMap(PATH_TO_YAML_FILE_1)).isEqualTo(expected);
        assertThat(Parser.parseMap(PATH_TO_YML_FILE)).isEqualTo(expected);
        assertThat(Parser.parseMap(PATH_TO_EMPTY_JSON_FILE)).isEqualTo(new TreeMap<>());
        assertThat(Parser.parseMap(PATH_TO_EMPTY_YAML_FILE)).isEqualTo(new TreeMap<>());
    }

    @Test
    void parseJsonFile() {
        assertThat(Parser.parseMap(PATH_TO_JSON_FILE_1)).isEqualTo(expected);
    }

    @Test
    void parseYamlFile() {
        assertThat(Parser.parseMap(PATH_TO_YAML_FILE_1)).isEqualTo(expected);
    }

    @Test
    void parseYmlFile() {
        assertThat(Parser.parseMap(PATH_TO_YML_FILE)).isEqualTo(expected);
    }

    @Test
    void parseEmptyJsonFile() {
        assertThat(Parser.parseMap(PATH_TO_EMPTY_JSON_FILE)).isEqualTo(new TreeMap<>());
    }

    @Test
    void parseEmptyYamlFile() {
        assertThat(Parser.parseMap(PATH_TO_EMPTY_YAML_FILE)).isEqualTo(new TreeMap<>());
    }
}
