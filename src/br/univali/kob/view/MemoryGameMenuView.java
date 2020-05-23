package br.univali.kob.view;

import br.univali.kob.model.GameStatus;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class MemoryGameMenuView {
    private Stage stage;

    private Scene scene;

    private BorderPane borderPane;

    private GameStatus gameStatus;

    public MemoryGameMenuView(Stage stage) {
        this.initValues(stage);
    }

    public MemoryGameMenuView(Stage stage, GameStatus gameStatus) {
        this.initValues(stage);
        this.gameStatus = new GameStatus(gameStatus);
    }

    public Scene getScene() {
        return scene;
    }

    private void initValues(Stage stage) {
        this.stage = stage;
        this.borderPane = new BorderPane();
        this.scene = new Scene(this.borderPane,1500, 1550);
        this.borderPane.setCenter(this.drawHBoxGameInfo());
        this.borderPane.setBottom(this.drawHBoxActions());
        this.borderPane.setStyle("-fx-background-color: ghostwhite;");
    }

    private HBox drawHBoxActions() {
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER);
        hBox.setSpacing(10);
        hBox.setPadding(new Insets(15, 12,15,12));
        hBox.setStyle("-fx-border-color: darkblue; -fx-border-style: solid inside; -fx-border-width: 3; -fx-border-insets: 3; -fx-border-radius: 2;");

        hBox.getChildren().addAll(this.drawNewGameButton(), this.drawExitGameButton());
        return hBox;
    }

    private HBox drawHBoxGameInfo() {
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER);
        hBox.setSpacing(10);
        hBox.setPadding(new Insets(15, 12,15,12));

        Text infoText = new Text("Jogo da Memória\n\nFeito por: Douglas Martins");
        infoText.setFont(Font.font("Abyssinica SIL", FontWeight.BOLD, FontPosture.REGULAR,25));
        infoText.setFill(Color.BLUE);
        infoText.setStroke(Color.BLACK);
        infoText.setStrokeWidth(1);

        hBox.getChildren().addAll(infoText);
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
        exitGameButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new ExitGameButtonEventHandler());
        exitGameButton.setPrefSize(100, 20);
        return exitGameButton;
    }

    private class NewGameButtonEventHandler implements EventHandler<Event> {
        @Override
        public void handle(Event event) {
            if (gameStatus != null) {
                new MemoryGameDifficultyModalView(stage, new GameStatus(gameStatus));
            } else {
                new MemoryGameDifficultyModalView(stage);
            }
        }
    }

    private class ExitGameButtonEventHandler implements EventHandler<Event> {
        @Override
        public void handle(Event event) {
            Platform.runLater(() -> {
                System.exit(0);
                stage.close();
                Platform.exit();
            });
        }
    }
}
