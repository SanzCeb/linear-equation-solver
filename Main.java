package solver;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        Path in = null, out = null;
        for (int i = 0; i < args.length; i += 2) {
            if (isAnArgument(args[i])) {
                var fileName = args[i + 1];
                if (in == null ) {
                    in =  Paths.get(fileName);
                } else if (out == null) {
                    out = Paths.get(fileName);
                }
            }
        }
        if (in == null){
            System.out.println("-in arg not found");
        } else  {
            if (out == null) {
                System.out.println("-out arg not found");
            } else {
                LinearEquationSolverCLI.run(in, out);
            }
        }
    }

    private static boolean isAnArgument(String arg) {
        return arg.equalsIgnoreCase("-in") ||
                arg.equalsIgnoreCase("-out");
    }
}
