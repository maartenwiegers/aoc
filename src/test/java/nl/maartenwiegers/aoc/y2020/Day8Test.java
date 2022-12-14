package nl.maartenwiegers.aoc.y2020;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day8Test {

    private final Day8 day8 = new Day8();

    @ParameterizedTest
    @MethodSource("getPart1Arguments")
    void shouldGetCorrectAnswerPart1(long expected, String filename) {
        assertEquals(expected, day8.getAccumulatorValueAtStartOfSecondLoop(filename));
    }

    @ParameterizedTest
    @MethodSource("getPart2Arguments")
    void shouldGetCorrectAnswerPart2(long expected, String filename) {
        assertEquals(expected, day8.getAccumulatorValueAtTermination(filename));
    }

    private static Stream<Arguments> getPart1Arguments() {
        return Stream.of(Arguments.of(5, "example"), Arguments.of(1563, "puzzleinput"));
    }

    private static Stream<Arguments> getPart2Arguments() {
        return Stream.of(Arguments.of(8, "example"), Arguments.of(767, "puzzleinput"));
    }
}
