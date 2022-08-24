package hexlet.code;

import hexlet.code.formatters.JSON;
import hexlet.code.formatters.Plain;
import hexlet.code.formatters.Stylish;

import java.util.List;

public class Formatter {
    public static String getFormattedString(List<Entry> differences, String format) {
        if (format.equals("plain")) {
            return Plain.format(differences);
        } else if (format.equals("json")) {
            return JSON.format(differences);
        }
        return Stylish.format(differences);
    }
}
