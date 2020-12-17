package solver.matrix;

import solver.methods.StraightLineEquationSolver;
import java.util.List;
import java.util.stream.Stream;

public class AugmentedMatrix {
    public final int NUM_LINEAR_EQUATIONS_COUNT;
    private final List<LinearEquation> linearEquations;
    private final int NUM_VARIABLES;

    public AugmentedMatrix(List<LinearEquation> linearEquations, int linearEquationsCount, int variablesCount) {
        NUM_LINEAR_EQUATIONS_COUNT = linearEquationsCount;
        this.linearEquations = linearEquations;
        this.NUM_VARIABLES = variablesCount;
    }

    public void calculateRowEchelonForm() {
        for (int i = 0 ;  i < NUM_LINEAR_EQUATIONS_COUNT - 1; i++) {
            for (int j = i + 1; j < NUM_LINEAR_EQUATIONS_COUNT; j++) {
                setCellToZero(i, j, linearEquations);
            }
        }
    }

    public LinearEquation getAugmentedRow(int i) {
        return linearEquations.get(i);
    }

    public List<LinearEquation> getLinearEquations() {return linearEquations; }

    public void calculateReducedRowEchelonForm() {
        calculateRowEchelonForm();
        divideRowsByLeadingEntry();
        for (int i = NUM_LINEAR_EQUATIONS_COUNT - 1;  i > 0; i--) {
            for (int j = i - 1; j >= 0; j--) {
                setCellToZero(i, j, linearEquations);
            }
        }
    }

    private void divideRowsByLeadingEntry() {
        var nonNullEquations = significantEquations()
                .iterator();

        while (nonNullEquations.hasNext()){
            var augmentedRow = nonNullEquations.next();
            var leadingEntry = augmentedRow.getLeadingEntry();
            var dividedRow = augmentedRow.mulRowByScalar(ComplexNumber.ONE.divBy(leadingEntry));
            linearEquations.set(linearEquations.indexOf(augmentedRow), dividedRow);
        }

    }

    private static void setCellToZero(int row, int column, List<LinearEquation> echelonFormRows) {
        var echelonRow = echelonFormRows.get(row);
        var leadingEntry = echelonRow.getEntry(row);
        var rowWithCellToNullify = echelonFormRows.get(column);
        var cellToNullify = rowWithCellToNullify.getEntry(row);
        if (leadingEntry.isNotZero() && cellToNullify.isNotZero()) {
            var scalar = StraightLineEquationSolver.getSolution(leadingEntry, cellToNullify.neg());
            LinearEquation multipliedRow = echelonRow.mulRowByScalar(scalar);
            LinearEquation rowWithCellNullified = rowWithCellToNullify.addRow(multipliedRow);
            echelonFormRows.set(column, rowWithCellNullified);
        }
    }

    public boolean hasInfiniteSolutions() {
        return significantEquations().count() < NUM_VARIABLES;
    }

    public boolean hasNoSolution() {
        return linearEquations.stream().anyMatch(LinearEquation::hasNoSolution);
    }

    private Stream<LinearEquation> significantEquations() {
        return linearEquations.stream().filter(LinearEquation::isNotNull);
    }
}
