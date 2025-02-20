package edu.icet.senuka.fxhotel_manager.controller.checkinout;

import atlantafx.base.controls.Calendar;
import com.google.inject.Inject;
import edu.icet.senuka.fxhotel_manager.controller.SuperController;
import edu.icet.senuka.fxhotel_manager.dto.Reservation;
import edu.icet.senuka.fxhotel_manager.service.custom.CheckInOutService;
import edu.icet.senuka.fxhotel_manager.service.custom.ReservationService;
import edu.icet.senuka.fxhotel_manager.util.types.ReservationStatusType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;




public class CheckInOutFormController extends SuperController {

    @FXML
    private Pane panelCheckInCalendar;

    @FXML
    private Pane panelCheckOutCalendar;

    @FXML
    private ComboBox<Reservation> comboBoxReservation;

    private Calendar checkInCalendar;
    private Calendar checkOutCalendar;

    @Inject
    private ReservationService reservationService;

    @Inject
    private CheckInOutService service;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        checkInCalendar = new Calendar();
        checkInCalendar.setTopNode(new ClockWidget());
        checkInCalendar.setShowWeekNumbers(true);

        checkOutCalendar = new Calendar();
        checkOutCalendar.setTopNode(new ClockWidget());
        checkOutCalendar.setShowWeekNumbers(true);

        panelCheckInCalendar.getChildren().add(checkInCalendar);
        panelCheckOutCalendar.getChildren().add(checkOutCalendar);

        loadReservations();
        updateCalendars();
    }

    private void loadReservations() {
        comboBoxReservation.getItems().clear();

        comboBoxReservation.getItems().addAll(
            reservationService.getAll().stream().
                    filter(
                            reservation -> reservation.getStatus().equals(ReservationStatusType.Confirmed)
                    ).toList()
        );

        comboBoxReservation.getSelectionModel().select(0);
    }

    public void buttonMarkCheckInOnAction() {
        Reservation selectedItem = comboBoxReservation.getSelectionModel().getSelectedItem();

        if (selectedItem == null) {
            showAlert(
                    Alert.AlertType.ERROR,
                    "Error!",
                    "Unable to mark check in date",
                    "You haven't selected a reservation to check in! Please make sure to select a reservation to update check in dates."
            );

            return;
        }

        selectedItem.getCheckInOut().setCheckInDate(
                checkInCalendar.getValue()
        );

        boolean markCheckIn = service.markCheckIn(selectedItem.getCheckInOut(), selectedItem.getRoom());

        if (markCheckIn) {

            showAlert(
                    Alert.AlertType.INFORMATION,
                    "Success!",
                    "Successfully marked check in",
                    "The check in date has been successfully updated in the database!"
            );


            loadReservations();
            updateCalendars();
        }


    }

    public void buttonMarkCheckOutOnAction() {
        Reservation selectedItem = comboBoxReservation.getSelectionModel().getSelectedItem();

        if (selectedItem == null) {
            showAlert(
                    Alert.AlertType.ERROR,
                    "Error!",
                    "Unable to mark check in date",
                    "You haven't selected a reservation to check in! Please make sure to select a reservation to update check in dates."
            );

            return;
        }

        selectedItem.getCheckInOut().setCheckOutDate(
                checkOutCalendar.getValue()
        );

        boolean markCheckOut = service.markCheckOut(selectedItem.getCheckInOut(), selectedItem.getRoom());

        if (markCheckOut) {
            showAlert(
                    Alert.AlertType.INFORMATION,
                    "Success!",
                    "Successfully marked check out",
                    "The check out date has been successfully updated in the database!"
            );

            loadReservations();
            updateCalendars();
        } else {
            showAlert(
                    Alert.AlertType.ERROR,
                    "Error!",
                    "Unable to mark check out date",
                    "Please make sure that the checkout date is atleast one day after the check in date. You cannot mark a check out date with specifying check in first!"
            );
        }
    }

    private void updateCalendars() {
        Reservation selectedItem = comboBoxReservation.getSelectionModel().getSelectedItem();

        if (selectedItem == null) return;

        if (selectedItem.getCheckInOut().getCheckInDate() != null) {
            checkInCalendar.setValue(
                        selectedItem.getCheckInOut().getCheckInDate()
            );


        } else checkInCalendar.setValue(null);

        if (selectedItem.getCheckInOut().getCheckOutDate() != null) {
            checkOutCalendar.setValue(
                    selectedItem.getCheckInOut().getCheckOutDate()
            );

        } else checkOutCalendar.setValue(null);

    }

    public void comboBoxReservationOnAction() {
        updateCalendars();
    }

}
