package nl.maartenwiegers.aoc.y2022;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day6Test {

    private final Day6 day6 = new Day6();

    private static Stream<Arguments> getArguments() {
        return Stream.of(
                // Part 1
                Arguments.of("example-1", 4, 7),
                Arguments.of("example-2", 4, 5),
                Arguments.of("example-3", 4, 6),
                Arguments.of("example-4", 4, 10),
                Arguments.of("example-5", 4, 11),
                Arguments.of("puzzleinput", 4, 1804),
                // Part 2
                Arguments.of("example-1", 14, 19),
                Arguments.of("example-2", 14, 23),
                Arguments.of("example-3", 14, 23),
                Arguments.of("example-4", 14, 29),
                Arguments.of("example-5", 14, 26),
                Arguments.of("puzzleinput", 14, 2508)
        );
    }

    @ParameterizedTest
    @MethodSource("getArguments")
    void shouldGetCorrectAnswers(String filename, int distinctCharactersRequired, int expected) {
        assertEquals(expected,
                day6.getCharactersProcessBeforeFirstStartOfPacketMarker(filename, distinctCharactersRequired));
    }
}
