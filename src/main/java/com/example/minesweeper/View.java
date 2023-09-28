package com.example.minesweeper;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextBoundsType;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;

public class View extends GridPane {
    private Button newGameButton = new Button("Начать игру");
    private Button exitButton = new Button("Закрыть программу");
    private Stage stage;
    private RadioButton newer = new RadioButton("Новичок");
    private RadioButton amateur = new RadioButton("Любитель");
    private RadioButton professional = new RadioButton("Профессионал");
    private RadioButton custom = new RadioButton("Установить размер:");
    private ComboBox<Integer> comboBoxWidth = new ComboBox<Integer>();;
    private ComboBox<Integer> comboBoxHeight = new ComboBox<Integer>();

    public void initialize(Tile[][] gameField) {
        getChildren().clear();
        getColumnConstraints().clear();
        getRowConstraints().clear();

        for(int i = 0; i < gameField.length; i++) {
            ColumnConstraints col = new ColumnConstraints(30);
            RowConstraints row = new RowConstraints(30);
            getColumnConstraints().add(col);
            getRowConstraints().add(row);
        }

        for (int i = 0; i < getColumnCount(); i++) {
            for (int j = 0; j < getRowCount(); j++) {
                Rectangle rect = new Rectangle(30, 30, Color.GRAY);
                Text text = new Text("");
                text.setFont(new Font("Arial", 28));
                text.setVisible(false);
                rect.setStroke(Color.BLACK);
                text.setTranslateX(7);
                text.setTranslateY(2);
                add(rect, i, j);
                add(text, i, j);
            }
        }
    }

    public void showDialogMenu(String massage) {
        stage = new Stage();
        exitButton.setAlignment(Pos.BOTTOM_CENTER);
        VBox pane = new VBox();
        pane.setAlignment(Pos.CENTER);
        pane.setSpacing(10);

        ToggleGroup group = new ToggleGroup();
        newer.setToggleGroup(group);
        amateur.setToggleGroup(group);
        professional.setToggleGroup(group);
        custom.setToggleGroup(group);
        newer.setTranslateX(-17);
        amateur.setTranslateX(-13);
        HBox hBox1 = new HBox();
        hBox1.setAlignment(Pos.CENTER);
        HBox hBox2 = new HBox();
        hBox2.setAlignment(Pos.CENTER);

        comboBoxWidth.disableProperty().setValue(true);
        comboBoxWidth.getItems().addAll(5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30);
        comboBoxWidth.getSelectionModel().selectFirst();
        comboBoxWidth.setConverter(new IntegerStringConverter());
        comboBoxWidth.setPrefWidth(55.0);
        Text textWidth = new Text("Ширина:  ");
        Text textHeight = new Text("Высота:    ");

        comboBoxHeight.disableProperty().setValue(true);
        comboBoxHeight.getItems().addAll(5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30);
        comboBoxHeight.getSelectionModel().selectFirst();
        comboBoxHeight.setConverter(new IntegerStringConverter());
        comboBoxHeight.setPrefWidth(55.0);

        hBox1.getChildren().addAll(textWidth, comboBoxWidth);
        hBox2.getChildren().addAll(textHeight, comboBoxHeight);

        pane.getChildren().addAll(newer, amateur, professional, custom, hBox1, hBox2, newGameButton, exitButton);
        group.selectToggle(newer);
        Scene scene = new Scene(pane, 300, 250);
        stage.setTitle(massage);
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setOnCloseRequest(event -> {
            System.exit(0);
        });
        stage.setX(485);
        stage.show();
    }

    public void endOfGame(Tile[][] gameField, String massage) {
        if(massage.equals("Вы проиграли!")) {
            for (int i = 0; i < gameField.length; i++) {
                for (int j = 0; j < gameField[i].length; j++) {
                    if(gameField[i][j].isMined()) gameField[i][j].setOpen(true);
                }
            }
        }
        draw(gameField);
        showDialogMenu(massage);
    }

    public void draw(Tile[][] gameField) {
        for (int i = 0; i < gameField.length; i++) {
            for (int j = 0; j < gameField[i].length; j++) {
                int index = (i * gameField.length + j)  * 2;
                Rectangle rect = (Rectangle) this.getChildren().get(index);
                Text text = (Text) this.getChildren().get(index + 1);
                if(gameField[i][j].isOpen()) {
                    rect.setFill(Color.ANTIQUEWHITE);
                    String neighborCount = gameField[i][j].getMinedNeighborsCount() > 0 ? String.valueOf(gameField[i][j].getMinedNeighborsCount()) : "";
                    text.setText(neighborCount);
                    text.fillProperty().setValue(Color.BLACK);
                    text.strokeProperty().setValue(null);
                    text.setVisible(true);
                }
                if(gameField[i][j].isOpen() && gameField[i][j].isMined()) {
                    text.setText("\uD83D\uDCA3");
                    text.setTranslateY(-1.5);
                    text.setFont(new Font("Arial", 20));
                    text.fillProperty().setValue(Color.RED);
                    text.strokeProperty().setValue(Color.BLACK);
                    text.boundsTypeProperty().setValue(TextBoundsType.VISUAL);
                }
                if(gameField[i][j].isMarked() && !gameField[i][j].isOpen()) {
                    text.setText("\uD83D\uDEA9");
                    text.fillProperty().setValue(Color.RED);
                    text.strokeProperty().setValue(Color.BLACK);
                    text.setVisible(true);
                    text.setMouseTransparent(true);
                } else if(!gameField[i][j].isOpen()) {
                    text.setText("");
                }
            }
        }
    }

    public Button getNewGameButton() {
        return newGameButton;
    }

    public Button getExitButton() {
        return exitButton;
    }

    public Stage getStage() {
        return stage;
    }

    public RadioButton getNewer() {
        return newer;
    }

    public RadioButton getAmateur() {
        return amateur;
    }

    public RadioButton getProfessional() {
        return professional;
    }

    public RadioButton getCustom() {
        return custom;
    }

    public ComboBox<Integer> getComboBoxWidth() {
        return comboBoxWidth;
    }

    public ComboBox<Integer> getComboBoxHeight() {
        return comboBoxHeight;
    }
}
