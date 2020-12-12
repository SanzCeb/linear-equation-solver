package solver;

import solver.io.LinearEquationSolverInput;
import solver.io.LinearEquationSolverOutput;
import solver.matrix.AugmentedMatrix;
import solver.methods.GaussianJordanElimination;

import java.nio.file.Path;

public class LinearEquationSolverCLI {

    public static void run(Path in, Path out) {
        LinearEquationSolverInput.readAugmentedRows(in).ifPresent(augmentedMatrix -> {
            var solutions = GaussianJordanElimination.getSolutions(augmentedMatrix);
            LinearEquationSolverOutput.writeSolutions(out, solutions);
        });
    }

}
