package ex.code;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class Solution {
    public static void main(String[] args) {

        Generator generator = new Generator();

        //Task 5
        System.out.println("\n-------------\nPre-match games: ");
        var names = generator
                .getGames()
                .stream()
                .filter(game -> !game.isStarted())
                .map(Game::getName)
                .collect(Collectors.toList());
                System.out.println(names);

        //Task 6
        System.out.println("\n-------------\nGames grouped by sport: ");
        Map<String, List<String>> groupByPriceMap = generator
                .getGames()
                .stream()
                .collect(Collectors.groupingBy(Game::getSport, Collectors.mapping(Game::getName, Collectors.toList())));
                System.out.println(groupByPriceMap);

        //Task 7
        System.out.println("\n-------------\nIn-Play games with in-play markets: ");
        var inPlayGamesWithInPlayMarkets = generator
                .getGames()
                .stream()
                .filter(Game::isStarted)
                .map(game -> {
                    Map<String, List<String>> hm = new HashMap<>();
                    List<String> marketTmp = game.getMarkets().stream().filter(Market::isStarted).map(Market::getName).collect(Collectors.toList());
                    hm.put(game.getName(), marketTmp);
                    return hm;
                })
                .collect(Collectors.toList());
                System.out.println(inPlayGamesWithInPlayMarkets);

        //Task 8
        System.out.println(generator.wordExists("championshipfinals", names));

        //Task 9
        System.out.println(generator.wordExists("playoff", names));
    }
}

/**
 * Task 1
 */
class Game {
    private final String sport;
    private final String name;
    private final boolean started;
    private final List<Market> markets;

    public Game(String sport, String name, boolean started, List<Market> markets) {
        this.sport = sport;
        this.name = name;
        this.started = started;
        this.markets = markets;
    }

    public String getSport() {
        return sport;
    }

    public String getName() {
        return name;
    }

    public boolean isStarted() {
        return started;
    }

    public List<Market> getMarkets() {
        return markets;
    }
}

/**
 * Task 2
 */
class Market {
    private final String name;
    private final boolean started;

    public Market(String name, boolean started) {
        this.name = name;
        this.started = started;
    }

    public String getName() {
        return name;
    }

    public boolean isStarted() {
        return started;
    }
}

class Generator {
    private static final boolean IN_PLAY = true;
    private static final boolean PRE_MATCH = false;
    private final List<Game> games = new ArrayList<>();

    public Generator() {
        //Task 3
        List<Market> markets = new ArrayList<>();
        markets.add(new Market("End Result", PRE_MATCH));
        markets.add(new Market("End Result Live", IN_PLAY));
        markets.add(new Market("Total Goals Live", IN_PLAY));
        //Task 4
        this.games.add(new Game("soccer", "championship", PRE_MATCH, markets));
        this.games.add(new Game("soccer", "playoff", IN_PLAY, markets));
        this.games.add(new Game("soccer", "tryout", PRE_MATCH, markets));
        this.games.add(new Game("basketball", "foobar", PRE_MATCH, markets));
        this.games.add(new Game("baseball", "finals", PRE_MATCH, markets));
    }

    public List<Game> getGames() {
        return games;
    }

    //Task 8, 9 and 10
    public boolean wordExists(String word, List<String> names) {
        for (String s :names) {
            if (word.contains(s)) {
                return true;
            }
        }
        return false;
    }
}