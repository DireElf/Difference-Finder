package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.util.concurrent.Callable;

@Command(name = "gendiff", mixinStandardHelpOptions = true, version = "1.0",
        description = "Compares two configuration files and shows a difference.")

public class App implements Callable<Integer> {
    @Override
    public final Integer call() {
        System.out.println(Differ.generate(filepath1, filepath2, format));
        return 0;
    }

    @Option(names = {"-f", "--format"}, paramLabel = "format",
            defaultValue = "stylish", description = "output format [default: stylish]")
    private String format;

    @Parameters(index = "0", paramLabel = "filepath1", defaultValue = "./src/test/resources/file1.json",
            description = "path to first file")
    private String filepath1;

    @Parameters(index = "1", paramLabel = "filepath2", defaultValue = "./src/test/resources/file2.json",
            description = "path to second file")
    private String filepath2;

    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }
}
