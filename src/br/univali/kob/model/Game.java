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

    public Game(GameDifficulty gameDifficulty) {
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

    public void gameStart() {
        this.gameState = GameState.GAME_START;
        this.grid = this.gameCards.generateGrid(this.gameDifficulty);
        this.gameLoop();
    }

    public void gameLoop() {
        this.gameState = GameState.GAME_LOOP;
        this.currentTime = this.gameDifficulty.getGameTime();

        this.timer = new Timer("gameTimer");
        this.timer.schedule(new GameTimerTask(), 0, 1000);
    }

    public void gameRestart() {
    }

    public void gameOver() {
        // TODO: on view, open modal to show options for restarting and status for the past game, calling gameRestart() function
        this.gameState = (this.currentTime > 0 ? GameState.GAME_PLAYER_WIN : GameState.GAME_PLAYER_LOST);
        System.out.println("Game Over");
    }

    public void gameExit() {

    }

    public Boolean isGameOver() {
        // TODO: add verify for cards matrix
        return (this.currentTime < 0);
    }

    private class GameTimerTask extends TimerTask {
        @Override
        public void run() {
            if (!isGameOver()) {
                System.out.println("Time: " + ((currentTime % 3600) / 60) + ":" + (currentTime % 60));
                --currentTime;
            } else {
                timer.cancel();
                gameOver();
            }
        }
    }
}
