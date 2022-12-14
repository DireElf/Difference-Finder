package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.IOException;
import java.util.Map;

public class Parser {
    public static Map<String, Object> getParsedMap(String dataType, String data) throws IOException {
        Map<String, Object> result;
        ObjectMapper mapper = dataType.equals("JSON") ? new ObjectMapper() : new ObjectMapper(new YAMLFactory());
        result = mapper.readValue(data, new TypeReference<>() {
        });
        if (result.isEmpty()) {
            throw new IOException("Empty " + dataType + " file");
        }
        return result;
    }
}
