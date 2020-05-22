package br.univali.kob.view;

import br.univali.kob.model.Game;
import br.univali.kob.model.GameDifficulty;
import br.univali.kob.model.GameStatus;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MemoryGameOverModal {
    private Stage stage;

    private Stage dialog;

    private Scene scene;

    private BorderPane borderPane;

    private GameDifficulty gameDifficulty;

    private GameStatus gameStatus;

    public MemoryGameOverModal(Stage stage, Game game) {
        this.dialog = new Stage();
        this.stage = stage;
        this.gameStatus = new GameStatus(game.getGameStatus(), false);
        this.gameDifficulty = game.getGameDifficulty();
        this.borderPane = new BorderPane();
        this.borderPane.setCenter(this.drawMessage());
        this.borderPane.setBottom(this.drawVBox());
        this.borderPane.setStyle("-fx-background-color: ghostwhite;");

        this.scene = new Scene(this.borderPane,500, 550);
        this.drawModal();
    }

    public Scene getScene() {
        return scene;
    }

    private void drawModal() {
        this.dialog.initModality(Modality.APPLICATION_MODAL);
        this.dialog.initOwner(stage);
        this.dialog.setScene(this.scene);
        this.dialog.show();
    }

    private VBox drawVBox() {
        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(10);
        vBox.setPadding(new Insets(15, 12,15,12));
        vBox.setStyle("-fx-border-color: darkblue; -fx-border-style: solid inside; -fx-border-width: 3; -fx-border-insets: 3; -fx-border-radius: 2;");

        vBox.getChildren().addAll(this.drawGameRestartButton(), this.drawGameMenuButton(), this.drawGameExitButton());
        return vBox;
    }

    private Text drawMessage() {
        Text gameStatusText = new Text("Fim de Jogo\n\nAcertos: " + this.gameStatus.getRightMoves()
                + "\nErros: " + this.gameStatus.getWrongMoves()
                + "\nTotal de jogadas: " + this.gameStatus.totalMoves());
        gameStatusText.setFont(Font.font("Abyssinica SIL", FontWeight.BOLD, FontPosture.REGULAR,25));
        gameStatusText.setFill(Color.BLUE);
        gameStatusText.setStroke(Color.BLACK);
        gameStatusText.setStrokeWidth(1);

        return gameStatusText;
    }

    private Button drawGameRestartButton() {
        Button normalDifficultyButton = new Button("Jogar novamente");
        normalDifficultyButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new GameRestartButtonEventHandler());
        normalDifficultyButton.setPrefSize(150, 20);
        return normalDifficultyButton;
    }

    private Button drawGameExitButton() {
        Button hardDifficultyButton = new Button("Sair");
        hardDifficultyButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new GameExitButtonEventHandler());
        hardDifficultyButton.setPrefSize(150, 20);
        return hardDifficultyButton;
    }

    private Button drawGameMenuButton() {
        Button menuButton = new Button("Menu");
        menuButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new GameMenuButtonEventHandler());
        menuButton.setPrefSize(150, 20);
        return menuButton;
    }

    private class GameRestartButtonEventHandler implements EventHandler<Event> {
        @Override
        public void handle(Event event) {
            stage.setScene(new MemoryGameView(stage, gameDifficulty, new GameStatus(gameStatus)).getScene());
            stage.hide();
            dialog.close();
            stage.show();
        }
    }

    private class GameExitButtonEventHandler implements EventHandler<Event> {
        @Override
        public void handle(Event event) {
            stage.setScene(new MemoryGameExitView(stage, new GameStatus(gameStatus)).getScene());
            stage.hide();
            dialog.close();
            stage.show();
        }
    }

    private class GameMenuButtonEventHandler implements EventHandler<Event> {
        @Override
        public void handle(Event event) {
            stage.setScene(new MemoryGameMenuView(stage, new GameStatus(gameStatus)).getScene());
            stage.hide();
            dialog.close();
            stage.show();
        }
    }
}
