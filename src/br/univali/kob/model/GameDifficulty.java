package br.univali.kob.model;

public enum GameDifficulty {
    NORMAL(20, 4, 210),
    HARD(30, 6, 300);

    private final Integer size;

    private final Integer rows;

    private final Integer gameTime;

    GameDifficulty(Integer size, Integer rows, Integer gameTime) {
        this.size = size;
        this.rows = rows;
        this.gameTime = gameTime;
    }

    public Integer getSize() {
        return size;
    }

    public Integer getRows() {
        return rows;
    }

    public Integer getGameTime() { return gameTime; }
}
