package edu.icet.senuka.FXHotelManager.controller.login;

import com.google.inject.Inject;
import edu.icet.senuka.FXHotelManager.controller.SuperController;
import edu.icet.senuka.FXHotelManager.service.custom.UserService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginFormController extends SuperController  {

    @FXML
    private TextField textFieldUsername;

    @Inject
    private UserService service;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    public void buttonLoginOnAction(ActionEvent actionEvent) {
        System.out.println(service.login(null));

    }
}
