package nl.maartenwiegers.aoc.y2022;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day5Test {

    private final Day5 day5 = new Day5();

    private static Stream<Arguments> getArgumentsPart1() {
        return Stream.of(Arguments.of("example", "CMZ"), Arguments.of("puzzleinput", "GFTNRBZPF"));
    }

    private static Stream<Arguments> getArgumentsPart2() {
        return Stream.of(Arguments.of("example", "MCD"), Arguments.of("puzzleinput", "VRQWPDSGP"));
    }

    @ParameterizedTest
    @MethodSource("getArgumentsPart1")
    void shouldGetCorrectAnswersPart1(String filename, String expected) {
        assertEquals(expected, day5.getCratesAtTopPart1(filename));
    }

    @ParameterizedTest
    @MethodSource("getArgumentsPart2")
    void shouldGetCorrectAnswersPart2(String filename, String expected) {
        assertEquals(expected, day5.getCratesAtTopPart2(filename));
    }
}
