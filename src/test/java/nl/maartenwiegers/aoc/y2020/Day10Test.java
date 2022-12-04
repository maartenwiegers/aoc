package nl.maartenwiegers.aoc.y2020;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day10Test {

    private final Day10 day10 = new Day10();

    private static Stream<Arguments> getArgumentsPart1() {
        return Stream.of(Arguments.of("example-small", 35),
                Arguments.of("example-large", 220),
                Arguments.of("puzzleinput", 2516));
    }

    private static Stream<Arguments> getArgumentsPart2() {
        return Stream.of(Arguments.of("example-small", 8),
                Arguments.of("example-large", 19208),
                Arguments.of("puzzleinput", 2516));
    }

    @ParameterizedTest
    @MethodSource("getArgumentsPart1")
    void shouldGetCorrectAnswersPart1(String filename, int expected) {
        assertEquals(expected, day10.getFactorOfDifferences(filename));
    }

    @ParameterizedTest
    @MethodSource("getArgumentsPart2")
    void shouldGetCorrectAnswersPart2(String filename, int expected) {
        assertEquals(expected, day10.getNumberOfPossibleArrangements(filename));
    }
}
