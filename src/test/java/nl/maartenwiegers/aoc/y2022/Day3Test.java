package nl.maartenwiegers.aoc.y2022;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day3Test {

    private final Day3 day3 = new Day3();

    private static Stream<Arguments> getPart1Arguments() {
        return Stream.of(Arguments.of("example", 157), Arguments.of("puzzleinput", 7878));
    }

    private static Stream<Arguments> getPart2Arguments() {
        return Stream.of(Arguments.of("example", 70), Arguments.of("puzzleinput", 2760));
    }

    @ParameterizedTest
    @MethodSource("getPart1Arguments")
    void shouldGetCorrectAnswerPart1(String filename, int expected) {
        assertEquals(expected, day3.getSumOfPriorityItems(filename));
    }

    @ParameterizedTest
    @MethodSource("getPart2Arguments")
    void shouldGetCorrectAnswerPart2(String filename, int expected) {
        assertEquals(expected, day3.getSumOfPrioritiesOfBadges(filename));
    }
}
