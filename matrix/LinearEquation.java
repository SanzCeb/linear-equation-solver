package solver.matrix;

import java.util.List;
import java.util.stream.Collectors;

public class LinearEquation {
    private final List<Double> equation;
    public LinearEquation(List<Double> equation) {
        this.equation = equation;
    }

    public double getLeadingEntry() {
        return equation.stream()
                .limit(equation.size() - 1)
                .dropWhile(cell -> cell == 0)
                .findFirst()
                .orElse((double) 0);
    }

    public double getConstant() {
        return equation.get(equation.size() - 1);
    }

    public int numVariables () { return equation.size() - 1;}

    public double getEntry(int j) {
        return equation.get(j);
    }

    public LinearEquation mulRowByScalar(double scalar) {
        return new LinearEquation(equation.stream()
                .map(cell -> cell * scalar)
                .collect(Collectors.toList()));
    }

    public LinearEquation addRow(LinearEquation addedRow) {
        var addedCells = addedRow.equation.iterator();
        return new LinearEquation(equation.stream()
                .map(cell -> cell + addedCells.next())
                .map(cell -> Math.abs(cell) < 1e-6 ? 0 : cell) //avoids floating errors
                .collect(Collectors.toList()));
    }

    public int getPosition(double entry) {
        return equation.indexOf(entry);
    }

    public void swap(int column1, int column2) {
        var entry2 = equation.get(column2);
        var entry1 = equation.set(column1, entry2);
        equation.set(column2, entry1);
    }

    public boolean isNotNull() {
        return equation.stream()
                .limit(equation.size() - 1)
                .anyMatch(aDouble -> aDouble != 0);
    }

    public boolean hasNoSolution() {
        var coefficient = getLeadingEntry();
        var constant = getConstant();
        return coefficient == 0 && constant != 0;
    }

    public boolean isNull() {
        return equation.stream()
                .allMatch(aDouble -> aDouble == 0);
    }
}
