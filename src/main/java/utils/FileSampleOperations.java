package utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class FileSampleOperations {

    public static final String DB = "db/";

    static public void save(String className, String name, List<Sample> samples) throws IOException {
        List<String> stringList = samples.stream().map(Sample::toString).collect(Collectors.toList());
        stringList.add(0, className + ";");
        Files.write(Paths.get(DB + name), stringList);
    }
}
