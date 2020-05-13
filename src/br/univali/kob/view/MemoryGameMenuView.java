package br.univali.kob.view;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;


public class MemoryGameMenuView {
    private Stage stage;

    private Scene scene;

    private BorderPane borderPane;

    public MemoryGameMenuView(Stage stage) {
        this.stage = stage;
        this.borderPane = new BorderPane();
        this.scene = new Scene(this.borderPane,500, 550);
        this.borderPane.setBottom(this.drawHBox());
        this.borderPane.setStyle("-fx-background-color: lightgray;");
    }

    public Scene getScene() {
        return scene;
    }

    public BorderPane getBorderPane() {
        return borderPane;
    }

    private HBox drawHBox() {
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER);
        hBox.setSpacing(10);
        hBox.setPadding(new Insets(15, 12,15,12));
        hBox.setStyle("-fx-border-color: darkgray; -fx-border-style: solid inside; -fx-border-width: 3; -fx-border-insets: 3; -fx-border-radius: 2;");

        hBox.getChildren().addAll(this.drawNewGameButton(), this.drawExitGameButton());
        return hBox;
    }

    private Button drawNewGameButton() {
        Button newGameButton = new Button("Novo Jogo");
        newGameButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new NewGameButtonEventHandler());
        newGameButton.setPrefSize(100, 20);
        return newGameButton;
    }

    private Button drawExitGameButton() {
        Button exitGameButton = new Button("Sair");
        exitGameButton.setPrefSize(100, 20);
        return exitGameButton;
    }

    private class NewGameButtonEventHandler implements EventHandler<Event> {
        @Override
        public void handle(Event event) {
            new MemoryGameDifficultyModalView(stage);
        }
    }
}
