package solver.equations;

public class StraightLineEquation {
    public static double getSolution(double a, double b) {
        return (a == 0) ? Double.POSITIVE_INFINITY : b / a;
    }
}
