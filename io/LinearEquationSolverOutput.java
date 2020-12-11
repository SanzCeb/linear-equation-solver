package solver.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.stream.Collectors;

public class LinearEquationSolverOutput {
    public static void writeSolutions(Path out, double[] solutions) {
        System.out.println("The solution is:");
        try {
            var solutionsStr = Arrays.stream(solutions)
                    .mapToObj(Double::toString)
                    .collect(Collectors.joining(System.lineSeparator()));
            System.out.println(solutionsStr);
            Files.deleteIfExists(out);
            Files.writeString(out, solutionsStr, StandardOpenOption.CREATE_NEW);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
