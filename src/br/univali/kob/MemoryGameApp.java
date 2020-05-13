package br.univali.kob;

import br.univali.kob.view.MemoryGameMenuView;
import javafx.application.Application;
import javafx.stage.Stage;

public class MemoryGameApp extends Application {
    private Stage stage;

    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;

        stage.setTitle("Jogo da Memória");
        stage.setScene(new MemoryGameMenuView(this.stage).getScene());
        stage.show();
        stage.setResizable(false);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
