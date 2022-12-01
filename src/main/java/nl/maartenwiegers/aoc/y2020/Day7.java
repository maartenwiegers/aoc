package nl.maartenwiegers.aoc.y2020;

import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import nl.maartenwiegers.aoc.commons.FileService;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
public class Day7 {

    private static final String FILE_NAME = "input/y2020/07-%s.txt";
    private static final String SHINY_GOLD = "shinygold";
    private final List<Bag> bags = new ArrayList<>();

    public long getCountBagColorsHoldingAtLeastOneShinyGold(String filename) {
        initializeBags(filename);
        Set<String> colors = new HashSet<>();
        colors.add(SHINY_GOLD);

        Set<String> outerBags = new HashSet<>();
        while (!colors.isEmpty()) {
            Set<String> newColors = new HashSet<>();
            colors.stream()
                    .map(color -> bags.stream()
                            .filter(bag -> bag.getContents()
                                    .containsKey(color))
                            .map(Bag::getColor)
                            .collect(Collectors.toSet()))
                    .forEach(newColors::addAll);
            newColors.removeAll(outerBags);
            outerBags.addAll(newColors);

            colors.clear();
            colors.addAll(newColors);
        }
        return outerBags.size();
    }

    public long getCountBagsInsideOneShinyGold(String filename) {
        initializeBags(filename);
        return getCountBagsInsideBag(SHINY_GOLD, bags.stream()
                .collect(Collectors.toMap(Bag::getColor, bag -> bag)));
    }

    private long getCountBagsInsideBag(String bagColor, Map<String, Bag> bags) {
        Bag bag = bags.get(bagColor);

        long totalCount = 0;
        for (String color : bag.getContents()
                .keySet()) {
            long bagsInBag = getCountBagsInsideBag(color, bags);
            totalCount += (bagsInBag * bag.getContents()
                    .get(color)) + bag.getContents()
                    .get(color);
        }
        return totalCount;
    }

    private void initializeBags(String filename) {
        FileService.getInputAsListString(String.format(FILE_NAME, filename))
                .forEach(line -> {
                    log.debug(line);
                    String[] lineSplit = StringUtils.splitByWholeSeparator(line, "contain");
                    String[] thisBag = StringUtils.split(lineSplit[0]);
                    bags.add(Bag.builder()
                                     .color(thisBag[0] + thisBag[1])
                                     .contents(getContentsFromLine(lineSplit[1]))
                                     .build());
                });
        log.info("Initialized bags: {}", bags);
    }

    private Map<String, Integer> getContentsFromLine(String line) {
        if (StringUtils.contains(line, "no other bags")) {
            return Collections.emptyMap();
        }
        Map<String, Integer> contents = new HashMap<>();
        Arrays.stream(StringUtils.split(line, ","))
                .forEach(bag -> {
                    String[] bagElements = StringUtils.split(bag);
                    contents.put(bagElements[1] + bagElements[2], Integer.valueOf(bagElements[0]));
                });
        return contents;
    }

    @Builder
    @Data
    static class Bag {
        String color;
        Map<String, Integer> contents;
    }
}
