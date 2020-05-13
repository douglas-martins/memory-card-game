package br.univali.kob.model;

public class Player {
    private Integer rightPlay;

    private Integer wrongPlay;

    private Integer wins;

    private Integer defeats;

    public Integer getRightPlay() {
        return rightPlay;
    }

    public Integer getWrongPlay() {
        return wrongPlay;
    }

    public Integer getWins() {
        return wins;
    }

    public Integer getDefeats() {
        return defeats;
    }

    public void addPlay(boolean isRight) {
        if (isRight) {
            this.rightPlay++;
        } else {
            this.wrongPlay++;
        }
    }

    public void addGameResult(boolean win) {
        if (win) {
            this.wins++;
        } else {
            this.defeats++;
        }
    }
}
