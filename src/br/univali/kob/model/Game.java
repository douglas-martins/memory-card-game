package br.univali.kob.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Game {
    private GameCards gameCards;

    private GameDifficulty gameDifficulty;

    private GameState gameState;

    private List<List<Card>> grid;

    private Integer currentTime;

    private Timer timer;

    private List<Card> cardsClicked;

    private Integer rightMoves;

    private Integer wrongMoves;

    private Integer wins;

    private Integer loses;

    public Game(GameDifficulty gameDifficulty) {
        this.wrongMoves = 0;
        this.rightMoves = 0;
        this.wins = 0;
        this.loses = 0;
        this.gameState = GameState.GAME_WAITING_START;
        this.gameDifficulty = gameDifficulty;
        this.gameCards = new GameCards();
        this.cardsClicked = new ArrayList<>();
        this.gameStart();
    }

    public List<List<Card>> getGrid() {
        return grid;
    }

    public List<Card> getCardsClicked() {
        return cardsClicked;
    }

    public GameState getGameState() {
        return gameState;
    }

    public GameDifficulty getGameDifficulty() {
        return gameDifficulty;
    }

    public Integer getCurrentTime() {
        return currentTime;
    }

    public Integer getRightMoves() {
        return rightMoves;
    }

    public Integer getWrongMoves() {
        return wrongMoves;
    }

    public Integer getWins() {
        return wins;
    }

    public Integer getLoses() {
        return loses;
    }

    public void addWrongMoves() {
        this.wrongMoves++;
    }

    public void addRightMoves() {
        this.rightMoves++;
    }

    public void gameStart() {
        this.gameState = GameState.GAME_START;
        this.grid = this.gameCards.generateGrid(this.gameDifficulty);
    }

    public void gameLoop() {
        this.gameState = GameState.GAME_LOOP;
        this.currentTime = this.gameDifficulty.getGameTime();

        this.timer = new Timer("gameTimer");
        this.timer.schedule(new GameTimerTask(), 0, 1000);
    }

    public void gameRestart() {
    }

    private void gameOver(GameState gameResult) {
        // TODO: on view, open modal to show options for restarting and status for the past game, calling gameRestart() function
        this.gameState = gameResult;
        System.out.println("Game Over");
    }

    public void gameExit() {

    }

    private GameState isGameOver() {
        if (this.isTimesUp()) {
            this.loses++;
            return GameState.GAME_PLAYER_LOST;
        }

        if (this.isPlayerFoundAll()) {
            this.wins++;
            return GameState.GAME_PLAYER_WIN;
        }
        return this.gameState;
    }

    public Boolean isTimesUp() {
        return this.currentTime <= 0;
    }

    public Boolean isPlayerFoundAll() {
        long count = 0;
        for (List<Card> row : this.grid) {
            count += row.stream().filter(Card::getShowing).count();
        }

        return count >= this.gameDifficulty.getSize();
    }

    private class GameTimerTask extends TimerTask {
        @Override
        public void run() {
            GameState nextGameState = isGameOver();
            if (nextGameState == GameState.GAME_LOOP) {
                --currentTime;
            } else {
                timer.cancel();
                gameOver(nextGameState);
            }
        }
    }
}
