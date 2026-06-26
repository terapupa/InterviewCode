package ex.code;

import java.util.List;

public class Example3 {

    public enum SportEnum {
        soccer, baseball, basketball
    }

    public static void main(String[] args) {

    }

    private class Game {
        private String sport;
        private String name;
        private boolean started;
        private List<Market> markets;

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

    private class Market {
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

}
