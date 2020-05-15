package br.univali.kob.view;

import br.univali.kob.model.Card;
import br.univali.kob.model.Game;
import br.univali.kob.model.GameDifficulty;
import br.univali.kob.model.GameState;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Timer;
import java.util.TimerTask;

public class MemoryGameView {
    private Stage stage;

    private Scene scene;

    private BorderPane borderPane;

    private Text timerText;

    private Game game;

    public MemoryGameView(Stage stage, GameDifficulty gameDifficulty) {
        this.stage = stage;
        this.game = new Game(gameDifficulty);
        this.timerText = new Text("");

        this.borderPane = new BorderPane();
        this.scene = new Scene(this.borderPane,1500, 1550);
        this.borderPane.setStyle("-fx-background-color: lightgray;");
        this.borderPane.setTop(this.drawTimer());
        this.borderPane.setCenter(this.drawCardsGrid());
        this.borderPane.setBottom(this.drawGameStatus());

        this.initGameStartCountdown();
    }

    public Scene getScene() {
        return scene;
    }

    public BorderPane getBorderPane() {
        return borderPane;
    }

    private HBox drawTimer() {
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.TOP_CENTER);
        hBox.setSpacing(10);
        hBox.setPadding(new Insets(15, 12,15,12));
        hBox.setStyle("-fx-border-color: darkgray; -fx-border-style: solid inside; -fx-border-width: 3; -fx-border-insets: 3; -fx-border-radius: 2;");

        hBox.getChildren().addAll(this.timerText);
        return hBox;
    }

    private Parent drawCardsGrid() {
        GridPane gridPane = new GridPane();

        for (int i = 0; i < this.game.getGrid().size(); i++) {
            for (int j = 0; j < this.game.getGrid().get(i).size(); j++) {
                Card card = this.game.getGrid().get(i).get(j);
                System.out.println(card.getCardType().getHide());
                System.out.println(card.getImagePathToDraw());
                Image image = new Image(card.getImagePathToDraw(), 150, 150, false, false);
                ImageView imageView = new ImageView(image);
                imageView.addEventHandler(MouseEvent.MOUSE_CLICKED, new CardEventHandler());

                gridPane.add(imageView, i, j);
            }
        }

        gridPane.setAlignment(Pos.CENTER);
        gridPane.setGridLinesVisible(true);
        gridPane.setHgap(1);
        gridPane.setVgap(1);

        return gridPane;
    }

    private HBox drawGameStatus() {
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.BOTTOM_CENTER);
        hBox.setSpacing(10);
        hBox.setPadding(new Insets(15, 12,15,12));
        hBox.setStyle("-fx-border-color: darkgray; -fx-border-style: solid inside; -fx-border-width: 3; -fx-border-insets: 3; -fx-border-radius: 2;");

//        hBox.getChildren().addAll(this.timerText);
        return hBox;
    }

    private void initGameStartCountdown() {
        Timer timer = new Timer();
        timer.schedule(new BeginGameTimerTask(), 0, 1000);
    }

    private String getGameTimeString(Integer currentTime) {
        return ((currentTime % 3600) / 60) + ":" + (currentTime % 60);
    }

    private class BeginGameTimerTask extends TimerTask {
        private Integer time = 3;

        @Override
        public void run() {
            if (time > 0) {
                if (game.getGameState() == GameState.GAME_LOOP) {
                    // TODO: update game status
                    timerText.setText(getGameTimeString(game.getCurrentTime()));
                } else {
                    timerText.setText(String.valueOf(time % 60));
                }
                --time;
            } else {
                if (game.getGameState() != GameState.GAME_PLAYER_LOST || game.getGameState() != GameState.GAME_PLAYER_WIN) {
                    time = game.getGameDifficulty().getGameTime();
                    game.gameStart();
                    timerText.setText("Já!");
                }
            }
        }
    }

    private class CardEventHandler implements EventHandler<Event> {
        @Override
        public void handle(Event event) {
            // TODO: add handle for clicking on cards
            if (game.isGameOver()) {
                return;
            }

            Node node = (Node) event.getTarget();

            System.out.println(event.getEventType());
            System.out.println(event.getSource());
            System.out.println(event.getTarget());

            if (game.getCardsClicked().size() < 2) {
                Integer row = GridPane.getRowIndex(node);
                Integer column = GridPane.getColumnIndex(node);

                ImageView imageView = (ImageView) event.getSource();
                Card card = game.getGrid().get(row).get(column);
                card.setShowing(true);
                Image image = new Image(card.getImagePathToDraw(), 150, 150, false, false);

                imageView.setImage(image);
            }
        }
    }


}
