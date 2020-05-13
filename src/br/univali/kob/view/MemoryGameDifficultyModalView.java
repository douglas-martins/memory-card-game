package br.univali.kob.view;

import br.univali.kob.model.GameDifficulty;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MemoryGameDifficultyModalView {
    private Stage stage;

    private Stage dialog;

    private Scene scene;

    private BorderPane borderPane;

    public MemoryGameDifficultyModalView(Stage stage) {
        this.dialog = new Stage();
        this.stage = stage;
        this.borderPane = new BorderPane();
        this.borderPane.setCenter(this.drawMessage());
        this.borderPane.setBottom(this.drawVBox());
        this.borderPane.setStyle("-fx-background-color: lightgray;");

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
        vBox.setStyle("-fx-border-color: darkgray; -fx-border-style: solid inside; -fx-border-width: 3; -fx-border-insets: 3; -fx-border-radius: 2;");

        vBox.getChildren().addAll(this.drawNormalDifficultyButton(), this.drawHardDifficultyButton());
        return vBox;
    }

    private Text drawMessage() {
        return new Text("Selecione uma dificuldade");
    }

    private Button drawNormalDifficultyButton() {
        Button normalDifficultyButton = new Button("Normal");
        normalDifficultyButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new NormalDifficultyButtonEventHandler());
        normalDifficultyButton.setPrefSize(100, 20);
        return normalDifficultyButton;
    }

    private Button drawHardDifficultyButton() {
        Button hardDifficultyButton = new Button("Difícil");
        hardDifficultyButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new HardDifficultyButtonEventHandler());
        hardDifficultyButton.setPrefSize(100, 20);
        return hardDifficultyButton;
    }

    private class NormalDifficultyButtonEventHandler implements EventHandler<Event> {
        @Override
        public void handle(Event event) {
            stage.setScene(new MemoryGameView(stage, GameDifficulty.NORMAL).getScene());
            stage.hide();
            dialog.close();
            stage.show();
        }
    }

    private class HardDifficultyButtonEventHandler implements EventHandler<Event> {
        @Override
        public void handle(Event event) {
            stage.setScene(new MemoryGameView(stage, GameDifficulty.HARD).getScene());
            stage.hide();
            dialog.close();
            stage.show();
        }
    }
}
