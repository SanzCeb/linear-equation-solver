package solver;

import solver.equations.StraightLineEquation;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class LinearEquationSolver {

    public static void run() {
        var scanner = new Scanner(System.in);
        var constants = Arrays.stream(scanner.nextLine().split(" "))
                .mapToDouble(Double::parseDouble)
                .boxed()
                .collect(Collectors.toList());
        var a = constants.get(0);
        var b = constants.get(1);
        var x = new StraightLineEquation().solve(a , b);
        System.out.println(x);
    }

}
