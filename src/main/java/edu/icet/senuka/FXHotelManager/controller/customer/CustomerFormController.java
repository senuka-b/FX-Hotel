package edu.icet.senuka.FXHotelManager.controller.customer;

import com.google.inject.Inject;
import edu.icet.senuka.FXHotelManager.controller.SuperController;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import edu.icet.senuka.FXHotelManager.dto.Customer;
import edu.icet.senuka.FXHotelManager.service.custom.CustomerService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;

public class CustomerFormController extends SuperController {

    @Inject
    CustomerService service;
    @FXML
    private TableColumn<Customer, String> columnAddress;

    @FXML
    private TableColumn<?, ?> columnFullName;

    @FXML
    private TableColumn<?, ?> columnID;

    @FXML
    private TableColumn<?, ?> columnPhoneNumber;

    @FXML
    private TextField textFieldAddress;

    @FXML
    private TextField textFieldFullName;

    @FXML
    private TextField textFieldPhoneNumber;

    @FXML
    private TextField textFieldSearch;

    @FXML
    private TableView<Customer> tableCustomer;


    @FXML
    void buttonAddOnAction(ActionEvent event) {
        if (!validateTextFields()) return;

        boolean addCustomer = service.addCustomer(
                Customer.builder()
                        .fullName(textFieldFullName.getText().trim())
                        .phoneNumber(textFieldPhoneNumber.getText().trim())
                        .address(textFieldAddress.getText().trim())
                        .build()
        );

        if (addCustomer) showAlert(
                Alert.AlertType.INFORMATION,
                "Success!",
                "Added Customer successfully!",
                "Customer has been stored in the database successfully with the name " + textFieldFullName.getText()

        ); else showAlert(
                Alert.AlertType.ERROR,
                "Error!",
                "Unable to add Customer",
                "Couldn't add customer to the database."
        );

        clearTextFields();
        loadTable();
    }



    @FXML
    void buttonDeleteOnAction(ActionEvent event) {
        Customer selectedItem = tableCustomer.getSelectionModel().getSelectedItem();

        if (selectedItem != null) {
            if (service.deleteCustomer(selectedItem)) {
                showAlert(
                        Alert.AlertType.INFORMATION,
                        "Success!",
                        "Deleted Customer successfully",
                        "The customer with the name " + selectedItem.getFullName() + " has been deleted succesfully!"
                );

                loadTable();
                clearTextFields();
            }
        } else {
            showAlert(
                    Alert.AlertType.ERROR,
                    "Error!",
                    "Unable to delete customer",
                    "You haven't selected a customer to delete! Please select a customer first."
            );
        }
    }

    @FXML
    void buttonUpdateOnAction(ActionEvent event) {
        Customer selectedItem = tableCustomer.getSelectionModel().getSelectedItem();

        if (selectedItem != null) {
            if (!validateTextFields()) return;

            selectedItem.setFullName(textFieldFullName.getText().trim());
            selectedItem.setAddress(textFieldAddress.getText().trim());
            selectedItem.setPhoneNumber(textFieldPhoneNumber.getText().trim());

            boolean updateCustomer = service.updateCustomer(
                    selectedItem
            );

            if (updateCustomer) {
                showAlert(
                        Alert.AlertType.INFORMATION,
                        "Success!",
                        "Updated customer succesfully",
                        "The customer with ID " + selectedItem.getId() + " has been updated succesfully!"
                );
            }
        } else {
            showAlert(
                    Alert.AlertType.ERROR,
                    "Error!",
                    "Unable to update customer",
                    "You haven't selected a customer to update! Please select a customer first."
            );
        }

        loadTable();
    }

    @FXML
    void textFieldSearchOnKeyRelease(KeyEvent    event) {
        if (textFieldSearch.getText().trim().equals("")) {
            loadTable();
            return;
        }

        searchTable(textFieldSearch.getText());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        columnID.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnFullName.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        columnPhoneNumber.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        columnAddress.setCellValueFactory(new PropertyValueFactory<>("address"));

        tableCustomer.getSelectionModel().selectedItemProperty().addListener(
                (observableValue, customer, t1) -> populateTextFields()
        );

        loadTable();
    }

    private void loadTable() {
        tableCustomer.getItems().clear();

        List<Customer> customers = service.getAll();

        tableCustomer.setItems(
            FXCollections.observableList(customers)
        );
    }

    private void searchTable(String searchQuery) {
        tableCustomer.getItems().clear();

        tableCustomer.setItems(
                FXCollections.observableList(
                        service.getSearchResults(searchQuery, 5)
                )
        );
    }

    private boolean validateTextFields() {
        for (TextField field : new TextField[]{textFieldFullName, textFieldPhoneNumber, textFieldAddress}) {
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
        for (TextField field : new TextField[]{textFieldFullName, textFieldPhoneNumber, textFieldAddress}) {
            field.setText("");
        }
    }

    private void populateTextFields() {
        Customer selectedCustomer = tableCustomer.getSelectionModel().getSelectedItem();

        if (selectedCustomer != null) {
            textFieldFullName.setText(selectedCustomer.getFullName());
            textFieldAddress.setText(selectedCustomer.getAddress());
            textFieldPhoneNumber.setText(selectedCustomer.getPhoneNumber());
        }
    }


}
