package hexlet.code;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.TreeMap;

import static hexlet.code.Utils.getPathAsString;
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
    void parseJsonFile() {
        String path = getPathAsString("file1.json");
        assertThat(Parser.parseMap(path)).isEqualTo(expected);
    }

    @Test
    void parseYamlFile() {
        String path = getPathAsString("file1.yaml");
        assertThat(Parser.parseMap(path)).isEqualTo(expected);
    }

    @Test
    void parseYmlFile() {
        String path = getPathAsString("file1.yml");
        assertThat(Parser.parseMap(path)).isEqualTo(expected);
    }

    @Test
    void parseEmptyJsonFile() {
        String path = getPathAsString("empty.json");
        assertThat(Parser.parseMap(path)).isEqualTo(new TreeMap<>());
    }

    @Test
    void parseEmptyYamlFile() {
        String path = getPathAsString("empty.yaml");
        assertThat(Parser.parseMap(path)).isEqualTo(new TreeMap<>());
    }
}
