package edu.icet.senuka.fxhotel_manager.controller;

import atlantafx.base.util.Animations;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Duration;


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

    public Stage getStage() {
        return stage;
    }

    public void setup() {
        setupTitleBar();
        setupWindowControlButtons();

    }

    private void setupTitleBar() {
        if (titleBar == null) return; // Dashboard

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
        if (buttonClose == null || buttonMinimize == null) return;

        buttonClose.setOnAction(actionEvent -> {
            Timeline animation = Animations.fadeOut(stage.getScene().getRoot(), Duration.seconds(1));
            animation.setOnFinished(event -> stage.close());

            animation.playFromStart();
        });

        buttonMinimize.setOnAction(actionEvent -> stage.setIconified(true));
    }

    public ButtonType showAlert(Alert.AlertType type, String title, String header, String body) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(body);

        return alert.showAndWait().get();

    }



}
