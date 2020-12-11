package solver.methods;

import solver.equations.StraightLineEquation;
import solver.matrix.AugmentedMatrix;

public class GaussianJordanElimination {
    public static double[] getSolutions(AugmentedMatrix augmentedMatrix) {
        AugmentedMatrix rowEchelonForm = augmentedMatrix.getReducedRowEchelonForm();
        var output = new double[rowEchelonForm.NUM_ROWS];
        for (int i = rowEchelonForm.NUM_ROWS - 1; i >= 0; i--) {
            var augmentedRow = rowEchelonForm.getAugmentedRow(i);
            var coefficient = augmentedRow.getLeadingEntry();
            var constant = augmentedRow.getConstant();
            output[i] = StraightLineEquation.getSolution(coefficient, constant);
        }
        return output;
    }

}
