package nl.maartenwiegers.aoc.y2020;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day7Test {

    private final Day7 day7 = new Day7();

    @ParameterizedTest
    @MethodSource("getPart1Arguments")
    void shouldGetCorrectAnswerPart1(long expected, String filename) {
        assertEquals(expected, day7.getCountBagColorsHoldingAtLeastOneShinyGold(filename));
    }

    @ParameterizedTest
    @MethodSource("getPart2Arguments")
    void shouldGetCorrectAnswerPart2(long expected, String filename) {
        assertEquals(expected, day7.getCountBagsInsideOneShinyGold(filename));
    }

    private static Stream<Arguments> getPart1Arguments() {
        return Stream.of(Arguments.of(4, "example"), Arguments.of(121, "puzzleinput"));
    }

    private static Stream<Arguments> getPart2Arguments() {
        return Stream.of(Arguments.of(32, "example"), Arguments.of(20, "puzzleinput"));
    }
}
