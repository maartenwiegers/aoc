package nl.maartenwiegers.aoc.y2022;

import nl.maartenwiegers.aoc.commons.FileService;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Day3 {

    private static final String INPUT_FILE_NAME = "input/y2022/03-%s.txt";

    public int getSumOfPriorityItems(String filename) {
        List<String> rucksacks = FileService.getInputAsListString(String.format(INPUT_FILE_NAME, filename));
        AtomicInteger sumOfPriorities = new AtomicInteger();
        rucksacks.forEach(rucksack -> {
            int length = rucksack.length();
            String firstCompartment = rucksack.substring(0, length / 2);
            String secondCompartment = rucksack.substring(length / 2);
            for (int i = 0; i < firstCompartment.length(); i++) {
                if (StringUtils.contains(secondCompartment, firstCompartment.charAt(i))) {
                    sumOfPriorities.getAndAdd(getPriorityForItem(firstCompartment.charAt(i)));
                    break;
                }
            }
        });
        return sumOfPriorities.get();
    }

    public int getSumOfPrioritiesOfBadges(String filename) {
        List<String> rucksacks = FileService.getInputAsListString(String.format(INPUT_FILE_NAME, filename));
        AtomicInteger sumOfPriorities = new AtomicInteger();
        for (int i = 0; i < rucksacks.size() - 2; i += 3) {
            String firstRucksack = rucksacks.get(i);
            String secondRucksack = rucksacks.get(i + 1);
            String thirdRucksack = rucksacks.get(i + 2);
            for (int j = 0; j < firstRucksack.length(); j++) {
                char charAt = firstRucksack.charAt(j);
                if (StringUtils.contains(secondRucksack, charAt) && StringUtils.contains(thirdRucksack, charAt)) {
                    sumOfPriorities.getAndAdd(getPriorityForItem(charAt));
                    break;
                }
            }
        }
        return sumOfPriorities.get();
    }

    private int getPriorityForItem(char item) {
        if (item >= 'a' && item <= 'z') {
            return (int) item - 'a' + 1;
        }
        if (item >= 'A' && item <= 'Z') {
            return (int) item - 'A' + 27;
        }
        return 0;
    }
}
