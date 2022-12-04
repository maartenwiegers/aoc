package nl.maartenwiegers.aoc.y2022;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day4Test {

    private final Day4 day4 = new Day4();

    private static Stream<Arguments> getArguments() {
        return Stream.of(Arguments.of("example", "full", 2),
                Arguments.of("puzzleinput", "full", 528),
                Arguments.of("example", "part", 4),
                Arguments.of("puzzleinput", "part", 881)
        );
    }

    @ParameterizedTest
    @MethodSource("getArguments")
    void shouldGetCorrectAnswers(String filename, String mode, int expected) {
        assertEquals(expected, day4.getCountOfOverlappingAssignments(filename, mode));
    }
}
