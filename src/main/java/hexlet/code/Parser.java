package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

public class Parser {
    public static Map<String, Object> getDataMap(String fileType, String data) throws IOException {
        Map<String, Object> result = new TreeMap<>();
        if (fileType.equals("JSON")) {
            result = new ObjectMapper().readValue(data, new TypeReference<>() {
            });
        } else if (fileType.equals("YML")) {
            result = new ObjectMapper(new YAMLFactory()).readValue(data, new TypeReference<>() {
            });
        }
        if (result.isEmpty()) {
            throw new IOException("File has no content");
        }
        return result;
    }
}
