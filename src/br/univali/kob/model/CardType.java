package br.univali.kob.model;

public enum CardType {
    RED("RED"),
    GREEN("GREEN"),
    BLUE("BLUE"),
    YELLOW("YELLOW"),
    ORANGE("ORANGE"),
    PINK("PINK"),
    BLACK("BLACK"),
    GREY("GREY"),
    PURPLE("PURPLE"),
    BROWN("BROWN"),
    GOLD("GOLD"),
    SKYBLUE("SKY_BLUE"),
    DARKRED("DARK_RED"),
    DARKGREEN("DARK_GREEN"),
    DARKBLUE("DARK_BLUE");

    private final String name;

    private final String basePath = "br/univali/kob/view/resources/";

    CardType(String name) { this.name = name; }

    public String getVisible() { return this.basePath + this.name.toLowerCase() + ".png"; }

    public String getHide() { return this.basePath + "question_mark.png"; }
}