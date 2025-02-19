package edu.icet.senuka.FXHotelManager.controller.billing;

import com.google.inject.Inject;
import edu.icet.senuka.FXHotelManager.controller.SuperController;
import edu.icet.senuka.FXHotelManager.dto.Payment;
import edu.icet.senuka.FXHotelManager.service.custom.PaymentService;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.hibernate.query.sqm.internal.SimpleDeleteQueryPlan;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


public class ViewPaymentDialogFormController extends SuperController {


    @FXML
    private TableColumn<Payment, String> columnAmountPaid;

    @FXML
    private TableColumn<Payment, String> columnAmountToBePaid;

    @FXML
    private TableColumn<Payment, String> columnCustomerName;

    @FXML
    private TableColumn<Payment, String> columnPaymentID;

    @FXML
    private TableColumn<Payment, String> columnPaymentType;

    @FXML
    private TableColumn<Payment, String> columnReservationID;


    @FXML
    private TableView<Payment> tablePayment;

    @Inject
    private PaymentService service;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        columnAmountPaid.setCellValueFactory(cellDataFeatures -> new SimpleStringProperty(
                "Rs. " + cellDataFeatures.getValue().getAmountPaid()
        ));

        columnAmountToBePaid.setCellValueFactory(cellDataFeatures -> new SimpleStringProperty(
                "Rs. " + cellDataFeatures.getValue().getReservation().getCheckInOut().getTotalPrice()
        ));

        columnCustomerName.setCellValueFactory(cellDataFeatures -> new SimpleStringProperty(
                cellDataFeatures.getValue().getReservation().getCustomerNameValue()
        ));

        columnPaymentID.setCellValueFactory(cellDataFeatures -> new SimpleStringProperty(
                cellDataFeatures.getValue().getId() + ""
        ));

        columnPaymentType.setCellValueFactory(cellDataFeatures -> new SimpleStringProperty(
                cellDataFeatures.getValue().getPaymentType().toString()
        ));

        columnReservationID.setCellValueFactory(cellDataFeatures -> new SimpleStringProperty(
                cellDataFeatures.getValue().getReservation().getId() + ""
        ));

        tablePayment.setItems(FXCollections.observableList(service.getAll()));
    }
}
