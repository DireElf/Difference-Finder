package hexlet.code;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.TreeMap;

public class Differ {
    public static String generate(String path1, String path2, String format) {
        try {
            validate(path1, path2);
            TreeMap<String, Object> map1 = Parser.getDataMap(getFileType(path1), getData(path1));
            TreeMap<String, Object> map2 = Parser.getDataMap(getFileType(path2), getData(path2));
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

    private static String getFileType(String path) throws IOException {
        if (path.toLowerCase().endsWith(".json")) {
            return "JSON";
        } else if (path.toLowerCase().endsWith(".yml") || path.toLowerCase().endsWith(".yaml")) {
            return "YML";
        } else {
            throw new IOException("File \"" + getPath(path).toFile().getName()
                    + "\" has wrong format. Available extensions are: .json, .yml, .yaml");
        }
    }

    private static String getData(String uri) throws IOException {
        return Files.readString(getPath(uri));
    }

    private static Path getPath(String path) {
        return Paths.get(path).toAbsolutePath().normalize();
    }
}
