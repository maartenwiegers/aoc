package nl.maartenwiegers.aoc.y2022;

import lombok.Builder;
import lombok.Data;
import nl.maartenwiegers.aoc.commons.FileService;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class Day5 {

    private static final String INPUT_FILE_NAME = "input/y2022/05-%s.txt";
    List<MoveInstruction> moveInstructions = new ArrayList<>();
    HashMap<Integer, Stack> stackHashMap = new HashMap<>();

    public String getCratesAtTopPart1(String filename) {
        processInput(filename);
        processMoveInstructionsPart1();
        return getCreatesAtTop();
    }

    public String getCratesAtTopPart2(String filename) {
        processInput(filename);
        processMoveInstructionsPart2();
        return getCreatesAtTop();
    }

    private String getCreatesAtTop() {
        return stackHashMap.values()
                .stream()
                .map(Stack::getTopCrate)
                .collect(Collectors.joining());
    }

    private void processMoveInstructionsPart1() {
        moveInstructions.forEach(moveInstruction -> {
            for (int i = 1; i <= moveInstruction.quantity; i++) {
                String crate = stackHashMap.get(moveInstruction.from)
                        .getTopCrate();
                if (StringUtils.isNotBlank(crate)) {
                    stackHashMap.get(moveInstruction.from)
                            .removeTopCrate();
                    stackHashMap.get(moveInstruction.to)
                            .addCrateToTop(crate);
                }
            }
        });
    }

    private void processMoveInstructionsPart2() {
        moveInstructions.forEach(moveInstruction -> {
            List<String> crates = stackHashMap.get(moveInstruction.from)
                    .getTopCrates(moveInstruction.quantity)
                    .stream()
                    .toList();
            stackHashMap.get(moveInstruction.from)
                    .removeTopCrates(moveInstruction.quantity);
            stackHashMap.get(moveInstruction.to)
                    .addCratesToTop(crates);
        });
    }

    private void processInput(String filename) {
        List<String> input = FileService.getMultiLineInputAsListString(String.format(INPUT_FILE_NAME, filename));
        Arrays.stream(StringUtils.split(input.get(1), "\r\n"))
                .forEach(line -> {
                    String[] lineSplit = StringUtils.split(line);
                    moveInstructions.add(MoveInstruction.builder()
                            .quantity(Integer.parseInt(lineSplit[1]))
                            .from(Integer.parseInt(lineSplit[3]))
                            .to(Integer.parseInt(lineSplit[5]))
                            .build());
                });

        ArrayList<String> crateLines = new ArrayList<>(List.of(StringUtils.split(input.get(0), "\r\n")));
        Collections.reverse(crateLines);
        crateLines.remove(0);
        for (String line : crateLines) {
            line = line.replace("    ", " [x]")
                    .replace("[", "")
                    .replace("]", "")
                    .replace(" ", "");
            for (int index = 0, stackNumber = 1; index < line.length(); index++, stackNumber++) {
                String crateLetter = StringUtils.substring(line, index, index + 1);
                if (!crateLetter.equals("x") && !crateLetter.equals(" ")) {
                    stackHashMap.computeIfAbsent(stackNumber, s -> Stack.builder()
                            .build()).crates.add(crateLetter);
                }

            }
        }
    }

    @Builder
    @Data
    private static class MoveInstruction {
        int quantity;
        int from;
        int to;
    }

    @Builder
    @Data
    private static class Stack {
        @Builder.Default
        List<String> crates = new ArrayList<>();

        String getTopCrate() {
            return crates.get(crates.size() - 1);
        }

        List<String> getTopCrates(int quantity) {
            return crates.subList(Math.max(crates.size() - quantity, 0), crates.size());
        }

        void addCrateToTop(String crate) {
            crates.add(crate);
        }

        void addCratesToTop(List<String> newCrates) {
            newCrates.forEach(this::addCrateToTop);
        }

        void removeTopCrate() {
            crates.remove(crates.size() - 1);
        }

        void removeTopCrates(int quantity) {
            int removed = 0;
            int size = crates.size();
            while (removed < Math.min(size, quantity)) {
                removeTopCrate();
                removed++;
            }
        }
    }
}
