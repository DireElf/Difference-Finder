package hexlet.code;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeSet;

public final class Entry {
    private String name;
    private String status;
    private Object firstValue;
    private Object secondValue;

    public Entry(String newName, String newStatus, Object newFirstValue, Object newSecondValue) {
        this.name = newName;
        this.status = newStatus;
        this.firstValue = newFirstValue;
        this.secondValue = newSecondValue;
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

    public String getName() {
        return name;
    }

    public String getStatus() {
        return status;
    }

    public Object getFirstValue() {
        return firstValue;
    }

    public Object getSecondValue() {
        return secondValue;
    }


}
