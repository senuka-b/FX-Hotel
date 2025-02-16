package edu.icet.senuka.FXHotelManager.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public abstract class SuperController implements Initializable {
    private double xOffset = 0;
    private double yOffset = 0;

    private Stage stage;

    @FXML
    private Button buttonClose;
    @FXML
    private Button buttonMinimize;

    @FXML
    private BorderPane titleBar;

    public void setStage(Stage stage) {
        this.stage = stage;

        setup();
    }

    public void setup() {
        setupTitleBar();
        setupWindowControlButtons();

    }

    private void setupTitleBar() {
        titleBar.setOnMousePressed(event -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });

        titleBar.setOnMouseDragged(event -> {
            stage.setX(event.getScreenX() - xOffset);
            stage.setY(event.getScreenY() - yOffset);
        });
    }

    private void setupWindowControlButtons() {
        buttonClose.setOnAction(actionEvent -> {
            stage.close();
        });

        buttonMinimize.setOnAction(actionEvent -> {
            stage.setIconified(true);
        });
    }

    public void showAlert(Alert.AlertType type, String title, String header, String body) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(body);

        alert.show();

    }



}
