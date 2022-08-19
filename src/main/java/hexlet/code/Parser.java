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
        File file = Paths.get(path).toAbsolutePath().normalize().toFile();
        try {
            if (path.toLowerCase().endsWith(".json")) {
                return new ObjectMapper().readValue(file, new TypeReference<>() {
                });
            } else if (path.toLowerCase().endsWith(".yaml") || path.toLowerCase().endsWith(".yml")) {
                return new ObjectMapper(new YAMLFactory()).readValue(file, new TypeReference<>() {
                });
            } else {
                throw new RuntimeException("Invalid file format");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new TreeMap<>();
    }
}
