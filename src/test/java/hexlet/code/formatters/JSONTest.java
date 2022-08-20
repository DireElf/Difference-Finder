package hexlet.code.formatters;

import hexlet.code.Differ;
import hexlet.code.Parser;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.TreeMap;

import static hexlet.code.Utils.getPathAsString;
import static org.assertj.core.api.Assertions.assertThat;

class JSONTest {

    @Test
    void applyJSON() {
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
    void applyJSONtoEmptyMap() {
        assertThat(JSON.applyJSON(new TreeMap<>())).isEqualTo("{}");
    }
}
