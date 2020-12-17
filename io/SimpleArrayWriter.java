package solver.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.stream.Collectors;

public class SimpleArrayWriter {
    public static <T> void exportObjectsAsStrings (Path out, T[] objects) {
        try {
            var solutionsStr = joinObjects(objects);
            Files.deleteIfExists(out);
            Files.writeString(out, solutionsStr, StandardOpenOption.CREATE_NEW);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static <T> String joinObjects(T[] solutions) {
        return Arrays.stream(solutions)
                .map(T::toString)
                .collect(Collectors.joining(System.lineSeparator()));
    }
}
