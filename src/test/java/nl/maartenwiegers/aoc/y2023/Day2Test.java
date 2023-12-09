package nl.maartenwiegers.aoc.y2023;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day2Test {

    private final Day2 day2 = new Day2();

    @ParameterizedTest
    @MethodSource("getArgumentsPart1")
    void shouldGetCorrectAnswers(long expected, String filename) {
        assertEquals(expected, day2.getSumOfPossibleIds(filename));
    }

    @ParameterizedTest
    @MethodSource("getArgumentsPart2")
    void shouldGetCorrectAnswersPart2(long expected, String filename) {
        assertEquals(expected, day2.getPowers(filename));
    }

    static Stream<Arguments> getArgumentsPart1() {
        return Stream.of(
                Arguments.of(8, "example-1"),
                Arguments.of(2348, "puzzleinput")
        );
    }

    static Stream<Arguments> getArgumentsPart2() {
        return Stream.of(
                Arguments.of(2286, "example-1"),
                Arguments.of(76008, "puzzleinput")
        );
    }
}
