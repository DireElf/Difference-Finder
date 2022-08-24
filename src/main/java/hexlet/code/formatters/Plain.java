package hexlet.code.formatters;

import hexlet.code.Entry;
import org.apache.commons.lang3.ClassUtils;

import java.util.List;

public class Plain {
    public static String format(List<Entry> differences) {
        StringBuilder stringBuilder = new StringBuilder();
        if (!differences.isEmpty()) {
            for (Entry entry : differences) {
                switch (entry.getStatus()) {
                    case "added" -> stringBuilder.
                            append(String.format("Property '%s' was added with value: %s\n",
                                    entry.getName(),
                                    format(entry.getSecondValue())));
                    case "changed" -> stringBuilder
                            .append(String.format("Property '%s' was updated. From %s to %s\n",
                                    entry.getName(),
                                    format(entry.getFirstValue()),
                                    format(entry.getSecondValue())));
                    case "removed" -> stringBuilder
                            .append(String.format("Property '%s' was removed\n",
                                    entry.getName()));
                    default -> stringBuilder.append("");
                }
            }
            stringBuilder.delete(stringBuilder.length() - 1, stringBuilder.length());
        }
        return stringBuilder.toString();
    }

    private static String format(Object object) {
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
