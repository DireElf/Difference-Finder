package hexlet.code;

import java.io.IOException;
import java.util.List;
import java.util.TreeMap;

public class Differ {
    public static String generate(String path1, String path2, String format) {
        try {
            TreeMap<String, Object> map1 = Parser.getDataMap(path1);
            TreeMap<String, Object> map2 = Parser.getDataMap(path2);
            List<Entry> differences = Entry.getDifferences(map1, map2);
            return Formatter.getFormattedString(differences, format);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    public static String generate(String path1, String path2) {
        return generate(path1, path2, "stylish");
    }
}
