package hexlet.code;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Utils {
    static final String PATH_TO_TEST_RESOURCES = "./src/test/resources/";

    private static Path getFullPath(String fileName) {
        return Paths.get(PATH_TO_TEST_RESOURCES, fileName).toAbsolutePath().normalize();
    }

    public static String getPathAsString(String fileName) {
        return PATH_TO_TEST_RESOURCES + fileName;
    }

    public static String getFileContent(String fileName) {
        try {
            return Files.readString(getFullPath(fileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
