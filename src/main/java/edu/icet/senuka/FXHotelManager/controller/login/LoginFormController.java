package edu.icet.senuka.FXHotelManager.controller.login;

import com.google.inject.Inject;
import edu.icet.senuka.FXHotelManager.controller.SuperController;
import edu.icet.senuka.FXHotelManager.dto.User;
import edu.icet.senuka.FXHotelManager.service.custom.UserService;
import edu.icet.senuka.FXHotelManager.util.SceneHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginFormController extends SuperController  {

    @FXML
    private TextField textFieldUsername;

    @FXML
    private PasswordField textFieldPassword;

    @Inject
    private UserService service;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    public void buttonLoginOnAction(ActionEvent actionEvent) {
        User user = User.builder()
                .username(textFieldUsername.getText())
                .password(textFieldPassword.getText())
                .build();

        if (service.login(user)) {
            showAlert(Alert.AlertType.INFORMATION, "Login Successful!", "Welcome User !", "Hope you enjoy your stay!");
//            SceneHandler.changeScene();

            return;
        }

        showAlert(Alert.AlertType.ERROR,
                "Login failed!",
                "Unable to login!",
                "Your credentials are incorrect. Please try again by re-checking both your username and password");

    }

    public void buttonForgotPasswordOnAction(ActionEvent actionEvent) {
    }
}
