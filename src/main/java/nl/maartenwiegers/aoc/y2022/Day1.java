package nl.maartenwiegers.aoc.y2022;

import lombok.extern.slf4j.Slf4j;
import nl.maartenwiegers.aoc.commons.FileService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class Day1 {

    private static final String INPUT_FILE_NAME = "input/y2022/01-%s.txt";

    public int getTopNMaxCaloriesCarriedByOneElf(String filename, int top) {
        List<String> elves = FileService.getMultiLineInputAsListString(String.format(INPUT_FILE_NAME, filename));
        List<Integer> sumsByElf = new ArrayList<>();
        elves.forEach(elf -> {
            AtomicInteger thisElf = new AtomicInteger();
            Arrays.stream(elf.split("\r\n")).forEach(row -> thisElf.getAndAdd(Integer.parseInt(row)));
            sumsByElf.add(thisElf.get());
        });
        log.info("Calculated calories per elf: {}", sumsByElf);
        return sumsByElf.stream()
                .sorted(Collections.reverseOrder())
                .limit(top)
                .mapToInt(Integer::intValue)
                .sum();
    }
}
