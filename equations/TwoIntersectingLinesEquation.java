package solver.equations;

public class TwoIntersectingLinesEquation {
    private final double a, b, c, d, e, f;
    private double x = Double.NaN,  y = Double.NaN;

    public TwoIntersectingLinesEquation(double a, double b, double c, double d, double e, double f) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.e = e;
        this.f = f;
    }

    public double getX() {
        if (Double.isNaN(y)) {
            solveFirstEquation();
        }
        if (Double.isNaN(x)) {
            solveSecondEquation();
        }
        return x;
    }

    public double getY() {
        if (Double.isNaN(y)) {
            solveFirstEquation();
        }
        return y;
    }

    private void solveSecondEquation() {
        if (y != Double.POSITIVE_INFINITY) {
            var secondEquation = new StraightLineEquation(a, c - b * y);
            x = secondEquation.getSolution();
        } else {
            x = Double.POSITIVE_INFINITY;
        }
    }

    private void solveFirstEquation() {
        if ( a != 0) {
            var g = d / a;
            y = f - c * g;
            if (y != 0) {
                var h = b * g;
                y = e != h ? y / (e - h) : Double.POSITIVE_INFINITY;
            }
        }
    }

}
