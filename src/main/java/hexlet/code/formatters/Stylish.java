package hexlet.code.formatters;

import java.util.Map;

public class Stylish {
    public static String applyStylish(Map<String, Object> map1, Map<String, Object> map2,
            Map<String, String> differences) {
        StringBuilder stringBuilder = new StringBuilder("{");
        if (!differences.isEmpty()) {
            stringBuilder.append("\n");
            for (String key : differences.keySet()) {
                switch (differences.get(key)) {
                    case "added" -> stringBuilder.append(String.format("  + %s: %s\n", key, map2.get(key)));
                    case "changed" -> {
                        stringBuilder.append(String.format("  - %s: %s\n", key, map1.get(key)));
                        stringBuilder.append(String.format("  + %s: %s\n", key, map2.get(key)));
                    }
                    case "removed" -> stringBuilder.append(String.format("  - %s: %s\n", key, map1.get(key)));
                    default -> stringBuilder.append(String.format("    %s: %s\n", key, map1.get(key)));
                }
            }
        }
        stringBuilder.append("}");
        return stringBuilder.toString();
    }
}
