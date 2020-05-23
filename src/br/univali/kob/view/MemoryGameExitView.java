package br.univali.kob.view;

import br.univali.kob.model.GameStatus;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Timer;
import java.util.TimerTask;

public class MemoryGameExitView {
    private Stage stage;

    private Scene scene;

    private BorderPane borderPane;

    public MemoryGameExitView(Stage stage, GameStatus gameStatus) {
        this.stage = stage;
        this.borderPane = new BorderPane();
        this.scene = new Scene(this.borderPane,1500, 1550);
        this.borderPane.setCenter(this.drawGameStatus(gameStatus));
        this.borderPane.setStyle("-fx-background-color: ghostwhite;");

        Timer timer = new Timer();
        timer.schedule(new GameExitTimerTask(), 0, 1000);
    }

    public Scene getScene() {
        return scene;
    }

    private HBox drawGameStatus(GameStatus gameStatus) {
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER);
        hBox.setSpacing(10);
        hBox.setPadding(new Insets(15, 12,15,12));

        Text gameStatusText = new Text("\n\nVitórias: " + gameStatus.getWins() + "\nDerrotas: " + gameStatus.getLoses());
        gameStatusText.setFont(Font.font("Abyssinica SIL", FontWeight.BOLD, FontPosture.REGULAR,50));
        gameStatusText.setFill(Color.BLUE);
        gameStatusText.setStroke(Color.BLACK);
        gameStatusText.setStrokeWidth(1);

        hBox.getChildren().addAll(gameStatusText);
        return hBox;
    }

    private class GameExitTimerTask extends TimerTask {
        private Integer time = 3;

        @Override
        public void run() {
            if (time > 0) {
                --time;
            } else {
                this.cancel();
                Platform.runLater(() -> {
                    System.exit(0);
                    stage.close();
                    Platform.exit();
                });
            }
        }
    }
}
