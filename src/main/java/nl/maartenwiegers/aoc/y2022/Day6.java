package nl.maartenwiegers.aoc.y2022;

import nl.maartenwiegers.aoc.commons.FileService;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;

public class Day6 {

    private static final String INPUT_FILE_NAME = "input/y2022/06-%s.txt";

    public int getCharactersProcessBeforeFirstStartOfPacketMarker(String filename, int distinctCharactersRequired) {
        String input = FileService.getSingleLineInput(String.format(INPUT_FILE_NAME, filename));

        for (int i = distinctCharactersRequired; i < input.length(); i++) {
            String previousFourChars = input.substring(i - distinctCharactersRequired, i);

            if (Arrays.stream(StringUtils.toCodePoints(previousFourChars)).distinct()
                    .count() == distinctCharactersRequired) {
                return i;
            }
        }

        return 0;
    }
}
