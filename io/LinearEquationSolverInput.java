package solver.io;

import solver.matrix.AugmentedMatrix;
import solver.matrix.LinearEquation;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

public class LinearEquationSolverInput {
    public static Optional<AugmentedMatrix> readAugmentedRows(Path inputFilePath) {
        var augmentedRows = new ArrayList<LinearEquation>();

        try {
            var lines = Files.readAllLines(inputFilePath);
            var metadata = lines.get(0).split(" ");
            var variablesCount = Integer.parseInt(metadata[0]);
            var equationsCount = Integer.parseInt(metadata[1]);
            lines.remove(0);
            for (var line : lines) {
                var equation = Arrays.stream(line.split(" "))
                        .map(Double::parseDouble)
                        .collect(Collectors.toList());
                augmentedRows.add(new LinearEquation(equation));
            }
            return Optional.of(new AugmentedMatrix(augmentedRows, equationsCount, variablesCount));
        } catch (IOException e) {
            System.out.println("IO exception reading the input file");
            return Optional.empty();
        }
    }
}
