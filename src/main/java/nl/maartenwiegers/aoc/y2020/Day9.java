package nl.maartenwiegers.aoc.y2020;

import nl.maartenwiegers.aoc.commons.FileService;

import java.util.ArrayList;
import java.util.List;

public class Day9 {
    private static final String INPUT_FILE_NAME = "input/y2020/09-%s.txt";
    private List<Long> numbers = new ArrayList<>();

    public long getFirstNumberNotASumOfPreamble(String filename, int preambleSize) {
        numbers = FileService.getInputAsListLong(String.format(INPUT_FILE_NAME, filename));

        for (int i = preambleSize; i < numbers.size(); i++) {
            if (!isASumOfPreambles(i, preambleSize)) {
                return numbers.get(i);
            }
        }

        return 0;
    }

    public long getEncryptionWeakness(String filename, int preambleSize) {
        long sumToFind = getFirstNumberNotASumOfPreamble(filename, preambleSize);

        for (int i = 0; i < numbers.size(); i++) {
            for (int j = 1; j < numbers.size(); j++) {
                List<Long> subset = numbers.stream()
                        .skip(i)
                        .limit(j)
                        .sorted()
                        .toList();
                long sumOfSubset = subset.stream()
                        .mapToLong(Long::longValue)
                        .sum();
                if (sumOfSubset == sumToFind) {
                    return subset.get(0) + subset.get(subset.size() - 1);
                }
                if (sumOfSubset > sumToFind) {
                    break;
                }
            }
        }

        return 0;
    }

    private boolean isASumOfPreambles(int index, int preambleSize) {
        long thisNumber = numbers.get(index);

        for (int i = index - preambleSize; i < index; i++) {
            for (int j = i + 1; j < index; j++) {
                if (numbers.get(i) + numbers.get(j) == thisNumber) {
                    return true;
                }
            }
        }

        return false;
    }
}
