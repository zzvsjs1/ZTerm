package sample;

import javafx.application.Application;
import javafx.stage.Stage;

public class Launch extends Application {
    @Override
    public void start(Stage primaryStage) {
        primaryStage.close();
        ZTerm zt = new ZTerm(500, 600);
        zt.show();
    }
    

    public static void main(String[] args) {
        launch(args);
    }
}
