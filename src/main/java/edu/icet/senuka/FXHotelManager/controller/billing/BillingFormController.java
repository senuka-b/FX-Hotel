package edu.icet.senuka.FXHotelManager.controller.billing;

import com.google.inject.Inject;
import edu.icet.senuka.FXHotelManager.controller.SuperController;
import edu.icet.senuka.FXHotelManager.dto.Payment;
import edu.icet.senuka.FXHotelManager.dto.Reservation;
import edu.icet.senuka.FXHotelManager.service.custom.PaymentService;
import edu.icet.senuka.FXHotelManager.service.custom.ReservationService;
import edu.icet.senuka.FXHotelManager.util.types.PaymentType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;

import java.net.URL;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.ResourceBundle;

public class BillingFormController extends SuperController {

    @FXML
    private ToggleButton buttonPayViaCard;

    @FXML
    private ToggleButton buttonPayViaCash;

    @FXML
    private ToggleButton buttonPayViaCoupon;

    @FXML
    private ComboBox<Reservation> comboBoxReservation;

    @FXML
    private Text labelCalculatedAmount;

    @FXML
    private TextField textFieldAmountPaid;

    private ToggleGroup toggleGroup;

    @Inject
    private PaymentService service;

    @Inject
    private ReservationService reservationService;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        toggleGroup = new ToggleGroup();
        buttonPayViaCoupon.setToggleGroup(toggleGroup);
        buttonPayViaCard.setToggleGroup(toggleGroup);
        buttonPayViaCash.setToggleGroup(toggleGroup);

        comboBoxReservation.getItems().addAll(
                reservationService.getCheckOutReservations()
        );

        comboBoxReservation.getSelectionModel().select(0);

        setLabelCalculatedAmountValue();

    }

    private void setLabelCalculatedAmountValue() {
        Reservation reservation = comboBoxReservation.getSelectionModel().getSelectedItem();
        if (reservation == null) return;

        labelCalculatedAmount.setText(
                ChronoUnit.DAYS.between(
                        reservation.getCheckInOut().getCheckInDate(),
                        reservation.getCheckInOut().getCheckOutDate()
                ) + " days x Rs. " +
                        reservation.getRoom().getPricePerNight() + " = Rs. " +
                        reservation.getCheckInOut().getTotalPrice()
        );
    }

    public void buttonPayOnAction(ActionEvent actionEvent) {
        if (toggleGroup.getSelectedToggle() == null || textFieldAmountPaid.getText().trim().equals("")) {
            showAlert(
                    Alert.AlertType.ERROR,
                    "Error!",
                    "Unable to finalize payment!",
                    "Please make sure you select a payment type and enter the amount paid before finalizing your payment!"
            );

            return;
        }

        ToggleButton selectedToggle = (ToggleButton) toggleGroup.getSelectedToggle();

        HashMap<ToggleButton, PaymentType> buttonPaymentTypeHashMap = new HashMap<>();
        buttonPaymentTypeHashMap.put(buttonPayViaCard, PaymentType.Card);
        buttonPaymentTypeHashMap.put(buttonPayViaCash, PaymentType.Cash);
        buttonPaymentTypeHashMap.put(buttonPayViaCoupon, PaymentType.Coupon);

        Reservation reservation = comboBoxReservation.getSelectionModel().getSelectedItem();
        boolean finalizePayment = service.finalizePayment(
                Payment.builder()
                        .reservation(reservation)
                        .amountPaid(Double.parseDouble(textFieldAmountPaid.getText().trim()))
                        .paymentType(buttonPaymentTypeHashMap.get(selectedToggle))
                        .build()
        );

        if (finalizePayment) {
            showAlert(
                    Alert.AlertType.INFORMATION,
                    "Success!",
                    "Succesfully finalized payment!",
                    "The payment for customer " +
                            reservation.getCustomerNameValue()
                    + "has been finalized successfully with the amount " + textFieldAmountPaid.getText()
            );



            textFieldAmountPaid.setText("");
        }
    }

    public void buttonViewAllPayments(ActionEvent actionEvent) {
    }

    public void comboBoxReservationOnAction(ActionEvent actionEvent) {
        setLabelCalculatedAmountValue();
    }
}
