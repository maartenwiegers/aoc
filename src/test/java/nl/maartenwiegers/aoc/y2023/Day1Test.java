package nl.maartenwiegers.aoc.y2023;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day1Test {

    private final Day1 day1 = new Day1();

    @ParameterizedTest
    @MethodSource("getArguments")
    void shouldGetCorrectAnswers(long expected, String filename, boolean replaceWordsToDigits) {
        assertEquals(expected, day1.getSumOfCalibrationValues(filename, replaceWordsToDigits));
    }


    static Stream<Arguments> getArguments() {
        return Stream.of(
          Arguments.of(142, "example-1", false),
                Arguments.of(54953, "puzzleinput", false),
                Arguments.of(281, "example-2", true),
                Arguments.of(53868, "puzzleinput", true)
        );
    }
}
