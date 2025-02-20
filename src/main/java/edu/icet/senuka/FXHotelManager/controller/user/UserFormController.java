package edu.icet.senuka.FXHotelManager.controller.user;

import com.google.inject.Inject;
import edu.icet.senuka.FXHotelManager.controller.SuperController;
import edu.icet.senuka.FXHotelManager.dto.Customer;
import edu.icet.senuka.FXHotelManager.dto.User;
import edu.icet.senuka.FXHotelManager.service.custom.UserService;
import edu.icet.senuka.FXHotelManager.util.types.RoleType;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class UserFormController extends SuperController {
    @FXML
    private TableColumn<?, ?> columnID;

    @FXML
    private TableColumn<?, ?> columnRole;

    @FXML
    private TableColumn<?, ?> columnUsername;

    @FXML
    private TableColumn<?, ?> columnEmail;

    @FXML
    private ComboBox<RoleType> comboBoxRole;

    @FXML
    private TableView<User> tableUser;

    @FXML
    private TextField textFieldPassword;

    @FXML
    private TextField textFieldSearch;

    @FXML
    private TextField textFieldUsername;

    @FXML
    private TextField textFieldEmail;


    @Inject
    private UserService service;

    @FXML
    void buttonAddOnAction(ActionEvent event) {
        if (!validateTextFields()) return;

        boolean addUser = service.signup(
                User.builder()
                        .username(textFieldUsername.getText().trim())
                        .password(textFieldPassword.getText().trim())
                        .role(comboBoxRole.getSelectionModel().getSelectedItem())
                        .build()
        );

        if (addUser) showAlert(
                Alert.AlertType.INFORMATION,
                "Success!",
                "Added user successfully!",
                "User has been stored in the database successfully with the username " + textFieldUsername.getText()

        ); else showAlert(
                Alert.AlertType.ERROR,
                "Error!",
                "Unable to add User",
                "Couldn't add User to the database."
        );

        clearTextFields();
        loadUsers();
    }

    @FXML
    void buttonDeleteOnAction(ActionEvent event) {
        User selectedItem = tableUser.getSelectionModel().getSelectedItem();

        if (selectedItem != null) {
            if (service.deleteUser(selectedItem)) {
                showAlert(
                        Alert.AlertType.INFORMATION,
                        "Success!",
                        "Deleted user successfully",
                        "The user with the username " + selectedItem.getUsername() + " has been deleted successfully!"
                );

                loadUsers();
                clearTextFields();
            }
        } else {
            showAlert(
                    Alert.AlertType.ERROR,
                    "Error!",
                    "Unable to delete user",
                    "You haven't selected a user to delete! Please select a user first."
            );
        }
    }

    @FXML
    void buttonUpdateOnAction(ActionEvent event) {
        User selectedItem = tableUser.getSelectionModel().getSelectedItem();

        if (selectedItem != null) {
            if (!validateTextFields()) return;

            selectedItem.setUsername(textFieldUsername.getText().trim());
            selectedItem.setPassword(textFieldPassword.getText().trim());
            selectedItem.setRole(comboBoxRole.getSelectionModel().getSelectedItem());

            boolean updateUser = service.updateUser(
                    selectedItem
            );

            if (updateUser) {
                showAlert(
                        Alert.AlertType.INFORMATION,
                        "Success!",
                        "Updated user successfully",
                        "The user with username " + selectedItem.getUsername() + " has been updated successfully!"
                );
            }
        } else {
            showAlert(
                    Alert.AlertType.ERROR,
                    "Error!",
                    "Unable to update user",
                    "You haven't selected a user to update! Please select a user first."
            );
        }

        loadUsers();
    }

    @FXML
    void textFieldSearchOnKeyRelease(KeyEvent event) {
        if (textFieldSearch.getText().trim().equals("")) {
            loadUsers();
            return;
        }

        searchTable(textFieldSearch.getText());
    }

    private void searchTable(String searchQuery) {
        tableUser.getItems().clear();

        tableUser.setItems(
                FXCollections.observableList(
                        service.getSearchResults(searchQuery, 5)
                )
        );
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        comboBoxRole.getItems().addAll(RoleType.values());
        comboBoxRole.getSelectionModel().select(0);

        columnID.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnUsername.setCellValueFactory(new PropertyValueFactory<>("username"));
        columnRole.setCellValueFactory(new PropertyValueFactory<>("role"));
        columnEmail.setCellValueFactory(new PropertyValueFactory<>("email"));

        tableUser.getSelectionModel().selectedItemProperty().addListener(
                (observableValue, user, t1) -> populateInputFields()
        );

        loadUsers();
    }

    private void loadUsers() {
        tableUser.getItems().clear();

        tableUser.getItems().addAll(service.getAll());
    }

    private void populateInputFields() {
        User selectedItem = tableUser.getSelectionModel().getSelectedItem();

        if (selectedItem != null) {

            textFieldUsername.setText(selectedItem.getUsername());
            textFieldPassword.setText(selectedItem.getPassword());
            textFieldEmail.setText(selectedItem.getEmail());
            comboBoxRole.getSelectionModel().select(selectedItem.getRole());
        }

    }

    private boolean validateTextFields() {
        for (TextField field : new TextField[]{textFieldUsername, textFieldPassword, textFieldEmail}) {
            if (field.getText().trim().equals("")) {
                showAlert(
                        Alert.AlertType.ERROR,
                        "Error!",
                        "Validation error",
                        "One or more fields are empty. Please fill in all the details before doing anything"
                );
                return false;
            };
        }

        return true;
    }

    private void clearTextFields() {
        for (TextField field : new TextField[]{textFieldUsername, textFieldPassword, textFieldEmail})
            field.setText("");
    }
}
