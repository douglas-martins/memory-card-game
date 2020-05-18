package br.univali.kob.model;

import java.util.*;

public final class GameCards {
    private final Card[] assets;

    public GameCards() {
        this.assets = new Card[]{
                new Card(CardType.RED),
                new Card(CardType.GREEN),
                new Card(CardType.BLUE),
                new Card(CardType.YELLOW),
                new Card(CardType.ORANGE),
                new Card(CardType.PINK),
                new Card(CardType.BLACK),
                new Card(CardType.GREY),
                new Card(CardType.PURPLE),
                new Card(CardType.BROWN),
                new Card(CardType.GOLD),
                new Card(CardType.SKYBLUE),
                new Card(CardType.DARKRED),
                new Card(CardType.DARKGREEN),
                new Card(CardType.DARKBLUE)
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
        return new Card(card.getCardType());
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
