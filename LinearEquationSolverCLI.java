package solver;

import solver.equations.StraightLineEquation;
import solver.equations.TwoIntersectingLinesEquation;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

public class LinearEquationSolverCLI {

    public static void run() {
        var scanner = new Scanner(System.in);
        var constants = DoubleStream.generate(scanner::nextDouble)
                .limit(6)
                .iterator();

        var a = constants.nextDouble();
        var b = constants.nextDouble();
        var c = constants.nextDouble();
        var d = constants.nextDouble();
        var e = constants.nextDouble();
        var f = constants.nextDouble();
        var equation = new TwoIntersectingLinesEquation(a, b, c, d, e, f);
        equation.solve();
        System.out.println (equation.getX() + " " + equation.getY());
    }

}
