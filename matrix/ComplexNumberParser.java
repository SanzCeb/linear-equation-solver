package solver.matrix;

import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

public class ComplexNumberParser {

    private final static String REAL_NUMBER_PATTERN_FORMAT = "-?\\d*(\\.\\d+)?";
    private final static String IMAGINARY_NUMBER_PATTERN_FORMAT = "-?\\d*(\\.d+)?i";
    private final static String COMPLEX_NUMBER_PATTERN_FORMAT = String.format("%s[+-]%s", REAL_NUMBER_PATTERN_FORMAT, IMAGINARY_NUMBER_PATTERN_FORMAT);
    private static final Pattern REAL_NUMBER_PATTERN;
    private static final Pattern IMAGINARY_NUMBER_PATTERN;
    private static final Pattern COMPLEX_NUMBER_PATTERN;

    static {
        REAL_NUMBER_PATTERN = Pattern.compile(REAL_NUMBER_PATTERN_FORMAT);
        IMAGINARY_NUMBER_PATTERN = Pattern.compile(IMAGINARY_NUMBER_PATTERN_FORMAT);
        COMPLEX_NUMBER_PATTERN = Pattern.compile(COMPLEX_NUMBER_PATTERN_FORMAT);
    }

    public static ComplexNumber parseComplexNumber(String s) {
        var patternMatcher = REAL_NUMBER_PATTERN.matcher(s);

        if (patternMatcher.matches()) {
            return new ComplexNumber(Double.parseDouble(s), 0);
        }

        patternMatcher = IMAGINARY_NUMBER_PATTERN.matcher(s);
        if (patternMatcher.matches()) {
            double imaginaryPart;
            if (s.equals("i")) {
                imaginaryPart = 1;
            } else if (s.equals("-i")) {
                imaginaryPart = -1;
            } else {
                imaginaryPart = Double.parseDouble(s.substring(0, s.length() - 1));
            }
            return new ComplexNumber(0, imaginaryPart);
        }

        patternMatcher = COMPLEX_NUMBER_PATTERN.matcher(s);
        if (patternMatcher.matches()) {
            String realPartToken;
            if (s.startsWith("-")) {
                realPartToken = "-" + new StringTokenizer(s, "-+", false).nextToken();
            } else {
                realPartToken = new StringTokenizer(s, "-+", false).nextToken();
            }

            double realPart = Double.parseDouble(realPartToken);
            var remainingString = s.substring(realPartToken.length());
            double imaginaryPart;

            if (remainingString.equals("+i")) {
                imaginaryPart = 1;
            } else if (remainingString.equals("-i")) {
                imaginaryPart = -1;
            } else if (remainingString.startsWith("+")){
                imaginaryPart = Double.parseDouble(remainingString.substring(1, remainingString.length() - 1));
            } else {
                imaginaryPart = Double.parseDouble(remainingString.substring(0, remainingString.length() - 1));
            }

            return new ComplexNumber(realPart, imaginaryPart);
        }

        return null;
    }
}
