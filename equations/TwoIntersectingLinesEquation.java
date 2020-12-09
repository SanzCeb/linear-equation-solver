package solver.equations;

public class TwoIntersectingLinesEquation  extends LinearEquation{
    private final double a, b, c, d, e, f;
    private double x = Double.POSITIVE_INFINITY,  y = Double.POSITIVE_INFINITY;

    public TwoIntersectingLinesEquation(double a, double b, double c, double d, double e, double f) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.e = e;
        this.f = f;
    }

    @Override
    public void solve() {
        solveFirstEquation();
        solveSecondEquation();
    }

    private void solveSecondEquation() {
        if (y != Double.POSITIVE_INFINITY) {
            var secondEquation = new StraightLineEquation(a, c - b * y);
            secondEquation.solve();
            x = secondEquation.getSolution();
        }
    }

    private void solveFirstEquation() {
        if ( a != 0) {
            var g = d / a;
            y = f - c * g;
            if (y != 0) {
                var h = b * g;
                if (e != h) {
                    y /= e - h;
                }
            }
        }
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}
