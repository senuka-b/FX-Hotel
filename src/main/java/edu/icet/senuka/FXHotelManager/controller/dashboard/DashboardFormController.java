package edu.icet.senuka.FXHotelManager.controller.dashboard;

import atlantafx.base.util.Animations;
import edu.icet.senuka.FXHotelManager.controller.SuperController;
import edu.icet.senuka.FXHotelManager.util.SceneHandler;
import edu.icet.senuka.FXHotelManager.util.types.SceneType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
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

    public void buttonInsightOnAction(ActionEvent actionEvent) throws IOException {
        SceneHandler.changeScene(SceneType.INSIGHTS);
    }
    public void buttonReportOnAction(ActionEvent actionEvent) throws IOException {
        SceneHandler.changeScene(SceneType.REPORTS);
    }

    public void buttonBillingOnAction(ActionEvent actionEvent) throws IOException {
        SceneHandler.changeScene(SceneType.BILLING);
    }

    public void buttonLogOutOnAction(ActionEvent actionEvent) throws IOException {
        ButtonType logOut = showAlert(Alert.AlertType.CONFIRMATION, "Log out",
                "You are about to log out of the system!",
                "Are you sure you want to log out of the system?");


        if (logOut == ButtonType.OK) {
            SceneHandler.changeScene(SceneType.LOGIN);
        }
    }

    public void buttonCheckInCheckOutOnAction(ActionEvent actionEvent) throws IOException {
        SceneHandler.changeScene(SceneType.CHECKINCHECKOUT);
    }

    public void buttonReservationOnAction(ActionEvent actionEvent) throws IOException {
        SceneHandler.changeScene(SceneType.RESERVATION);
    }

    public void buttonRoomOnAction(ActionEvent actionEvent) throws IOException {
        SceneHandler.changeScene(SceneType.ROOM);
    }

    public void buttonCustomerOnAction(ActionEvent actionEvent) throws IOException {
        SceneHandler.changeScene(SceneType.CUSTOMER);
    }

    public void switchScene(AnchorPane root) {
        mainAnchor.getChildren().clear();
        mainAnchor.getChildren().add(root);

        Animations.fadeIn(root, Duration.seconds(1)).playFromStart();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


    public void buttonUsersOnAction(ActionEvent actionEvent) throws IOException {
        SceneHandler.changeScene(SceneType.USERS);
    }
}
