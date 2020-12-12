package solver.matrix;

import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.stream.IntStream;

public class LinearEquationsManipulator {
    private final List<LinearEquation> linearEquations;
    private final CommandHistory commandHistory;
    private final int lastVariableIndex;

    public LinearEquationsManipulator(List<LinearEquation> linearEquations, int numVariables) {
        this.linearEquations = linearEquations;
        this.commandHistory = new CommandHistory();
        this.lastVariableIndex = numVariables - 1;
    }

    public void manipulateEquations() {
        for (int i = 0; i < linearEquations.size(); i++) {
            var currentEquation = linearEquations.get(i);
            var diagonalIndex = Math.min(i, lastVariableIndex);
            var diagonalEntry = currentEquation.getEntry(diagonalIndex);
            if (diagonalEntry == 0) {
                var firstEquationWithNonZeroColumnOpt = findFirstEquationWithNonZeroColumn(i);

                //if the coefficient is zero, swap the row with one that doesn't have a zero coefficient.
                if (firstEquationWithNonZeroColumnOpt.isPresent()) {
                    var firstEquationWithNonZeroDiagonalEntry = firstEquationWithNonZeroColumnOpt.get();
                    swapEquations(firstEquationWithNonZeroDiagonalEntry, currentEquation);
                } else {
                    // If all the coefficients below the element are zero,
                    // you should look for a non-zero coefficient to the right of the current one.
                    // If such a coefficient is found in some other column,
                    // you should swap that column with the current one
                    var leadingEntry = findFirstNonZeroCoefficientAfterColumn(currentEquation, diagonalIndex);
                    if (leadingEntry.isPresent()) {
                        var leadingEntryPos = currentEquation.getPosition(leadingEntry.getAsDouble());
                        swapColumns(i, leadingEntryPos);
                    } else {
                        //If all the coefficients below and to the right of the element are zero,
                        // you should find the non-zero element in the whole bottom-right part of the linear system.
                        for (int j = i + 1; j < linearEquations.size(); j++) {
                            var anEquationBelow = linearEquations.get(j);
                            var bottomEquationLeadingEntry = findFirstNonZeroCoefficientAfterColumn(anEquationBelow, j);
                            // If such a coefficient is found in some other row and column,
                            // you should swap both those row and column so that this non-zero element
                            // is in place of the current element
                            if (bottomEquationLeadingEntry.isPresent()) {
                                var bottomEquationLeadingEntryPos = anEquationBelow.getPosition(bottomEquationLeadingEntry.getAsDouble());
                                swapEquations(currentEquation, anEquationBelow);
                                swapColumns(i, bottomEquationLeadingEntryPos);
                            }
                        }
                        //If there are no such elements in the whole bottom-right part of the linear system,
                        // you should end the first part of the algorithm.
                        if (linearEquations.indexOf(currentEquation) == i) {
                            break;
                        }
                    }
                }
            }
        }
    }

    private void swapColumns(int i, int j) {
        var swapCommand = new SwapCommand(linearEquations, i, j);
        commandHistory.pushCommand(swapCommand);
        swapCommand.execute();
    }

    private void swapEquations(LinearEquation linearEquation, LinearEquation currentEquation) {
        int linearEquationPosition = linearEquations.indexOf(linearEquation);
        int currentEquationPosition = linearEquations.indexOf(currentEquation);
        linearEquations.set(linearEquationPosition, currentEquation);
        linearEquations.set(currentEquationPosition, linearEquation);
        System.out.printf("R%d <-> R%d%n", currentEquationPosition + 1, linearEquationPosition + 1);
    }

    private Optional<LinearEquation> findFirstEquationWithNonZeroColumn(int row) {
        return IntStream.range(row, linearEquations.size())
                .mapToObj(linearEquations::get)
                .filter(linearEquation -> linearEquation.getEntry(row) != 0)
                .findFirst();
    }

    private OptionalDouble findFirstNonZeroCoefficientAfterColumn(LinearEquation linearEquation, int column) {
        return IntStream.rangeClosed(column, lastVariableIndex)
                .mapToDouble(linearEquation::getEntry)
                .filter(entry -> entry != 0)
                .findFirst();
    }

    public void restoreEquations() {
        while (commandHistory.isNotEmpty()){
            commandHistory.popCommand().undo();
        }
    }

}
