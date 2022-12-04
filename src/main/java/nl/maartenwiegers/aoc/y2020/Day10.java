package nl.maartenwiegers.aoc.y2020;

import nl.maartenwiegers.aoc.commons.FileService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day10 {

    private static final String INPUT_FILE_NAME = "input/y2020/10-%s.txt";

    private List<Integer> adapters = new ArrayList<>();

    public int getFactorOfDifferences(String filename) {
        adapters.clear();
        adapters = FileService.getInputAsListInteger(String.format(INPUT_FILE_NAME, filename))
                .stream()
                .sorted()
                .toList();

        int numberAdapters1JoltDifference = 0;
        int numberAdapters3JoltDifference = 0;

        for (int i = 0; i < adapters.size() - 1; i++) {
            int step = adapters.get(i + 1) - adapters.get(i);
            if (step == 1) {
                numberAdapters1JoltDifference++;
            }
            if (step == 3) {
                numberAdapters3JoltDifference++;
            }
        }

        return (numberAdapters1JoltDifference + 1) // Needs + 1 for the baseline of the charging outlet
                * (numberAdapters3JoltDifference + 1); // Needs + 1 for the baseline difference accepted by the device
    }

    public long getNumberOfPossibleArrangements(String filename) {
        adapters.clear();

        adapters.addAll(FileService.getInputAsListInteger(String.format(INPUT_FILE_NAME, filename))
                .stream()
                .sorted()
                .toList());
        adapters.add(0, 0);
        adapters.add(adapters.get(adapters.size() - 1) + 3);

        Map<Integer, Long> combinations = new HashMap<>();

        return countCombinations(adapters, combinations);
    }

    private long countCombinations(List<Integer> adapters, Map<Integer, Long> combinations) {
        if (adapters.size() <= 2) {
            return 1;
        }

        int lastAdapter = adapters.get(adapters.size() - 1);

        if (combinations.containsKey(lastAdapter)) {
            return combinations.get(lastAdapter);
        }

        long foundCombinations = 0;

        for (int i = adapters.size() - 2; i > adapters.size() - 5 && i >= 0; i--) {
            int adapter = adapters.get(i);

            if (lastAdapter - adapter <= 3) {
                foundCombinations += countCombinations(adapters.subList(0, i + 1), combinations);
            }
        }

        combinations.put(lastAdapter, foundCombinations);

        return foundCombinations;
    }
}
