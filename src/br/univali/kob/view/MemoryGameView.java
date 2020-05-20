package br.univali.kob.view;

import br.univali.kob.model.*;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
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
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MemoryGameView {
    private Stage stage;

    private Scene scene;

    private BorderPane borderPane;

    private Text timerText;

    private Text actionResultText;

    private Text wrongMovesText;

    private Text rightMovesText;

    private Game game;

    private Timer timer;

    private List<ImageView> imageViewClicked;

    private final int IMG_SIZE_WITH_HEIGHT = 150;

    public MemoryGameView(Stage stage, GameDifficulty gameDifficulty) {
        this.game = new Game(gameDifficulty);
        this.initValues(stage);
    }

    public MemoryGameView(Stage stage, GameDifficulty gameDifficulty, GameStatus gameStatus) {
        this.game = new Game(gameDifficulty, gameStatus);
        this.initValues(stage);
    }


    public Scene getScene() {
        return scene;
    }

    public BorderPane getBorderPane() {
        return borderPane;
    }

    private void initValues(Stage stage) {
        this.stage = stage;
        this.timerText = new Text("");
        this.actionResultText = new Text("");
        this.wrongMovesText = new Text("");
        this.rightMovesText = new Text("");
        this.imageViewClicked = new ArrayList<>();

        this.setTextStyle(this.timerText, this.actionResultText, this.wrongMovesText, this.rightMovesText);

        this.borderPane = new BorderPane();
        this.scene = new Scene(this.borderPane,1500, 1550);
        this.borderPane.setStyle("-fx-background-color: ghostwhite;");
        this.borderPane.setTop(this.drawTimer());
        this.borderPane.setCenter(this.drawGameView());
        this.borderPane.setBottom(this.drawGameStatus());

        this.initGameStartCountdown();
    }

    private HBox drawTimer() {
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.TOP_CENTER);
        hBox.setSpacing(10);
        hBox.setPadding(new Insets(15, 12,15,12));
        hBox.setStyle("-fx-border-color: darkblue; -fx-border-style: solid inside; -fx-border-width: 3; -fx-border-insets: 3; -fx-border-radius: 2;");

        hBox.getChildren().addAll(this.timerText, this.actionResultText);
        return hBox;
    }

    private HBox drawGameView() {
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.TOP_CENTER);
        hBox.setSpacing(10);
        hBox.setPadding(new Insets(15, 12,15,12));

        Parent grid = this.drawCardsGrid();
        hBox.getChildren().addAll(grid);

        return hBox;
    }

    private Parent drawCardsGrid() {
        GridPane gridPane = new GridPane();

        for (int i = 0; i < this.game.getGrid().size(); i++) {
            for (int j = 0; j < this.game.getGrid().get(i).size(); j++) {
                Card card = this.game.getGrid().get(i).get(j);
                Image image = new Image(card.getImagePathToDraw(), IMG_SIZE_WITH_HEIGHT, IMG_SIZE_WITH_HEIGHT, false, false);
                ImageView imageView = new ImageView(image);
                imageView.addEventHandler(MouseEvent.MOUSE_CLICKED, new CardEventHandler());

                gridPane.add(imageView, j, i);
            }
        }

        gridPane.setAlignment(Pos.CENTER);
        gridPane.setGridLinesVisible(false);
        gridPane.setHgap(1);
        gridPane.setVgap(1);

        return gridPane;
    }

    private HBox drawGameStatus() {
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.BOTTOM_CENTER);
        hBox.setSpacing(10);
        hBox.setPadding(new Insets(15, 12,15,12));
        hBox.setStyle("-fx-border-color: darkblue; -fx-border-style: solid inside; -fx-border-width: 3; -fx-border-insets: 3; -fx-border-radius: 2;");

        this.wrongMovesText.setText("Erros: 0");
        this.rightMovesText.setText("Acertos: 0");

        hBox.getChildren().addAll(this.wrongMovesText, this.rightMovesText);
        return hBox;
    }

    private void initGameStartCountdown() {
        this.timer = new Timer();
        this.timer.schedule(new BeginGameTimerTask(), 0, 1000);
    }

    private String getGameTimeString(Integer currentTime) {
        return ((currentTime % 3600) / 60) + ":" + (currentTime % 60);
    }

    private void changeCardImage(ImageView imageView, Card card, boolean isShowing) {
        card.setShowing(isShowing);
        Image image = new Image(card.getImagePathToDraw(), IMG_SIZE_WITH_HEIGHT, IMG_SIZE_WITH_HEIGHT, false, false);
        imageView.setImage(image);
    }

    private void setTextStyle(Text ...texts) {
        for (Text text : texts) {
            text.setFont(Font.font("Abyssinica SIL", FontWeight.BOLD, FontPosture.REGULAR,25));
            text.setFill(Color.BLACK);
            text.setStroke(Color.BLACK);
            text.setStrokeWidth(1);
        }
    }

    private class BeginGameTimerTask extends TimerTask {
        private Integer time = 3;

        @Override
        public void run() {
            if (time > 0 && !game.isPlayerFoundAll()) {
                if (game.getGameState() == GameState.GAME_LOOP) {
                    timerText.setText(getGameTimeString(game.getCurrentTime()));
                } else {
                    timerText.setText(String.valueOf(time % 60));
                }
                --time;
            } else {
                if (game.getGameState() == GameState.GAME_START) {
                    time = game.getGameDifficulty().getGameTime();
                    game.gameLoop();
                    timerText.setText("Já!");
                } else {
                    time = 0;
                    timer.cancel();
                    timerText.setText("Game Over!");

                    Platform.runLater(() -> new MemoryGameOverModal(stage, game));
                }
            }
        }
    }

    private class CardEventHandler implements EventHandler<Event> {
        @Override
        public void handle(Event event) {
            Node node = (Node) event.getTarget();
            Integer row = GridPane.getRowIndex(node);
            Integer column = GridPane.getColumnIndex(node);
            Card card = game.getGrid().get(row).get(column);

            if (game.getGameState() != GameState.GAME_LOOP || card.getShowing()) {
                return;
            }

            if (game.getCardsClicked().size() < 2) {
                ImageView imageView = (ImageView) event.getSource();
                actionResultText.setText("");
                changeCardImage(imageView, card, true);
                game.getCardsClicked().add(card);
                imageViewClicked.add(imageView);

                this.checkCardsClicked();
            }
        }

        private void checkCardsClicked() {
            if (game.getCardsClicked().size() == 2) {
                Timer timer = new Timer();
                boolean isEquals = this.isEqualCards();
                if (isEquals) {
                    game.getGameStatus().addRightMove();
                    actionResultText.setText("Combinação correta");
                    timer.schedule(new CardRightMatchTimerTask(), 0, 1000);
                } else {
                    game.getGameStatus().addWrongMove();
                    actionResultText.setText("Combinação errada");
                    timer.schedule(new CardWrongMatchTimerTask(), 0, 1000);
                }
                updateGameStatsText(isEquals);
            }
        }

        private Boolean isEqualCards() {
            return game.getCardsClicked().get(0).getCardType().equals(game.getCardsClicked().get(1).getCardType());
        }

        private void updateGameStatsText(boolean isEquals) {
            if (isEquals) {
                rightMovesText.setText("Acertos: " + game.getGameStatus().getRightMoves());
            } else {
                wrongMovesText.setText("Erros: " + game.getGameStatus().getWrongMoves());
            }
        }
    }

    private class CardWrongMatchTimerTask extends TimerTask {
        private Integer time = 1;

        @Override
        public void run() {
            if (time > 0) {
                --time;
            } else {
                game.getCardsClicked().get(0).setShowing(false);
                game.getCardsClicked().get(1).setShowing(false);
                imageViewClicked.get(0).setImage(new Image(game.getCardsClicked().get(0).getImagePathToDraw(), IMG_SIZE_WITH_HEIGHT, IMG_SIZE_WITH_HEIGHT, false, false));
                imageViewClicked.get(1).setImage(new Image(game.getCardsClicked().get(1).getImagePathToDraw(), IMG_SIZE_WITH_HEIGHT, IMG_SIZE_WITH_HEIGHT, false, false));

                actionResultText.setText("");
                game.getCardsClicked().clear();
                imageViewClicked.clear();
                this.cancel();
            }
        }
    }

    private class CardRightMatchTimerTask extends TimerTask {
        private Integer time = 1;

        @Override
        public void run() {
            if (time > 0) {
                --time;
            } else {
                actionResultText.setText("");
                game.getCardsClicked().clear();
                imageViewClicked.clear();
                this.cancel();
            }
        }
    }
}
