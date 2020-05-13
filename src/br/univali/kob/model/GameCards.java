package br.univali.kob.model;

import javafx.scene.shape.Circle;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public final class GameCards {
    private final Card[] assets;

    private final int ROW_SIZE = 5;

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
                new Card(CardType.SALMON),
                new Card(CardType.DARKRED),
                new Card(CardType.DARKGREEN),
                new Card(CardType.DARKBLUE)
        };
        Collections.shuffle(Arrays.asList(this.assets));
    }

    public List<List<Card>> generateGrid(GameDifficulty gameDifficulty) {
        List<List<Card>> grid = new ArrayList<>();

        for (int i = 0; i < ROW_SIZE; i++) {
            grid.add(new ArrayList<>());
        }

        int[] assetsPositions = ThreadLocalRandom.current()
                .ints(0, this.assets.length)
                .distinct()
                .limit((gameDifficulty.getSize() / 2))
                .toArray();

        this.insertGridCards(grid, assetsPositions);
        this.shuffleGridRow(grid);
        Collections.shuffle(grid);

        return grid;
    }

    private void insertGridCards(List<List<Card>> grid, int[] assetsPositions) {
        int row = 0;
        for (int i = 0; i < assetsPositions.length; i++) {
            grid.get(row).add(this.assets[i]);
            grid.get(row).add(this.cardDeepCopy(this.assets[i]));

            if (row < grid.size() - 1) {
                row++;
            } else {
                row = 0;
            }
        }
    }

    private Card cardDeepCopy(Card card) {
        return new Card(card.getCardType());
    }

    private void shuffleGridRow(List<List<Card>> grid) {
        for (List<Card> cards : grid) {
            Collections.shuffle(cards);
        }
    }
}
