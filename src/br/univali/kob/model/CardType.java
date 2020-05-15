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
    BLACK("SKYBLUE"),
    GREY("GREY"),
    PURPLE("PURPLE"),
    BROWN("BROWN"),
    GOLD("GOLD"),
    SALMON("SKY_BLUE"),
    DARKRED("DARK_RED"),
    DARKGREEN("DARK_GREEN"),
    DARKBLUE("DARK_BLUE");

    private String name;

    CardType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getVisible() {
//        Circle circle = new Circle();
//        circle.setCenterX(100.0f);
//        circle.setCenterY(100.0f);
//        circle.setRadius(50.0f);
//        circle.setFill(this.color);
//        return circle;
        return "br/univali/kob/view/resources/" + this.name.toLowerCase() + ".png";
    }

    public String getHide() {
//        Rectangle rectangle = new Rectangle();
//        rectangle.setX(150.0f);
//        rectangle.setY(75.0f);
//        rectangle.setWidth(150.0f);
//        rectangle.setHeight(150.0f);
//        rectangle.setFill(Color.BLACK);
//        return rectangle;
        return "br/univali/kob/view/resources/question_mark.png";
    }
}