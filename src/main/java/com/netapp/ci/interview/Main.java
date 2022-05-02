package com.netapp.ci.interview;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {
        String inputDir = ClassLoader.getSystemResource("sample_logs")
                .getPath();
        String outputFile = ClassLoader.getSystemResource("expected")
                .getPath() + File.separator + "merged.log";
        LogMerger.mergeLogs2(inputDir, outputFile);

        File actual = new File(outputFile);
        if (!actual.exists()) {
            System.out.printf("Output file %s does not exist.%n", outputFile);
            System.exit(1);
        } else {
            List<String> expectedContent = Files.readAllLines(
                    Paths.get(ClassLoader.getSystemResource("expected/expected.log").toURI()));
            List<String> actualContent = Files.readAllLines((actual.toPath()));
            if (actualContent.size() != expectedContent.size()) {
                System.err.printf("Merged logs file, does not match the expected content. " +
                        "Merged logs contain %d lines, expected %d lines.%n", actualContent.size(), expectedContent.size());
                System.exit(1);
            }
            for (int line = 0; line < actualContent.size(); line++) {
                if (!actualContent.get(line).equals(expectedContent.get(line)) &&
                        LogUtils.readTimestamp(actualContent.get(line)) !=
                                LogUtils.readTimestamp(expectedContent.get(line))) {
                    System.err.printf("Merged logs file, does not match the expected content. " +
                                    "At line %d%n\tExpected: %s %n\tActual:   %s%n",
                            line + 1, expectedContent.get(line), actualContent.get(line));
                    System.exit(1);
                }
            }
            System.out.println("Test passed.");
        }
    }
}
