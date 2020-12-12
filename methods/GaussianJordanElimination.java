package solver.methods;

import solver.matrix.AugmentedMatrix;
import solver.matrix.LinearEquationsManipulator;


public class GaussianJordanElimination {
    public static double[] getSolutions(AugmentedMatrix augmentedMatrix) {
        LinearEquationsManipulator linearEquationsManipulator;
        var linearEquations = augmentedMatrix.getLinearEquations();
        int numVariables = linearEquations.size() > 0 ? linearEquations.get(0).numVariables() : 0;
        linearEquationsManipulator = new LinearEquationsManipulator(linearEquations, numVariables);
        linearEquationsManipulator.manipulateEquations();
        augmentedMatrix.calculateReducedRowEchelonForm();
        linearEquationsManipulator.restoreEquations();
        var output = new double[numVariables];

        if (augmentedMatrix.hasNoSolution()) {
            return null;
        }
        if (augmentedMatrix.hasInfiniteSolutions()) {
                return new double[]{Double.POSITIVE_INFINITY};
        }

        for (int i = augmentedMatrix.NUM_LINEAR_EQUATIONS_COUNT - 1; i >= 0; i--) {
            var augmentedRow = augmentedMatrix.getAugmentedRow(i);
            var coefficient = augmentedRow.getLeadingEntry();
            var constant = augmentedRow.getConstant();

            if (augmentedRow.isNull()) {
                continue;
            }

            output[i] = StraightLineEquationSolver.getSolution(coefficient, constant);
        }
        return output;
    }

}
