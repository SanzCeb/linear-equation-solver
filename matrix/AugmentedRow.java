package solver.matrix;

import java.util.List;
import java.util.stream.Collectors;

public class AugmentedRow {
    private final List<Double> equation;
    public AugmentedRow(List<Double> equation) {
        this.equation = equation;
    }

    public double getLeadingEntry() {
        return equation.stream()
                .dropWhile(cell -> cell == 0)
                .findFirst()
                .orElse((double) 0);
    }

    public double getConstant() {
        return equation.get(equation.size() - 1);
    }

    public double getEntry(int j) {
        return equation.get(j);
    }

    public AugmentedRow mulRowByScalar(double scalar) {
        return new AugmentedRow(equation.stream()
                .map(cell -> cell * scalar)
                .collect(Collectors.toList()));
    }

    public AugmentedRow addRow(AugmentedRow addedRow) {
        var subtractedCells = addedRow.equation.iterator();
        return new AugmentedRow(equation.stream()
                .map(cell -> cell + subtractedCells.next())
                .map(cell -> Math.abs(cell) < 1e-6 ? 0 : cell) //avoids floating errors
                .collect(Collectors.toList()));
    }
}
