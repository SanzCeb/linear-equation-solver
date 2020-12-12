package solver.matrix;

import java.util.List;

class SwapCommand {
    private final List<LinearEquation> equations;
    private final int column1;
    private final int column2;

    public SwapCommand(List<LinearEquation> equations, int column1, int column2) {
        this.equations = equations;
        this.column1 = column1;
        this.column2 = column2;
    }

    public void execute() {
        equations.forEach(linearEquation -> linearEquation.swap(column1, column2));
        System.out.printf("C%d <-> C%d%n", column1 + 1, column2 + 2);
    }

    public void undo() {
        equations.forEach(linearEquation -> linearEquation.swap(column2, column1));
    }
}
