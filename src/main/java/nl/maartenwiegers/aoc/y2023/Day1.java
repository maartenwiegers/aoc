package nl.maartenwiegers.aoc.y2023;

import lombok.extern.slf4j.Slf4j;
import nl.maartenwiegers.aoc.commons.FileService;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class Day1 {

    private static final String INPUT_FILE_NAME = "input/y2023/01-%s.txt";
    private static final Map<String, String> WORD_TO_DIGIT_REPLACEMENTS = Map.of("one",
            "1",
            "two",
            "2",
            "three",
            "3",
            "four",
            "4",
            "five",
            "5",
            "six",
            "6",
            "seven",
            "7",
            "eight",
            "8",
            "nine",
            "9");

    private static final String[] FIND = WORD_TO_DIGIT_REPLACEMENTS.keySet().toArray(new String[0]);
    private static final String[] REPLACE = WORD_TO_DIGIT_REPLACEMENTS.values().toArray(new String[0]);
    private final Pattern NUMBERS =
            Pattern.compile("(1|2|3|4|5|6|7|8|9|one|two|three|four|five|six|seven|eight|nine)");

    public long getSumOfCalibrationValues(String filename, boolean replaceWordsToDigits) {
        List<String> values = FileService.getInputAsListString(String.format(INPUT_FILE_NAME, filename));
        AtomicLong counter = new AtomicLong();

        values.forEach(v -> {
            AtomicReference<String> value = new AtomicReference<>(v);
            String firstDigit = StringUtils.left(StringUtils.getDigits(value.get()), 1);
            String lastDigit = StringUtils.right(StringUtils.getDigits(value.get()), 1);
            if (replaceWordsToDigits) {
                ArrayList<String> numbers = new ArrayList<>();
                Matcher matcher = NUMBERS.matcher(v);
                int position = 0;
                while (matcher.find(position)) {
                    numbers.add(matcher.group(1));
                    position = matcher.start() + 1;
                }
                firstDigit = StringUtils.replaceEach(numbers.get(0), FIND, REPLACE);
                lastDigit = StringUtils.replaceEach(numbers.get(numbers.size() -1), FIND, REPLACE);
            }

            String valueNumber = firstDigit.concat(lastDigit);
            log.debug("In line {}, converted to {}, found value {}", v, value.get(), valueNumber);
            counter.getAndAdd(Long.parseLong(valueNumber));
        });

        return counter.get();
    }
}
