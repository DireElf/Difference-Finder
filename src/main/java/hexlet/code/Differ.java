package hexlet.code;

import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;
import java.util.TreeSet;

public class Differ {
    public static String generate(String path1, String path2, String format) {
        TreeMap<String, Object> map1 = Parser.parseMap(path1);
        TreeMap<String, Object> map2 = Parser.parseMap(path2);
        Map<String, String> differences = getDifferences(map1, map2);
        return Formatter.getFormattedString(map1, map2, differences, format);
    }

    public static String generate(String path1, String path2) {
        return generate(path1, path2, "stylish");
    }

    public static TreeMap<String, String> getDifferences(Map<String, Object> map1, Map<String, Object> map2) {
        TreeSet<String> keys = getOrderedKeySet(map1, map2);
        if (keys.isEmpty()) {
            return new TreeMap<>();
        }
        TreeMap<String, String> diffs = new TreeMap<>();
        for (String key : keys) {
            diffs.put(key, getStatus(map1, map2, key));
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
