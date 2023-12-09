package nl.maartenwiegers.aoc.y2023;

import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import nl.maartenwiegers.aoc.commons.FileService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
public class Day2 {

    private static final String INPUT_FILE_NAME = "input/y2023/02-%s.txt";
    List<Game> gameList = new ArrayList<>();

    public long getSumOfPossibleIds(String filename) {
        initializeGames(filename);
        return gameList.stream()
                .filter(game -> game.highestNumOfRed <= 12)
                .filter(game -> game.highestNumOfGreen <= 13)
                .filter(game -> game.highestNumOfBlue <= 14)
                .mapToInt(Game::getId)
                .sum();
    }

    public long getPowers(String filename) {
        initializeGames(filename);
        return gameList.stream()
                .mapToInt(game -> game.getHighestNumOfBlue() * game.getHighestNumOfRed() * game.getHighestNumOfGreen())
                .sum();
    }

    private void initializeGames(String filename) {
        List<String> rawGames = FileService.getInputAsListString(String.format(INPUT_FILE_NAME, filename));
        rawGames.forEach(game -> {
            List<Hand> hands = new ArrayList<>();
            int id = Integer.parseInt(game.split(":")[0].replace("Game ", ""));
            log.debug("ID: {}", id);
            Arrays.stream(game.split(":")[1].trim().split(";")).forEach(hand -> {
                log.debug("Hand: {}", hand);
                Arrays.stream(hand.split(",")).forEach(subHand -> {
                    log.debug("Subhand: {}", subHand);
                    hands.add(Hand.builder().count(Integer.parseInt(subHand.trim().split(" ")[0])).color(subHand.trim().split(" ")[1]).build());
                });
            });
            gameList.add(Game.builder()
                    .id(id)
                    .highestNumOfBlue(hands.stream().filter(hand -> "blue".equals(hand.color)).mapToInt(Hand::getCount).max().getAsInt())
                    .highestNumOfRed(hands.stream().filter(hand -> "red".equals(hand.color)).mapToInt(Hand::getCount).max().getAsInt())
                    .highestNumOfGreen(hands.stream().filter(hand -> "green".equals(hand.color)).mapToInt(Hand::getCount).max().getAsInt())
                    .build());
        });
    }


    @Builder
    @Data
    private static class Game {
        int id;
        @Builder.Default
        int highestNumOfBlue = 0;
        @Builder.Default
        int highestNumOfRed = 0;
        @Builder.Default
        int highestNumOfGreen = 0;
    }

    @Builder
    @Data
    private static class Hand {
        int count;
        String color;
    }
}
