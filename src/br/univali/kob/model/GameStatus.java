package br.univali.kob.model;

import java.util.Objects;

public class GameStatus {
    private Integer rightMoves;

    private Integer wrongMoves;

    private Integer wins;

    private Integer loses;

    public GameStatus() {
        this.resetValues();
    }

    public GameStatus(GameStatus gameStatus) {
        this.wins = gameStatus.getWins();
        this.loses = gameStatus.getLoses();
    }

    public GameStatus(GameStatus gameStatus, boolean restart) {
        this(gameStatus);
        if (restart) {
            this.gameRestart();
        } else {
            this.rightMoves = gameStatus.getRightMoves();
            this.wrongMoves = gameStatus.getWrongMoves();
        }
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

    public void addRightMove() {
        this.rightMoves++;
    }

    public void addWrongMove() {
        this.wrongMoves++;
    }

    public void addWin() {
        this.wins++;
    }

    public void addLoses() {
        this.loses++;
    }

    public Integer totalMoves() {
        return (this.rightMoves + this.wrongMoves);
    }

    public void resetValues() {
        this.gameRestart();
        this.wins = 0;
        this.loses = 0;
    }

    public void gameRestart() {
        this.rightMoves = 0;
        this.wrongMoves = 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameStatus that = (GameStatus) o;
        return Objects.equals(rightMoves, that.rightMoves) &&
                Objects.equals(wrongMoves, that.wrongMoves) &&
                Objects.equals(wins, that.wins) &&
                Objects.equals(loses, that.loses);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rightMoves, wrongMoves, wins, loses);
    }

    @Override
    public String toString() {
        return "GameStatus{" +
                "rightMoves=" + rightMoves +
                ", wrongMoves=" + wrongMoves +
                ", wins=" + wins +
                ", loses=" + loses +
                '}';
    }
}
