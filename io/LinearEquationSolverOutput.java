package solver.io;

import solver.matrix.ComplexNumber;

import java.nio.file.Path;
import java.util.Arrays;

public class LinearEquationSolverOutput {
    public static <T> void writeSolutions(Path out, T[] solutions) {
        System.out.println("The solutionsArray is:");
        Object[] solutionsArray;
        if (solutions == null) {
            solutionsArray = new String[] {"No solutions"};
        } else if (Arrays.stream(solutions).anyMatch(solution -> solution == ComplexNumber.POSITIVE_INFINITY)) {
            solutionsArray = new String[] {"Infinitely many solutions"};
        } else {
            solutionsArray = solutions;
        }
        SimpleArrayWriter.exportObjectsAsStrings(out, solutionsArray);
    }
}
