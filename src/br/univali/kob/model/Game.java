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

    private GameStatus gameStatus;

    public Game(GameDifficulty gameDifficulty) {
        this.gameStatus = new GameStatus();
        this.gameInitValues(gameDifficulty);
    }

    public Game(GameDifficulty gameDifficulty, GameStatus gameStatus) {
        this.gameInitValues(gameDifficulty, gameStatus);
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

    public GameStatus getGameStatus() { return gameStatus; }

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

    private void gameInitValues(GameDifficulty gameDifficulty, GameStatus gameStatus) {
        this.gameStatus = new GameStatus(gameStatus, true);
        this.gameInitValues(gameDifficulty);
    }

    private void gameInitValues(GameDifficulty gameDifficulty) {
        this.gameState = GameState.GAME_WAITING_START;
        this.gameDifficulty = gameDifficulty;
        this.gameCards = new GameCards();
        this.cardsClicked = new ArrayList<>();
        this.gameStart();
    }

    private GameState isGameOver() {
        if (this.isTimesUp()) {
            this.gameStatus.addLost();
            return GameState.GAME_PLAYER_LOST;
        }

        if (this.isPlayerFoundAll()) {
            this.gameStatus.addWin();
            return GameState.GAME_PLAYER_WIN;
        }
        return this.gameState;
    }

    private Boolean isTimesUp() {
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
                gameState = nextGameState;
            }
        }
    }
}
