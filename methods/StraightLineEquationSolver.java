package solver.methods;

import solver.matrix.ComplexNumber;

public class StraightLineEquationSolver {
    public static ComplexNumber getSolution(ComplexNumber a, ComplexNumber b) {
        return (a.isZero()) ? ComplexNumber.POSITIVE_INFINITY : b.divBy(a);
    }

}
