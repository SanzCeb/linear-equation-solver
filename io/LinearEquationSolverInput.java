package solver.io;

import solver.matrix.AugmentedRow;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class LinearEquationSolverInput {
    public static List<AugmentedRow> readAugmentedRows(Path inputFilePath) {
        var augmentedRows = new ArrayList<AugmentedRow>();

        try {
            var lines = Files.readAllLines(inputFilePath);
            lines.remove(0);
            for (var line : lines) {
                var equation = Arrays.stream(line.split(" "))
                        .map(Double::parseDouble)
                        .collect(Collectors.toList());
                augmentedRows.add(new AugmentedRow(equation));
            }
            return augmentedRows;
        } catch (IOException e) {
            System.out.println("IO exception reading the input file");
            return Collections.emptyList();
        }
    }
}
