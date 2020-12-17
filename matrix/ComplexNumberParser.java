package solver.matrix;

import java.util.StringTokenizer;

public class ComplexNumberParser {

    private final static String REAL_NUMBER_PATTERN_FORMAT = "-?\\d*(\\.\\d+)?";
    private final static String IMAGINARY_NUMBER_PATTERN_FORMAT = "-?\\d*(\\.d+)?i";
    private final static String COMPLEX_NUMBER_PATTERN_FORMAT = String.format("%s[+-]%s", REAL_NUMBER_PATTERN_FORMAT, IMAGINARY_NUMBER_PATTERN_FORMAT);

    public static ComplexNumber parseComplexNumber(String s) {

        if (s.matches(REAL_NUMBER_PATTERN_FORMAT)) {
            return new ComplexNumber(Double.parseDouble(s), 0);
        }

        if (s.matches(IMAGINARY_NUMBER_PATTERN_FORMAT)) {
            return new ComplexNumber(0, parseImaginaryPart(s));
        }

        if (s.matches(COMPLEX_NUMBER_PATTERN_FORMAT)) {
            String realPartToken;
            if (s.startsWith("-")) {
                realPartToken = "-" + new StringTokenizer(s, "-+", false).nextToken();
            } else {
                realPartToken = new StringTokenizer(s, "-+", false).nextToken();
            }

            double realPart = Double.parseDouble(realPartToken);
            var remainingString = s.substring(realPartToken.length());
            double imaginaryPart = parseImaginaryPart(remainingString);
            return new ComplexNumber(realPart, imaginaryPart);
        }

        return null;
    }

    private static double parseImaginaryPart (String imaginaryNumber) {
        double imaginaryPart;
        if (imaginaryNumber.matches("\\+?i")) {
            imaginaryPart = 1;
        } else if (imaginaryNumber.equals("-i")) {
            imaginaryPart = -1;
        } else {
            var cardinalBeginIndex = imaginaryNumber.startsWith("+") ? 1 : 0;
            var cardinal = imaginaryNumber.substring(cardinalBeginIndex, imaginaryNumber.length() - 1);
            imaginaryPart = Double.parseDouble(cardinal);
        }
        return imaginaryPart;
    }
}
