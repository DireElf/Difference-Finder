package hexlet.code.formatters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

public class JSON {
    public static String applyJSON(Map<String, String> differences) {
        try {
            return new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(differences);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
