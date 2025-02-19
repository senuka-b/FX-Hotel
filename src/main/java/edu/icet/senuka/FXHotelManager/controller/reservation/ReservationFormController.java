package edu.icet.senuka.FXHotelManager.controller.reservation;

import com.google.inject.Inject;
import edu.icet.senuka.FXHotelManager.controller.SuperController;
import edu.icet.senuka.FXHotelManager.dto.CheckInOut;
import edu.icet.senuka.FXHotelManager.dto.Customer;
import edu.icet.senuka.FXHotelManager.dto.Reservation;
import edu.icet.senuka.FXHotelManager.dto.Room;
import edu.icet.senuka.FXHotelManager.entity.CheckInOutEntity;
import edu.icet.senuka.FXHotelManager.entity.ReservationEntity;
import edu.icet.senuka.FXHotelManager.service.custom.CheckInOutService;
import edu.icet.senuka.FXHotelManager.service.custom.CustomerService;
import edu.icet.senuka.FXHotelManager.service.custom.ReservationService;
import edu.icet.senuka.FXHotelManager.service.custom.RoomService;
import edu.icet.senuka.FXHotelManager.util.types.AvailabilityType;
import edu.icet.senuka.FXHotelManager.util.types.ReservationStatusType;
import edu.icet.senuka.FXHotelManager.util.types.RoomType;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.modelmapper.ModelMapper;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class ReservationFormController extends SuperController {

    @Inject
    private CustomerService customerService;

    @Inject
    private RoomService roomService;

    @Inject
    private ReservationService service;

    @FXML
    private TableColumn<Reservation, String> columnCheckInDate;

    @FXML
    private TableColumn<?, ?> columnCheckOutDate;

    @FXML
    private TableColumn<?, ?> columnCustomerName;

    @FXML
    private TableColumn<?, ?> columnCustomerPhoneNumber;

    @FXML
    private TableColumn<?, ?> columnID;

    @FXML
    private TableColumn<?, ?> columnRoomNumber;

    @FXML
    private TableColumn<?, ?> columnStatus;

    @FXML
    private ComboBox<Customer> comboBoxCustomer;

    @FXML
    private ComboBox<Room> comboBoxRoom;

    @FXML
    private TableView<Reservation> tableReservation;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
            columnID.setCellValueFactory(new PropertyValueFactory<>("id"));
            columnCheckInDate.setCellValueFactory(new PropertyValueFactory<>("checkInDateValue"));
            columnCheckOutDate.setCellValueFactory(new PropertyValueFactory<>("checkOutDateValue"));
            columnCustomerName.setCellValueFactory(new PropertyValueFactory<>("customerNameValue"));
            columnCustomerPhoneNumber.setCellValueFactory(new PropertyValueFactory<>("customerPhoneNumberValue"));
            columnRoomNumber.setCellValueFactory(new PropertyValueFactory<>("roomIdValue"));
            columnStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

            columnStatus.setCellFactory(column -> new TableCell() {
                @Override
                protected void updateItem(Object status, boolean empty) {
                    super.updateItem(status, empty);

                    ReservationStatusType type = (ReservationStatusType) status;

                    if (empty || type == null) {
                        setText(null);
                        setGraphic(null);
                    } else {

                        switch (type) {
                            case Pending -> setStyle("-fx-background-color:  #EBC49F;");
                            case Confirmed -> setStyle("-fx-background-color:  #91DDCF;");
                            case Cancelled -> setStyle("-fx-background-color:  #FFAACF;");
                        }

                        setStyle(getStyle()+
                                "-fx-border-color: black; "+
                                "-fx-border-width: 2px; ");
                    }


                }
            });

            tableReservation.getSelectionModel().selectedItemProperty().addListener(
                    (observableValue, reservation, t1) -> populateInputValues()
            );


            loadReservations();
            restoreComboBoxes();

    }

    private void populateInputValues() {
        Reservation selectedItem = tableReservation.getSelectionModel().getSelectedItem();

        if (selectedItem != null) {
            comboBoxCustomer.getSelectionModel().select(selectedItem.getCustomer());
            comboBoxRoom.getSelectionModel().select(selectedItem.getRoom());
        }

    }

    private void restoreComboBoxes() {
        comboBoxCustomer.getItems().clear();
        comboBoxRoom.getItems().clear();

        comboBoxRoom.getItems().addAll(roomService.getAll().stream().
                filter(room -> room.getAvailability().equals(AvailabilityType.Available))
                .toList());

        comboBoxRoom.getSelectionModel().select(0);

        comboBoxCustomer.getItems().addAll(customerService.getAll());
        comboBoxCustomer.getSelectionModel().select(0);
    }

    private void loadReservations() {
        tableReservation.getItems().clear();

        tableReservation.setItems(FXCollections.observableList(service.getAll()));
        tableReservation.refresh(); // Fix for colors out of bounds

    }

    public void buttonAddOnAction(ActionEvent actionEvent) {
        boolean addReservation = service.addReservation(
                Reservation.builder()
                        .room(comboBoxRoom.getSelectionModel().getSelectedItem())
                        .customer(comboBoxCustomer.getSelectionModel().getSelectedItem())
                        .status(ReservationStatusType.Pending)
                        .build()
        );

        if (addReservation) {
            showAlert(
                    Alert.AlertType.INFORMATION,
                    "Success!",
                    "Added Reservation Successfully!",
                    "Reservation has been added successfully!"
            );

            restoreComboBoxes();
            loadReservations();

        }


    }

    public void buttonDeleteOnAction(ActionEvent actionEvent) {
        Reservation selectedItem = tableReservation.getSelectionModel().getSelectedItem();

        if (selectedItem == null) {
            showAlert(
                    Alert.AlertType.ERROR,
                    "Error!",
                    "Unable to delete reservation",
                    "You haven't selected a reservation to delete! Please select a reservation first."
            );

            return;
        }

        boolean deleteReservation = service.deleteReservation(selectedItem);

        if (deleteReservation) {
            showAlert(
                    Alert.AlertType.INFORMATION,
                    "Success!",
                    "Deleted Reservation successfully",
                    "The reservation with the ID " + selectedItem.getId() + " has been deleted successfully! " +
                            "And it's corresponding room has been restored."
            );

            loadReservations();
            restoreComboBoxes();
        }
    }

    public void buttonUpdateOnAction(ActionEvent actionEvent) throws CloneNotSupportedException {
        Reservation selectedItem = tableReservation.getSelectionModel().getSelectedItem();

        if (selectedItem == null) {
            showAlert(
                    Alert.AlertType.ERROR,
                    "Error!",
                    "Unable to update reservation",
                    "You haven't selected a reservation to update! Please select a reservation first."
            );

            return;
        }

        Reservation newReservation = (Reservation) selectedItem.clone();


        newReservation.setCustomer(comboBoxCustomer.getSelectionModel().getSelectedItem());
        newReservation.setRoom(comboBoxRoom.getSelectionModel().getSelectedItem());

        boolean updateReservation = service.updateReservation(selectedItem, newReservation);

        if (updateReservation) {
            showAlert(
                    Alert.AlertType.INFORMATION,
                    "Success!",
                    "Updated Reservation successfully",
                    "The reservation with the ID " + selectedItem.getId() + " has been updated successfully! " +
                            "And rooms were updated if any."
            );

            loadReservations();
            restoreComboBoxes();
        }

    }

    public void buttonConfirmReservationOnAction(ActionEvent actionEvent) {
        if (tableReservation.getSelectionModel().getSelectedItem() == null) {
            showAlert(
                    Alert.AlertType.ERROR,
                    "Error!",
                    "Unable to confirm reservation",
                    "You haven't selected a reservation to confirm! Please select a reservation first."
            );

            return;
        }


        boolean confirmReservation = service.confirmReservation(tableReservation.getSelectionModel().getSelectedItem());

        if (confirmReservation) {
            showAlert(
                    Alert.AlertType.INFORMATION,
                    "Success!",
                    "Confirm Reservation",
                    "Successfully confirmed reservation!"
            );

            loadReservations();
        }


    }

    public void buttonCancelReservationOnAction(ActionEvent actionEvent) {
        if (tableReservation.getSelectionModel().getSelectedItem() == null) {
            showAlert(
                    Alert.AlertType.ERROR,
                    "Error!",
                    "Unable to cancel reservation",
                    "You haven't selected a reservation to cancel! Please select a reservation first."
            );

            return;
        }

        boolean cancelReservation = service.cancelReservation(tableReservation.getSelectionModel().getSelectedItem());

        if (cancelReservation) {
            showAlert(
                    Alert.AlertType.INFORMATION,
                    "Success!",
                    "Cancel Reservation",
                    "Successfully cancelled reservation!"
            );
        }

        loadReservations();
    }

    public void buttonMarkReservationOnAction(ActionEvent actionEvent) {
        if (tableReservation.getSelectionModel().getSelectedItem() == null) {
            showAlert(
                    Alert.AlertType.ERROR,
                    "Error!",
                    "Unable to mark reservation as pending",
                    "You haven't selected a reservation to make it pending! Please select a reservation first."
            );

            return;
        }

        boolean markReservationAsPending = service.markReservationAsPending(tableReservation.getSelectionModel().getSelectedItem());

        if (markReservationAsPending) {
            showAlert(
                    Alert.AlertType.INFORMATION,
                    "Success!",
                    "Mark Reservation as Pending",
                    "Successfully marked reservation as pending!"
            );
        }
    }
}
