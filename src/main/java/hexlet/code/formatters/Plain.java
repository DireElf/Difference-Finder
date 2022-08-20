package hexlet.code.formatters;

import org.apache.commons.lang3.ClassUtils;

import java.util.Map;

public class Plain {
    public static String applyPlain(Map<String, Object> map1, Map<String, Object> map2,
                                    Map<String, String> differences) {
        StringBuilder stringBuilder = new StringBuilder();
        if (!differences.isEmpty()) {
            for (String key : differences.keySet()) {
                switch (differences.get(key)) {
                    case "added" -> stringBuilder.append(String.format(
                            "Property '%s' was added with value: %s\n", key, checkType(map2.get(key))));
                    case "changed" -> stringBuilder.append(String.format(
                            "Property '%s' was updated. From %s to %s\n",
                            key, checkType(map1.get(key)), checkType(map2.get(key))));
                    case "removed" -> stringBuilder.append(String.format("Property '%s' was removed\n", key));
                    default -> {
                        continue;
                    }
                }
            }
            stringBuilder.delete(stringBuilder.length() - 1, stringBuilder.length());
        }
        return stringBuilder.toString();
    }

    public static String checkType(Object object) {
        if (object == null) {
            return "null";
        }
        if (object instanceof String) {
            return String.format("'%s'", object);
        }
        if (!ClassUtils.isPrimitiveOrWrapper(object.getClass())) {
            return "[complex value]";
        }
        return String.valueOf(object);
    }
}
