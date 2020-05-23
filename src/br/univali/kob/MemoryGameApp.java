package br.univali.kob;

import br.univali.kob.view.MemoryGameMenuView;
import javafx.application.Application;
import javafx.stage.Stage;

public class MemoryGameApp extends Application {

    @Override
    public void start(Stage stage) {
        stage.setTitle("Jogo da Memória");
        stage.setScene(new MemoryGameMenuView(stage).getScene());
        stage.show();
        stage.setResizable(false);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
