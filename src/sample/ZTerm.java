package sample;

import java.io.File;
import java.util.ArrayList;
import java.util.Optional;

import javafx.scene.control.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.effect.Light;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.text.Font;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class ZTerm extends Application {
    private static final long serialVersionUID = 1L;
    private int width;
    private int height;
    private Scene mainScene;
    private ScrollPane drawingPane;
    private Font ztFont;
    private double ztFontSize;
    private String ztFontName;
    private Color ztFontColor;
    private int iLeft;
    private int iTop;
    private int x;
    private int y;
    private int maxHeight;
    private String key = "";
    private String tab;
    private ArrayList<TextField> ztTextComponents = new ArrayList<>();
    private ArrayList<TableView> ztTables = new ArrayList<>();
    private int lastComponentOrder = -2147483648;
    private Color lastChosenColor = null;

    private KeyEvent ztKeyEvent;


    public void showHelp() {

    }

    public ZTerm() {
        this.height = 500;
        this.width = 500;
        this.ztFontName = "Arial";
        this.ztFontSize = 15.0;
        this.ztFont = new Font(this.ztFontName, this.ztFontSize);

    }

    @Override
    public void start(Stage primaryStage) {
        this.drawingPane = new ScrollPane();
        this.drawingPane.setBackground(Background.EMPTY);
        this.mainScene = new Scene(drawingPane, this.height, this.width);
        primaryStage.setTitle("Hello");
        primaryStage.setScene(mainScene);
        primaryStage.setHeight(this.height);
        primaryStage.setWidth(this.width);
        primaryStage.show();
    }


    public String getPasswordFromDialog(String message){
        return "";
    }


    public int addRowToTable(int indexOfTable, String rowData) {return 0;}


    public Color getColorFromDialog(String message){
        return Color.AQUA;
    }


    public int addTable(int width, int height, String columnNames){
        return 0;
    }


    public void addButton(String buttonText, final Object handlingObject, final String handlingMethodName){
        if (buttonText == null || buttonText.isBlank())
            buttonText = "null";

        buttonText = buttonText.replaceAll("\t", this.tab);
        boolean changed = false;
        Button bt = new Button(buttonText);

        bt.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent ae) {
                try {
                    handlingObject.getClass().getMethod(handlingMethodName, (Class[])null).invoke(handlingObject, (Object[])null);
                } catch (Exception var3){
                    System.err.println(var3.getCause() + " occurred when invoking " + handlingMethodName + " method in the object '" + handlingObject + "' (" + handlingObject.getClass() + ").");
                }
            }
        });
        bt.setFont(this.ztFont);
        this.drawingPane.setContent(bt);
        double buttonHeight = bt.getPrefHeight();
        double buttonWidth = bt.getPrefWidth();

        double thisWidth = this.x + buttonWidth + 2;

    }

    public void setFontColor(Color fontColor) {
        this.ztFontColor = fontColor;
    }

    public void setBackgroundColor(Color backgroundColor) {
    }

    public void setTabSize(int tabSize){
        if (tabSize >= 1)
            this.tab = String.valueOf(new char[tabSize]).replaceAll("\u0000", " ");
    }


    public void addTextField(String defaultText, int widthInPixels){
        if (defaultText == null || defaultText.isBlank())
            defaultText = "null";

        defaultText = defaultText.replaceAll("\\t", this.tab);
        TextField tf = new TextField(defaultText);

        //return
    }


    public void clear(){

    }


    public void print(String message){
        if (message == null)
            message = "null";

        message = message.replaceAll("\\t", this.tab);
        String[] lines = message.split("\n");

        if (lines.length > 1){

        } else {

        }
    }


    public void showMessageDialog(String title, String message){
        if (title == null)
            title = "null";

        if (message == null)
            message = "null";

        Alert messageDialog = new Alert(Alert.AlertType.INFORMATION);
        messageDialog.setTitle(title);
        messageDialog.setHeaderText(null);
        messageDialog.setContentText(message);
        messageDialog.showAndWait();
    }


    public void showErrorDialog(String message) {
        if (message == null)
            message = "null";

        Alert error = new Alert(Alert.AlertType.ERROR);
        error.setTitle("Error");
        error.setHeaderText(null);
        error.setContentText(message);
        error.showAndWait();
    }

    public void showWarningDialog(String message) {
        if (message == null)
            message = "null";

        Alert error = new Alert(Alert.AlertType.WARNING);
        error.setTitle("Warning");
        error.setHeaderText(null);
        error.setContentText(message);
        error.showAndWait();
    }

    public String getInputString(String message) {
        TextInputDialog inputDialog = new TextInputDialog();
        inputDialog.setTitle("Input Dialog");
        inputDialog.setHeaderText(null);
        inputDialog.setContentText(message);

        Optional<String> input = inputDialog.showAndWait();

        return input.orElse("null");
    }

    public String getFilePath() {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Choose a file");
        Stage chooseWindow = new Stage();
        chooseWindow.setWidth(600);
        chooseWindow.setHeight(400);
        File file = chooser.showOpenDialog(chooseWindow);

        return file == null ? "null" : file.getAbsolutePath();
    }

    public String setFilePath() {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Choose a file");
        Stage chooseWindow = new Stage();
        chooseWindow.setWidth(600);
        chooseWindow.setHeight(400);
        File file = chooser.showSaveDialog(chooseWindow);

        return file == null ? "null" : file.getAbsolutePath();
    }

    public  void  addImageIcon(String imageFilename) {
        File file = new File(imageFilename);
        Image i = new Image(file.toURI().toString());

        if (i.errorProperty().get())
            throw new RuntimeException("Not a ZTerm error: Could not load image \"" + imageFilename + "\"");
        else {
            ImageView iv = new ImageView(i);

            if (i.getHeight() > this.height)
                iv.fitHeightProperty().setValue(i.getHeight() / 2);

            if (i.getWidth() > this.width)
                iv.fitWidthProperty().setValue(i.getWidth() / 2);

            this.drawingPane.setContent(iv);
            this.drawingPane.setFitToHeight(true);
        }
    }

    public double getMouseX() {
        return 0;
    }


    public static void main(String[] args) {
        launch(args);
    }
}
