package hexlet.code;

import java.util.Map;
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

    public static Map<String, String> getDifferences(Map<String, Object> map1, Map<String, Object> map2) {
        TreeSet<String> keys = getOrderedKeySet(map1, map2);
        if (!keys.isEmpty()) {
            Map<String, String> diffs = new TreeMap<>();
            for (String key : keys) {
                if (!map1.containsKey(key)) {
                    diffs.put(key, "added");
                } else {
                    if (map2.containsKey(key)) {
                        if (String.valueOf(map1.get(key)).equals(String.valueOf(map2.get(key)))) {
                            diffs.put(key, "unchanged");
                        } else {
                            diffs.put(key, "changed");
                        }
                    } else {
                        diffs.put(key, "removed");
                    }
                }
            }
            return diffs;
        }
        return new TreeMap<>();
    }

    private static TreeSet<String> getOrderedKeySet(Map<String, Object> map1, Map<String, Object> map2) {
        TreeSet<String> keys = new TreeSet<>(map1.keySet());
        keys.addAll(map2.keySet());
        return keys;
    }
}
