package solver.matrix;

import java.util.List;
import java.util.stream.Collectors;

public class LinearEquation {
    private final List<ComplexNumber> equation;
    public LinearEquation(List<ComplexNumber> equation) {
        this.equation = equation;
    }

    public ComplexNumber getLeadingEntry() {
        return equation.stream()
                .limit(equation.size() - 1)
                .dropWhile(ComplexNumber::isZero)
                .findFirst()
                .orElse(ComplexNumber.ZERO);
    }

    public ComplexNumber getConstant() {
        return equation.get(equation.size() - 1);
    }

    public int numVariables () { return equation.size() - 1;}

    public ComplexNumber getEntry(int j) {
        return equation.get(j);
    }

    public LinearEquation mulRowByScalar(ComplexNumber scalar) {
        return new LinearEquation(equation.stream()
                .map(cell -> cell.mulBy(scalar))
                .collect(Collectors.toList()));
    }

    public LinearEquation addRow(LinearEquation addedRow) {
        var addedCells = addedRow.equation.iterator();
        return new LinearEquation(equation.stream()
                .map(cell -> cell.add(addedCells.next()))
                .collect(Collectors.toList()));
    }

    public int getPosition(ComplexNumber entry) {
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
                .anyMatch(ComplexNumber::isNotZero);
    }

    public boolean hasNoSolution() {
        var coefficient = getLeadingEntry();
        var constant = getConstant();
        return coefficient.isZero() && constant.isNotZero();
    }

    public boolean isNull() {
        return equation.stream()
                .allMatch(ComplexNumber::isZero);
    }
}
