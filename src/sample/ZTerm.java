package sample;

import java.io.File;
import java.util.ArrayList;
import java.util.Optional;

import javafx.scene.control.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.text.Font;
import javafx.event.ActionEvent;

public class ZTerm extends Stage {
    private static final long serialVersionUID = 1L;
    private double width;
    private double height;
    private Stage mainStage;
    private Scene mainScene;
    private GridPane drawingPane;
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
    private ArrayList<TextField> ztTextField = new ArrayList<>();
    private ArrayList<TextArea> ztTextArea = new ArrayList<>();
    private ArrayList<TableView> ztTables = new ArrayList<>();
    private int lastComponentOrder = -2147483648;
    private Color lastChosenColor = null;


    public void showHelp() {

    }


    public ZTerm(double width, double height) {
        this.x = 0;
        this.y = 0;
        this.height = height;
        this.width = width;
        this.ztFontName = "Arial";
        this.ztFontSize = 15.0;
        this.ztFont = new Font(this.ztFontName, this.ztFontSize);
        this.setTabSize(4);
        this.setHeight(height);
        this.setWidth(width);
    }

    public String getPasswordFromDialog(String message){
        return "";
    }


    public void addRowToTable(int indexOfTable, String rowData) {

    }


    public Color getColorFromDialog(String message){
        return Color.AQUA;
    }


    public void addTable(int width, int height, String columnNames){
        TableView<String> ztTable;

        if (columnNames == null) {
            ztTable = new TableView<>();
            this.ztTables.add(ztTable);

        }
    }


    public void addButton(String buttonText, final Object handlingObject, final String handlingMethodName){
        if (buttonText == null || buttonText.isBlank())
            buttonText = "null";

        buttonText = buttonText.replaceAll("\t", this.tab);
        boolean changed = false;
        Button bt = new Button(buttonText);

        bt.setOnAction((ActionEvent e) -> {
            try {
                handlingObject.getClass().getMethod(handlingMethodName, (Class[])null).invoke(handlingObject, (Object[])null);
            } catch (Exception var3){
                System.err.println(var3.getCause() + " occurred when invoking " + handlingMethodName + " method in the object '" + handlingObject + "' (" + handlingObject.getClass() + ").");
            }
        });

        bt.setFont(this.ztFont);
        this.x += bt.getWidth();
        this.y += bt.getHeight();
        this.drawingPane.add(bt, this.x, this.y);
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

        //message = message.replaceAll("\\t", this.tab);
        //String[] lines = message.split("\n");

        Label newLabel = new Label(message);
        newLabel.prefHeight(-1);
        newLabel.prefWidth(-1);
        System.out.println(newLabel.getWidth());

        if (true){
            this.x += newLabel.getWidth();
            this.y += newLabel.getHeight();
            drawingPane.add(new Label(message), this.x , this.y);
        } else {

        }
    }


    public void setXY(int x, int y) {
        this.iLeft = x;
        this.iTop = y;
        this.x = x;
        this.y = y;
        this.maxHeight = 0;
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


    public boolean showConfirmationDialog(String message) {
        if (message == null)
            message = "null";


        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText(null);
        alert.setContentText(message);

        Optional<ButtonType> result = alert.showAndWait();

        return result.get() == ButtonType.OK;
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


    public void addImageIcon(String imageFilename) {
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

            this.x += iv.getFitWidth();
            this.y += iv.getFitHeight();
            this.drawingPane.add(iv, this.x, this.y);
        }
    }


    public void getMouseX() {

    }

}
