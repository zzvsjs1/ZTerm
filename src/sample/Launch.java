package sample;

import javafx.application.Application;
import javafx.stage.Stage;

public class Launch extends Application {
    ZTerm zt;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.close();
        this.zt = new ZTerm(500, 600);

    }


    public static void main(String[] args) {
        launch(args);
    }
}
