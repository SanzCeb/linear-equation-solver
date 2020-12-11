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
            for (int j = i + 1; j < NUM_ROWS; j++) {
                setCellToZero(i, j, rowEchelonFormRows);
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
            for (int j = i - 1; j >= 0; j--) {
                setCellToZero(i, j, reducedRowEchelonFormRows);
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

    private static void setCellToZero(int row, int column, ArrayList<AugmentedRow> echelonFormRows) {
        var echelonRow = echelonFormRows.get(row);
        var leadingEntry = echelonRow.getEntry(row);
        var rowWithCellToNullify = echelonFormRows.get(column);
        var cellToNullify = rowWithCellToNullify.getEntry(row);
        var scalar = StraightLineEquation.getSolution(leadingEntry, -cellToNullify);
        AugmentedRow multipliedRow = echelonRow.mulRowByScalar(scalar);
        AugmentedRow rowWithCellNullified = rowWithCellToNullify.addRow(multipliedRow);
        echelonFormRows.set(column, rowWithCellNullified);
    }
}
