package br.univali.kob.model;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

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

    CardType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getVisible() {
        return "br/univali/kob/view/resources/" + this.name.toLowerCase() + ".png";
    }

    public String getHide() {
        return "br/univali/kob/view/resources/question_mark.png";
    }
}