package solver.equations;

public class StraightLineEquation {
    private final double a;
    private final double b;
    private double x = Double.NaN;

    public StraightLineEquation(double a, double b) {
        this.a = a;
        this.b = b;
    }

    public double getSolution() {
        if (Double.isNaN(x)) {
            solve();
        }
        return x;
    }

    private void solve() {
        x = (a == 0) ? Double.POSITIVE_INFINITY : b / a;
    }

}
