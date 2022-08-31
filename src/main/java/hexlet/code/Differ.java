package hexlet.code;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Differ {
    public static String generate(String path1, String path2, String format) {
        try {
            validate(path1, path2);
            Map<String, String> data1 = getSourceData(path1);
            Map<String, String> data2 = getSourceData(path2);
            Map<String, Object> map1 = Parser.getParsedMap(data1);
            Map<String, Object> map2 = Parser.getParsedMap(data2);
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

    private static void validate(String... paths) throws IOException {
        for (String uri : paths) {
            File file = getPath(uri).toFile();
            if (!file.exists()) {
                throw new FileNotFoundException("File \"" + file.getName() + "\" not found");
            }
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                if (br.readLine() == null) {
                    throw new IOException("File \"" + file.getName() + "\" is empty");
                }
            }
        }
    }

    private static Map<String, String> getSourceData(String path) throws IOException {
        Map<String, String> result = new HashMap<>();
        if (path.toLowerCase().endsWith(".json")) {
            result.put("type", "JSON");
        } else if (path.toLowerCase().endsWith(".yml") || path.toLowerCase().endsWith(".yaml")) {
            result.put("type", "YML");
        } else {
            throw new IOException("File \"" + getPath(path).toFile().getName()
                    + "\" has wrong format. Available extensions are: .json, .yml, .yaml");
        }
        result.put("data", Files.readString(getPath(path)));
        return result;
    }

    private static Path getPath(String path) {
        return Paths.get(path).toAbsolutePath().normalize();
    }
}
