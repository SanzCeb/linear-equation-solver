package solver;

import solver.io.LinearEquationSolverInput;
import solver.io.LinearEquationSolverOutput;
import solver.matrix.AugmentedMatrix;
import solver.methods.GaussianJordanElimination;

import java.nio.file.Path;

public class LinearEquationSolverCLI {

    public static void run(Path in, Path out) {
        var augmentedRows = LinearEquationSolverInput.readAugmentedRows(in);
        var augmentedMatrix = new AugmentedMatrix(augmentedRows);
        var solutions = GaussianJordanElimination.getSolutions(augmentedMatrix);
        LinearEquationSolverOutput.writeSolutions(out, solutions);
    }

}
