package nl.maartenwiegers.aoc.y2022;

import lombok.extern.slf4j.Slf4j;
import nl.maartenwiegers.aoc.commons.FileService;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class Day2 {

    private static final String INPUT_FILE_NAME = "input/y2022/02-%s.txt";
    private static final Map<RockPaperScissors, Integer> POINTS_FOR_SHAPE = Map.of(RockPaperScissors.ROCK, 1, RockPaperScissors.PAPER, 2, RockPaperScissors.SCISSORS, 3);
    private static final Map<String, RockPaperScissors> SHAPE_TO_PLAY_FOR_OUTCOME = Map.of("A X", RockPaperScissors.SCISSORS, "A Y", RockPaperScissors.ROCK, "A Z", RockPaperScissors.PAPER, "B X", RockPaperScissors.ROCK, "B Y", RockPaperScissors.PAPER, "B Z", RockPaperScissors.SCISSORS, "C X", RockPaperScissors.PAPER, "C Y", RockPaperScissors.SCISSORS, "C Z", RockPaperScissors.ROCK);

    public int getScore(String filename, int part) {
        List<String> plays = FileService.getInputAsListString(String.format(INPUT_FILE_NAME, filename));
        AtomicInteger score = new AtomicInteger();
        plays.forEach(play -> {
            String[] hands = StringUtils.split(play);
            RockPaperScissors opponentPlay = RockPaperScissors.fromValue(hands[0]);
            RockPaperScissors myPlay = part == 1 ? RockPaperScissors.fromValue(hands[1]) : SHAPE_TO_PLAY_FOR_OUTCOME.get(play);
            score.getAndAdd(getMyScoreFromPlay(opponentPlay, myPlay));
        });

        return score.get();
    }

    private int getMyScoreFromPlay(RockPaperScissors opponentPlay, RockPaperScissors myPlay) {
        return getPointsFromWinDrawOrLose(opponentPlay, myPlay) + getPointsForShape(myPlay);
    }

    private int getPointsFromWinDrawOrLose(RockPaperScissors opponentPlay, RockPaperScissors myPlay) {
        if (opponentPlay == myPlay) {
            return 3; // Draw
        }
        if (isWonByMe(opponentPlay, myPlay)) {
            return 6;
        }
        return 0; // Lost
    }

    private int getPointsForShape(RockPaperScissors myPlay) {
        return POINTS_FOR_SHAPE.get(myPlay);
    }

    private boolean isWonByMe(RockPaperScissors opponentPlay, RockPaperScissors myPlay) {
        return (myPlay == RockPaperScissors.ROCK && opponentPlay == RockPaperScissors.SCISSORS) || (myPlay == RockPaperScissors.PAPER && opponentPlay == RockPaperScissors.ROCK) || (myPlay == RockPaperScissors.SCISSORS && opponentPlay == RockPaperScissors.PAPER);
    }

    public enum RockPaperScissors {
        ROCK, PAPER, SCISSORS;

        public static RockPaperScissors fromValue(String value) {
            return switch (value) {
                case "A", "X" -> ROCK;
                case "B", "Y" -> PAPER;
                case "C", "Z" -> SCISSORS;
                default -> throw new IllegalArgumentException();
            };
        }
    }
}
