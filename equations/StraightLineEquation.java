package solver.equations;

public class StraightLineEquation implements LinearEquation{

    @Override
    public double solve(double a, double b) {
        return (a == 0) ? Double.POSITIVE_INFINITY : b / a;
    }
}
