package solver.methods;

public class StraightLineEquationSolver {
    public static double getSolution(double a, double b) {
        return (a == 0) ? Double.POSITIVE_INFINITY : b / a;
    }
}
