package edu.icet.senuka.fxhotel_manager.controller.dashboard;

import atlantafx.base.util.Animations;
import edu.icet.senuka.fxhotel_manager.controller.SuperController;
import edu.icet.senuka.fxhotel_manager.util.SceneHandler;
import edu.icet.senuka.fxhotel_manager.util.types.SceneType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DashboardFormController extends SuperController {
    @FXML
    private AnchorPane mainAnchor;

    public void buttonInsightOnAction() throws IOException {
        SceneHandler.changeScene(SceneType.INSIGHTS);
    }
    public void buttonReportOnAction() throws IOException {
        SceneHandler.changeScene(SceneType.REPORTS);
    }

    public void buttonBillingOnAction() throws IOException {
        SceneHandler.changeScene(SceneType.BILLING);
    }

    public void buttonLogOutOnAction() throws IOException {
        ButtonType logOut = showAlert(Alert.AlertType.CONFIRMATION, "Log out",
                "You are about to log out of the system!",
                "Are you sure you want to log out of the system?");


        if (logOut == ButtonType.OK) {
            SceneHandler.changeScene(SceneType.LOGIN);
        }
    }

    public void buttonCheckInCheckOutOnAction() throws IOException {
        SceneHandler.changeScene(SceneType.CHECKINCHECKOUT);
    }

    public void buttonReservationOnAction() throws IOException {
        SceneHandler.changeScene(SceneType.RESERVATION);
    }

    public void buttonRoomOnAction() throws IOException {
        SceneHandler.changeScene(SceneType.ROOM);
    }

    public void buttonCustomerOnAction() throws IOException {
        SceneHandler.changeScene(SceneType.CUSTOMER);
    }

    public void switchScene(AnchorPane root) {
        mainAnchor.getChildren().clear();
        mainAnchor.getChildren().add(root);

        Animations.fadeIn(root, Duration.seconds(1)).playFromStart();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        /*
        * Dummy initialize
        * */
    }


    public void buttonUsersOnAction() throws IOException {
        SceneHandler.changeScene(SceneType.USERS);
    }
}
