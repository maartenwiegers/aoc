package nl.maartenwiegers.aoc.y2022;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day2Test {

    private final Day2 day2 = new Day2();

    private static Stream<Arguments> getPart1Arguments() {
        return Stream.of(Arguments.of("example", 15), Arguments.of("puzzleinput", 14264));
    }

    private static Stream<Arguments> getPart2Arguments() {
        return Stream.of(Arguments.of("example", 12), Arguments.of("puzzleinput", 12382));
    }

    @ParameterizedTest
    @MethodSource("getPart1Arguments")
    void shouldGetCorrectAnswerPart1(String filename, int expected) {
        assertEquals(expected, day2.getScore(filename, 1));
    }

    @ParameterizedTest
    @MethodSource("getPart2Arguments")
    void shouldGetCorrectAnswerPart2(String filename, int expected) {
        assertEquals(expected, day2.getScore(filename, 2));
    }
}
