package hexlet.code.formatters;

import hexlet.code.Entry;

import java.util.List;

public class Stylish {
    public static String format(List<Entry> differences) {
        StringBuilder stringBuilder = new StringBuilder("{");
        if (!differences.isEmpty()) {
            stringBuilder.append("\n");
            for (Entry entry : differences) {
                switch (entry.getStatus()) {
                    case "added" -> stringBuilder
                            .append(String.format("  + %s: %s\n", entry.getName(), entry.getSecondValue()));
                    case "changed" -> {
                        stringBuilder
                                .append(String.format("  - %s: %s\n", entry.getName(), entry.getFirstValue()));
                        stringBuilder
                                .append(String.format("  + %s: %s\n", entry.getName(), entry.getSecondValue()));
                    }
                    case "removed" -> stringBuilder
                            .append(String.format("  - %s: %s\n", entry.getName(), entry.getFirstValue()));
                    default -> stringBuilder
                            .append(String.format("    %s: %s\n", entry.getName(), entry.getFirstValue()));
                }
            }
        }
        stringBuilder.append("}");
        return stringBuilder.toString();
    }
}
