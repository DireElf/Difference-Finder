package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.TreeMap;

public class Parser {
    public static TreeMap<String, Object> getDataMap(String path) throws IOException {
        validate(getFile(path));
        String data = Files.readString(Paths.get(path).toAbsolutePath().normalize());
        return parseMap(data, path);
    }

    public static TreeMap<String, Object> parseMap(String data, String path) throws IOException {
        TreeMap<String, Object> result;
        if (path.toLowerCase().endsWith(".json")) {
            result = new ObjectMapper().readValue(data, new TypeReference<>() {
            });
        } else {
            result = new ObjectMapper(new YAMLFactory()).readValue(data, new TypeReference<>() {
            });
        }
        if (result.isEmpty()) {
            throw new IOException("File \"" + getFile(path).getName() + "\" has no content");
        }
        return result;
    }

    private static void validate(File file) throws IOException {
        if (!file.exists()) {
            throw new FileNotFoundException("File \"" + file.getName() + "\" not found");
        }
        String fileName = file.getName().toLowerCase();
        if (!(fileName.endsWith(".json") || fileName.endsWith(".yaml") || fileName.endsWith(".yml"))) {
            throw new IOException("File \"" + fileName
                    + "\" has wrong format. Available extensions are: .json, .yml, .yaml");
        }
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            if (br.readLine() == null) {
                throw new IOException("File \"" + file.getName() + "\" is empty");
            }
        }
    }

    private static File getFile(String path) {
        return Paths.get(path).toAbsolutePath().normalize().toFile();
    }
}
