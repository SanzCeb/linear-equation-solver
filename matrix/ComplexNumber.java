package solver.matrix;

public class ComplexNumber {
    public final static ComplexNumber ZERO;
    public final static ComplexNumber POSITIVE_INFINITY;
    public static final ComplexNumber ONE;
    private final double realPart;
    private final double imaginaryPart;
    private final double conjugateProduct;

    private final static String IMAGINARY_PART_SYMBOL = "i";

    static {
        ZERO = new ComplexNumber(0, 0);
        ONE = new ComplexNumber(1.0, 0);
        POSITIVE_INFINITY = new ComplexNumber(Double.POSITIVE_INFINITY
                , Double.POSITIVE_INFINITY);

    }

    public ComplexNumber(double realPart, double imaginaryPart) {
        this.realPart = Math.abs(realPart) < 1e-6 ? 0 : realPart;
        this.imaginaryPart = Math.abs(imaginaryPart) < 1e-6 ? 0 : imaginaryPart;
        this.conjugateProduct = conjugateProduct();
    }

    public ComplexNumber mulBy(ComplexNumber number) {
        var realPart = mulRealPart(number);
        var imaginaryPart = mulImaginaryPart(number);
        return new ComplexNumber(realPart, imaginaryPart);
    }

    public ComplexNumber add(ComplexNumber number) {
        var newRealPart = realPart + number.realPart;
        var newImaginaryPart = imaginaryPart + number.imaginaryPart;
        return new ComplexNumber(newRealPart, newImaginaryPart);
    }

    public boolean isNotZero() {
        return ZERO.realPart != realPart ||
                ZERO.imaginaryPart != imaginaryPart;
    }

    public boolean isZero() {
        return ZERO.realPart == realPart &&
                ZERO.imaginaryPart == imaginaryPart;
    }

    public ComplexNumber divBy(ComplexNumber number) {
        if (number.conjugateProduct == 0) {
            return ComplexNumber.POSITIVE_INFINITY;
        }

        var numberConjugateProduct = this.mulBy(number.conjugate());
        var realPart = numberConjugateProduct.realPart / number.conjugateProduct;
        var imaginaryPart = numberConjugateProduct.imaginaryPart / number.conjugateProduct;

        return new ComplexNumber(realPart, imaginaryPart);
    }

    public ComplexNumber neg() {
        return new ComplexNumber(-realPart, -imaginaryPart);
    }

    private double mulRealPart(ComplexNumber number) {
        return this.realPart * number.realPart +
                -(imaginaryPart * number.imaginaryPart);
    }

    private double mulImaginaryPart(ComplexNumber number) {
        return imaginaryPart * number.realPart +
                (realPart * number.imaginaryPart);
    }

    private ComplexNumber conjugate(){
        return new ComplexNumber(realPart, -imaginaryPart);
    }

    private double conjugateProduct() {
        return Math.pow(realPart, 2) + Math.pow(imaginaryPart, 2);
    }

    @Override
    public String toString() {
        if (imaginaryPart == 0) {
            return Double.toString(realPart);
        }

        return String.format("%s%s%s", realPartToString(), operatorToString(), imaginaryPartToString());
    }

    private String imaginaryPartToString() {
        return imaginaryPart != 0 ? String.format("%s%s", imaginaryPart, IMAGINARY_PART_SYMBOL) : "";
    }

    private String operatorToString() {
        return imaginaryPart > 0 ? "+" : "";
    }
    private String realPartToString () {
        return realPart != 0 ? Double.toString(realPart) : "";
    }
}
