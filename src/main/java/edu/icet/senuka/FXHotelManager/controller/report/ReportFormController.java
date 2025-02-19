package edu.icet.senuka.FXHotelManager.controller.report;

import com.google.inject.Inject;
import edu.icet.senuka.FXHotelManager.controller.SuperController;
import edu.icet.senuka.FXHotelManager.service.custom.ReportService;
import edu.icet.senuka.FXHotelManager.util.SceneHandler;
import edu.icet.senuka.FXHotelManager.util.types.ReportType;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.stage.FileChooser;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;

import java.awt.event.ActionEvent;
import java.io.File;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class ReportFormController extends SuperController {

    @FXML
    private ComboBox<ReportType> comboBoxReports;



    @Inject
    private ReportService reportService;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        comboBoxReports.getItems().addAll(ReportType.values());
        comboBoxReports.getSelectionModel().select(0);
    }

    public void buttonGenerateReportOnAction(javafx.event.ActionEvent actionEvent) {
        switch (comboBoxReports.getSelectionModel().getSelectedItem()) {
            case Customer -> reportService.createCustomerReport();
            case Room -> reportService.createRoomReport();
            case User -> reportService.createUserReport();
            case Payment -> reportService.createPaymentReport();
            case Reservation -> reportService.createReservationReport();
        }
    }

    public void buttonSaveReportOnAction(javafx.event.ActionEvent actionEvent) throws JRException {
        JasperPrint jasperPrint = reportService.createReport(comboBoxReports.getSelectionModel().getSelectedItem());

        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF Files", "*.pdf"));
        fileChooser.setTitle("Choose location to save report");
        fileChooser.setInitialFileName(String.format("FXHotel-Customer-Report-%s",
                LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
        );

        File file = fileChooser.showSaveDialog(SceneHandler.getStage());

        JasperExportManager.exportReportToPdfFile(jasperPrint,
                file.getAbsolutePath().endsWith(".pdf") ? file.getAbsolutePath() : file.getAbsolutePath() + ".pdf"
        );

        showAlert(
                Alert.AlertType.INFORMATION,
                "Success",
                "Save Report",
                "Successfully saved the generated report to your disk!"
        );
    }
}
