package hexlet.code;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;
import java.util.TreeSet;

public class Differ {
    public static String generate(String path1, String path2, String format) {
        try {
            TreeMap<String, Object> map1 = Parser.parseMap(path1);
            TreeMap<String, Object> map2 = Parser.parseMap(path2);
            List<Entry> differences = getDifferences(map1, map2);
            return Formatter.getFormattedString(differences, format);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    public static String generate(String path1, String path2) {
        return generate(path1, path2, "stylish");
    }

    public static List<Entry> getDifferences(Map<String, Object> map1, Map<String, Object> map2) {
        TreeSet<String> keys = getOrderedKeySet(map1, map2);
        if (keys.isEmpty()) {
            return new ArrayList<>();
        }
        List<Entry> diffs = new ArrayList<>();
        for (String key : keys) {
            String status = getStatus(map1, map2, key);
            diffs.add(new Entry(key, status, map1.get(key), map2.get(key)));
        }
        return diffs;
    }

    private static TreeSet<String> getOrderedKeySet(Map<String, Object> map1, Map<String, Object> map2) {
        TreeSet<String> keys = new TreeSet<>(map1.keySet());
        keys.addAll(map2.keySet());
        return keys;
    }

    private static String getStatus(Map<String, Object> map1, Map<String, Object> map2, String key) {
        if (map1.containsKey(key) && map2.containsKey(key)) {
            boolean hasChange = !Objects.equals(map1.get(key), map2.get(key));
            return hasChange ? "changed" : "unchanged";
        }
        if (!map1.containsKey(key)) {
            return "added";
        }
        return "removed";
    }
}
