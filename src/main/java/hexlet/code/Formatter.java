package hexlet.code;

import hexlet.code.formatters.JSON;
import hexlet.code.formatters.Plain;
import hexlet.code.formatters.Stylish;

import java.util.Map;

public class Formatter {
    public static String getFormattedString(Map<String, Object> map1, Map<String, Object> map2,
                                            Map<String, String> differences, String format) {
        if (format.equals("plain")) {
            return Plain.applyPlain(map1, map2, differences);
        } else if (format.equals("json")) {
            return JSON.applyJSON(differences);
        }
        return Stylish.applyStylish(map1, map2, differences);
    }
}
