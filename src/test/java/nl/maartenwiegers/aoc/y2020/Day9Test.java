package nl.maartenwiegers.aoc.y2020;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day9Test {

    private final Day9 day9 = new Day9();

    private static Stream<Arguments> getArgumentsPart1() {
        return Stream.of(Arguments.of("example", 5, 127L),
                Arguments.of("puzzleinput", 25, 466456641));
    }

    private static Stream<Arguments> getArgumentsPart2() {
        return Stream.of(Arguments.of("example", 5, 62L),
                Arguments.of("puzzleinput", 25, 55732936L));
    }

    @ParameterizedTest
    @MethodSource("getArgumentsPart1")
    void shouldGetCorrectAnswersPart1(String filename, int preamble, long expected) {
        assertEquals(expected, day9.getFirstNumberNotASumOfPreamble(filename, preamble));
    }

    @ParameterizedTest
    @MethodSource("getArgumentsPart2")
    void shouldGetCorrectAnswersPart2(String filename, int preamble, long expected) {
        assertEquals(expected, day9.getEncryptionWeakness(filename, preamble));
    }
}
