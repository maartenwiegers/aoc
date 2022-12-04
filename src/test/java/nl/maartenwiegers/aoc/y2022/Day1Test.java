package nl.maartenwiegers.aoc.y2022;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day1Test {

    private final Day1 day1 = new Day1();

    private static Stream<Arguments> getPart1Arguments() {
        return Stream.of(Arguments.of("example", 24000), Arguments.of("puzzleinput", 72602));
    }

    private static Stream<Arguments> getPart2Arguments() {
        return Stream.of(Arguments.of("example", 45000), Arguments.of("puzzleinput", 207410));
    }

    @ParameterizedTest
    @MethodSource("getPart1Arguments")
    void shouldGetCorrectAnswerPart1(String filename, int expected) {
        assertEquals(expected, day1.getTopNMaxCaloriesCarriedByOneElf(filename, 1));
    }

    @ParameterizedTest
    @MethodSource("getPart2Arguments")
    void shouldGetCorrectAnswerPart2(String filename, int expected) {
        assertEquals(expected, day1.getTopNMaxCaloriesCarriedByOneElf(filename, 3));
    }
}
