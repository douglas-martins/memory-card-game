package br.univali.kob.model;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public enum CardType {
    RED(Color.RED),
    GREEN(Color.GREEN),
    BLUE(Color.BLUE),
    YELLOW(Color.YELLOW),
    ORANGE(Color.ORANGE),
    PINK(Color.PINK),
    BLACK(Color.BLACK),
    GREY(Color.GREY),
    PURPLE(Color.PURPLE),
    BROWN(Color.BROWN),
    GOLD(Color.GOLD),
    SALMON(Color.SALMON),
    DARKRED(Color.DARKRED),
    DARKGREEN(Color.DARKGREEN),
    DARKBLUE(Color.DARKBLUE);

    private String name;

    private Color color;

    CardType(Color color) {
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public Color getColor() {
        return color;
    }

    public Circle getVisible() {
        Circle circle = new Circle();
        circle.setCenterX(100.0f);
        circle.setCenterY(100.0f);
        circle.setRadius(50.0f);
        circle.setFill(this.color);
        return circle;
    }

    public Rectangle getHide() {
        Rectangle rectangle = new Rectangle();
        rectangle.setX(150.0f);
        rectangle.setY(75.0f);
        rectangle.setWidth(150.0f);
        rectangle.setHeight(150.0f);
        rectangle.setFill(Color.BLACK);
        return rectangle;
    }
}