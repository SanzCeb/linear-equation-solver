package solver.equations;

public class StraightLineEquation extends LinearEquation{
    private final double a;
    private final double b;
    private double x = Double.NaN;

    public StraightLineEquation(double a, double b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public void solve() {
        x = (a == 0) ? Double.POSITIVE_INFINITY : b / a;
    }

    public double getSolution() {
        return x;
    }
}
