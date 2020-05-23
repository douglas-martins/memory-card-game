package br.univali.kob.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public final class GameCards {
    private final Card[] assets;

    public GameCards() {
        this.assets = new Card[]{
                new Card(CardType.RED, true),
                new Card(CardType.GREEN, true),
                new Card(CardType.BLUE, true),
                new Card(CardType.YELLOW, true),
                new Card(CardType.ORANGE, true),
                new Card(CardType.PINK, true),
                new Card(CardType.BLACK, true),
                new Card(CardType.GREY, true),
                new Card(CardType.PURPLE, true),
                new Card(CardType.BROWN, true),
                new Card(CardType.GOLD, true),
                new Card(CardType.SKYBLUE, true),
                new Card(CardType.DARKRED, true),
                new Card(CardType.DARKGREEN, true),
                new Card(CardType.DARKBLUE, true)
        };
        Collections.shuffle(Arrays.asList(this.assets));
    }

    public List<List<Card>> generateGrid(GameDifficulty gameDifficulty) {
        List<List<Card>> grid = new ArrayList<>();

        for (int i = 0; i < gameDifficulty.getRows(); i++) {
            grid.add(new ArrayList<>());
        }

        this.insertGridCards(grid, gameDifficulty);
        this.shuffleGridRow(grid);
        Collections.shuffle(grid);

        return grid;
    }

    private void insertGridCards(List<List<Card>> grid, GameDifficulty gameDifficulty) {
        ListRandomIterator randomRows = new ListRandomIterator(gameDifficulty.getRows());
        List<Card> shuffledCards = this.shuffleCards(gameDifficulty);
        for (Card card : shuffledCards) {
            grid.get(randomRows.getNextElement()).add(card);
            grid.get(randomRows.getNextElement()).add(this.cardDeepCopy(card));
        }
    }

    private List<Card> shuffleCards(GameDifficulty gameDifficulty) {
        Collections.shuffle(Arrays.asList(this.assets));
        return new ArrayList<>(Arrays.asList(this.assets).subList(0, (gameDifficulty.getSize() / 2)));
    }

    private Card cardDeepCopy(Card card) {
        return new Card(card.getCardType(), card.getShowing());
    }

    private void shuffleGridRow(List<List<Card>> grid) {
        for (List<Card> cards : grid) {
            Collections.shuffle(cards);
        }
    }

    private class ListRandomIterator {
        private final List<Object> elements;

        private Integer currentIndex;

        private final int size;

        public ListRandomIterator(int size) {
            this.elements = new ArrayList<>();
            this.size = size;
            this.currentIndex = 0;

            this.initList();
        }

        public Integer getNextElement() {
            if (this.currentIndex >= this.elements.size()) {
                this.resetList();
                return this.getNextElement();
            }
            Integer nextPosition = (Integer) this.elements.get(this.currentIndex);
            this.currentIndex++;
            return nextPosition;
        }

        private void initList() {
            this.elements.clear();
            for (int i = 0; i < this.size; i++) {
                this.elements.add(i);
            }

            Collections.shuffle(this.elements);
        }

        private void resetList() {
            this.initList();
            this.currentIndex = 0;
        }

        @Override
        public String toString() {
            return "ListRandomIterator{" +
                    "elements=" + elements +
                    ", currentIndex=" + currentIndex +
                    ", size=" + size +
                    '}';
        }
    }
}
