package nl.maartenwiegers.aoc.y2020;

import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import nl.maartenwiegers.aoc.commons.FileService;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Slf4j
public class Day8 {

    private static final String FILE_NAME = "input/y2020/08-%s.txt";
    private final List<Instruction> instructionList = new ArrayList<>();

    public long getAccumulatorValueAtStartOfSecondLoop(String filename) {
        initializeOperations(filename);
        long accumulator = 0;
        int counter = 0;

        while (true) {
            Instruction instruction = instructionList.get(counter);

            if (instruction.isVisited()) {
                return accumulator;
            }
            instruction.setVisited(true);

            if (instruction.getOperation()
                    .equals("acc")) {
                accumulator += instruction.getArgument();
            } else if (instruction.getOperation()
                    .equals("jmp")) {
                counter += instruction.getArgument() - 1;
            }

            counter++;
        }
    }

    public long getAccumulatorValueAtTermination(String filename) {
        initializeOperations(filename);
        for (int i = 0; i < instructionList.size(); i++) {
            instructionList.forEach(instruction -> instruction.setVisited(false));
            Instruction instruction = instructionList.get(i);

            if ("acc".equals(instruction.getOperation())) {
                continue;
            }

            List<Instruction> modifiedInstructions = new ArrayList<>(instructionList);
            Instruction newInstruction;
            if (instruction.getOperation()
                    .equals("nop")) {
                newInstruction = Instruction.builder()
                        .operation("jmp")
                        .argument(instruction.getArgument())
                        .build();
            } else if (instruction.getOperation()
                    .equals("jmp")) {
                newInstruction = Instruction.builder()
                        .operation("nop")
                        .argument(instruction.getArgument())
                        .build();
            } else {
                continue;
            }

            modifiedInstructions.set(i, newInstruction);
            AtomicLong accumulator = new AtomicLong();
            if (isTerminatingProgram(modifiedInstructions, accumulator)) {
                return accumulator.get();
            }

        }
        return 0;
    }

    private void initializeOperations(String filename) {
        FileService.getInputAsListString(String.format(FILE_NAME, filename))
                .forEach(line -> {
                    String[] lineSplit = StringUtils.split(line);
                    instructionList.add(Instruction.builder()
                                                .operation(lineSplit[0])
                                                .argument(Integer.parseInt(lineSplit[1]))
                                                .build());
                });
        log.info("Initialized instructions: {}", instructionList);
    }

    private boolean isTerminatingProgram(List<Instruction> modifiedInstructionList, AtomicLong accumulator) {
        int thisAccumulator = 0;
        int counter = 0;

        while (counter < modifiedInstructionList.size()) {
            Instruction instruction = modifiedInstructionList.get(counter);
            if (instruction.isVisited()) {
                return false;
            }
            instruction.setVisited(true);

            if (instruction.getOperation()
                    .equals("acc")) {
                thisAccumulator += instruction.getArgument();
            } else if (instruction.getOperation()
                    .equals("jmp")) {
                counter += instruction.getArgument() - 1;
            }

            counter++;
        }
        accumulator.set(thisAccumulator);
        return true;
    }

    @Builder
    @Data
    private static class Instruction {
        String operation;
        int argument;
        @Builder.Default
        boolean visited = false;
    }
}