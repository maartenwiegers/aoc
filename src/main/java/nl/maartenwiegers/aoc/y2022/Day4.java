package nl.maartenwiegers.aoc.y2022;

import lombok.Data;
import nl.maartenwiegers.aoc.commons.FileService;
import org.apache.commons.lang3.StringUtils;

import java.util.HashSet;
import java.util.List;
import java.util.stream.IntStream;

public class Day4 {

    private static final String INPUT_FILE_NAME = "input/y2022/04-%s.txt";

    public long getCountOfOverlappingAssignments(String filename, String mode) {
        List<String> assignmentPairs = FileService.getInputAsListString(String.format(INPUT_FILE_NAME, filename));
        return assignmentPairs.stream()
                .map(AssignmentPair::new)
                .map(pair -> isOverlapping(pair, mode))
                .filter(result -> result)
                .count();
    }

    private Boolean isOverlapping(AssignmentPair assignmentPair, String mode) {
        List<Integer> firstRange = IntStream.rangeClosed(assignmentPair.firstAssignment.start, assignmentPair.firstAssignment.end)
                .boxed().toList();
        List<Integer> secondRange = IntStream.rangeClosed(assignmentPair.secondAssignment.start, assignmentPair.secondAssignment.end)
                .boxed().toList();

        if (mode.equals("full")) {
            return new HashSet<>(firstRange).containsAll(secondRange) || new HashSet<>(secondRange).containsAll(firstRange);
        }
        return firstRange.stream().anyMatch(secondRange::contains) || secondRange.stream()
                .anyMatch(firstRange::contains);
    }

    @Data
    private static class Assignment {
        int start;
        int end;

        public Assignment(String left, String right) {
            start = Integer.parseInt(left);
            end = Integer.parseInt(right);
        }
    }

    @Data
    private static class AssignmentPair {
        Assignment firstAssignment;
        Assignment secondAssignment;

        public AssignmentPair(String input) {
            String[] assignments = StringUtils.split(input, ",");
            firstAssignment = new Assignment(StringUtils.split(assignments[0], "-")[0], StringUtils.split(assignments[0], "-")[1]);
            secondAssignment = new Assignment(StringUtils.split(assignments[1], "-")[0], StringUtils.split(assignments[1], "-")[1]);
        }
    }
}
