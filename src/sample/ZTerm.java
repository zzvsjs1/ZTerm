package sample;


import java.io.File;
import java.util.ArrayList;
import java.util.Optional;

import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.text.Font;
import javafx.event.ActionEvent;
import javafx.util.Pair;

import javax.swing.*;
import javax.swing.text.JTextComponent;


public class ZTerm extends Stage{
    private static final long serialVersionUID = 1L;
    private double width;
    private double height;
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
    private double mouseX;
    private double mouseY;
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
        this.drawingPane = new GridPane();
        this.drawingPane.setVgap(5);
        this.drawingPane.setHgap(5);
        this.mainScene = new Scene(this.drawingPane);
        this.setScene(this.mainScene);
        this.setTabSize(4);
        this.setHeight(height);
        this.setWidth(width);

        this.show();
    }


    //???
    public String getPasswordFromDialog(String message){
        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle("PasswordField");
        dialog.setHeaderText(null);

        ButtonType loginButtonType = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        PasswordField password = new PasswordField();
        password.setPromptText("Password");

        grid.add(new Label("Password:"), 0, 0);
        grid.add(password, 1, 0);

        dialog.getDialogPane().setContent(grid);

        Optional<String> result = dialog.showAndWait();

        if(result.isEmpty())
            result = Optional.of("null");

        return result.get();
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
        this.drawingPane.add(bt, this.x, this.y);
        this.x += bt.getWidth();
        this.y += bt.getHeight();

        bt.setTranslateX(this.x);
        bt.setTranslateY(this.y);

        this.drawingPane.getHeight();
    }


    public void setTabSize(int tabSize){
        if (tabSize >= 1)
            this.tab = String.valueOf(new char[tabSize]).replaceAll("\u0000", " ");
    }


    public void addTextField(String defaultText, double widthInPixels){
        if (defaultText == null || defaultText.isBlank())
            defaultText = "null";

        TextField tf = new TextField(defaultText);
        tf.setFont(this.ztFont);
        tf.setPrefWidth(widthInPixels);
        this.ztTextField.add(tf);

        //
        this.drawingPane.add(tf, this.x += 5, this.y += 5);
    }


    public void addTextArea(String defaultText, double widthInPixels, double heightInPixels) {
        if (defaultText == null) {
            defaultText = "null";
        }

        TextArea textArea = new TextArea();
        textArea.setPrefWidth(widthInPixels);
        textArea.setPrefHeight(heightInPixels);
        textArea.setText(defaultText);
        this.ztTextArea.add(textArea);
        ScrollPane scrollPane = new ScrollPane(textArea);
        this.drawingPane.getChildren().add(scrollPane);
    }


    public void setTextInFieldEntry(int textFieldIndex, String text) {
        if (text == null)
            text = "null";

        this.ztTextField.get(textFieldIndex).setText(text);
    }

    public String getTextFromFieldEntry(int textFieldIndex) {
        return this.ztTextField.get(textFieldIndex).getText();
    }


    public void setTextInAreaEntry(int textFieldIndex, String text) {
        if (text == null)
            text = "null";

        this.ztTextArea.get(textFieldIndex).setText(text);
    }


    public String getTextFromAreaEntry(int textFieldIndex) {
        return this.ztTextArea.get(textFieldIndex).getText();
    }


    public void clear(){
        this.drawingPane = new GridPane();
        this.mainScene = new Scene(this.drawingPane);
        this.setScene(this.mainScene);
    }


    public void setFontName(String fontName) {
        this.ztFontName = fontName;
        this.ztFont = new Font(this.ztFontName, this.ztFontSize);
    }


    public void setBackgroundColor(Color backgroundColor) {
        this.drawingPane.setBackground(new Background(new BackgroundFill(backgroundColor, new CornerRadii(0), Insets.EMPTY)));
    }


    public void setBackgroundColor(double r, double g, double b, double o) {
        this.drawingPane.setBackground(new Background(new BackgroundFill(new Color(r, g, b, o), new CornerRadii(0), Insets.EMPTY)));
    }


    public void setFontColor(Color fontColor) {
        this.ztFontColor = fontColor;
    }


    public void setFontColor(double r, double g, double b, double o) {
        this.ztFontColor =  new Color(r, g, b, o);
    }


    public void print(String message){
        if (message == null)
            message = "null";

        Text newText = new Text(message);

        if (true){
            this.x += message.length();
            this.y += 2;
            drawingPane.add(newText, this.x , this.y);
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


    public boolean showConfirmInformationDialog(String message) {
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

            ScrollPane scrollPane = new ScrollPane();
            this.drawingPane.add(iv, this.x, this.y);
        }
    }


    public double getMouseX() {
        return this.mouseX;
    }

}
