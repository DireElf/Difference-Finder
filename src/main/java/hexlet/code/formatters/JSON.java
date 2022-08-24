package hexlet.code.formatters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.Entry;

import java.util.List;

public class JSON {
    public static String format(List<Entry> differences) {
        try {
            return new ObjectMapper().writeValueAsString(differences);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
