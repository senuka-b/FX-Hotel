package edu.icet.senuka.FXHotelManager.controller.login;

import com.google.inject.Inject;
import edu.icet.senuka.FXHotelManager.controller.SuperController;
import edu.icet.senuka.FXHotelManager.dto.User;
import edu.icet.senuka.FXHotelManager.service.custom.UserService;
import edu.icet.senuka.FXHotelManager.util.SceneHandler;
import edu.icet.senuka.FXHotelManager.util.types.SceneType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
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

    public void buttonLoginOnAction(ActionEvent actionEvent) throws IOException {
        User user = User.builder()
                .username(textFieldUsername.getText().trim())
                .password(textFieldPassword.getText().trim())
                .build();

        User loggedInUser = service.login(user);


        if (loggedInUser != null) {
            showAlert(Alert.AlertType.INFORMATION, "Login Successful!", "Welcome "+ loggedInUser.getUsername() + " !", "Hope you enjoy your stay!");

            SceneHandler.setUser(loggedInUser);
            SceneHandler.changeScene(SceneType.DASHBOARD);

            return;
        }

        showAlert(Alert.AlertType.ERROR,
                "Login failed!",
                "Unable to login!",
                "Your credentials are incorrect. Please try again by re-checking both your username and password");

    }

    public void buttonForgotPasswordOnAction(ActionEvent actionEvent) throws IOException {
        SceneHandler.createDialog(SceneType.FORGOTPASSWORD).show();
    }
}
