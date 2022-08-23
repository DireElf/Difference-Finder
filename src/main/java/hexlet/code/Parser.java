package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.TreeMap;

public class Parser {
    public static TreeMap<String, Object> parseMap(String path) {
        TreeMap<String, Object> result = new TreeMap<>();
        try {
            File file = Paths.get(path).toAbsolutePath().normalize().toFile();
            validate(file);
            if (path.toLowerCase().endsWith(".json")) {
                result = new ObjectMapper().readValue(file, new TypeReference<>() {
                });
            } else {
                result = new ObjectMapper(new YAMLFactory()).readValue(file, new TypeReference<>() {
                });
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    private static void validate(File file) {
        if (!file.exists()) {
            throw new Error(String.format("File \"%s\" not found", file.getName()));
        }
        String fileName = file.getName().toLowerCase();
        if (!(fileName.endsWith(".json") || fileName.endsWith(".yaml") || fileName.endsWith(".yml"))) {
            throw new Error("Invalid file format. Available extensions are: .json, .yml, .yaml");
        }
    }
}
