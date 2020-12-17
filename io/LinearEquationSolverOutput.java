package solver.io;

import solver.matrix.ComplexNumber;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.stream.Collectors;

public class LinearEquationSolverOutput {
    public static void writeSolutions(Path out, ComplexNumber[] solutions) {
        System.out.println("The solution is:");
        try {
            String solutionsStr;

            if (solutions == null) {
                solutionsStr = "No solutions";
            } else if (Arrays.stream(solutions).anyMatch(solution -> solution == ComplexNumber.POSITIVE_INFINITY)) {
                solutionsStr = "Infinitely many solutions";
            } else {
                solutionsStr = Arrays.stream(solutions)
                        .map(ComplexNumber::toString)
                        .collect(Collectors.joining(System.lineSeparator()));
            }

            System.out.println(solutionsStr);
            Files.deleteIfExists(out);
            Files.writeString(out, solutionsStr, StandardOpenOption.CREATE_NEW);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
