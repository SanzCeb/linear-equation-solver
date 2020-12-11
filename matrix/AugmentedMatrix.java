package solver.matrix;

import solver.equations.StraightLineEquation;

import java.util.ArrayList;
import java.util.List;

public class AugmentedMatrix {
    public final int NUM_ROWS;
    private final List<AugmentedRow> augmentedRows;

    public AugmentedMatrix(List<AugmentedRow> augmentedRows) {
        NUM_ROWS = augmentedRows.size();
        this.augmentedRows = augmentedRows;
    }

    public AugmentedMatrix getRowEchelonForm() {
        var rowEchelonFormRows = new ArrayList<>(augmentedRows);
        for (int i = 0 ;  i < NUM_ROWS - 1; i++) {
            var echelonRow = rowEchelonFormRows.get(i);
            var xi = echelonRow.getEntry(i);
            for (int j = i + 1; j < NUM_ROWS; j++) {
                var currentAugmentedRow = rowEchelonFormRows.get(j);
                var entry = currentAugmentedRow.getEntry(i);
                var scalar = StraightLineEquation.getSolution(xi, -entry); // ax + b = 0
                AugmentedRow multipliedRow = echelonRow.mulRowByScalar(scalar);
                AugmentedRow reducedRowEchelonFormRow = currentAugmentedRow.addRow(multipliedRow);
                rowEchelonFormRows.set(j, reducedRowEchelonFormRow);
                System.out.printf("R%d + %.4fR%d -> R%d%n",j + 1, scalar, i + 1, j + 1);
            }
        }
        return new AugmentedMatrix(rowEchelonFormRows);
    }

    public AugmentedRow getAugmentedRow(int i) {
        return augmentedRows.get(i);
    }

    public AugmentedMatrix getReducedRowEchelonForm() {
        var rowEchelonForm = getRowEchelonForm()
                .divideRowsByLeadingEntry();
        var reducedRowEchelonFormRows = new ArrayList<>(rowEchelonForm.augmentedRows);
        for (int i = rowEchelonForm.NUM_ROWS - 1;  i > 0; i--) {
            var echelonRow = reducedRowEchelonFormRows.get(i);
            var xi = echelonRow.getEntry(i);
            for (int j = i - 1; j >= 0; j--) {
                var currentAugmentedRow = reducedRowEchelonFormRows.get(j);
                var entry = currentAugmentedRow.getEntry(i);
                var scalar = StraightLineEquation.getSolution(xi, -entry); // ax + b = 0
                AugmentedRow multipliedRow = echelonRow.mulRowByScalar(scalar);
                AugmentedRow reducedRowEchelonFormRow = currentAugmentedRow.addRow(multipliedRow);
                reducedRowEchelonFormRows.set(j, reducedRowEchelonFormRow);
                System.out.printf("R%d + %.4fR%d -> R%d%n",j + 1, scalar, i + 1, j + 1);
            }
        }

        return new AugmentedMatrix(reducedRowEchelonFormRows);
    }

    private AugmentedMatrix divideRowsByLeadingEntry() {
        var dividedRows = new ArrayList<AugmentedRow>();

        for (var augmentedRow : augmentedRows) {
            var leadingEntry = augmentedRow.getLeadingEntry();
            var dividedRow = augmentedRow.mulRowByScalar(Math.pow(leadingEntry, -1));
            dividedRows.add(dividedRow);
        }

        return new AugmentedMatrix(dividedRows);
    }

}
