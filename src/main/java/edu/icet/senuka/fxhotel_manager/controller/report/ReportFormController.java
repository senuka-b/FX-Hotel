package edu.icet.senuka.fxhotel_manager.controller.report;

import com.google.inject.Inject;
import edu.icet.senuka.fxhotel_manager.controller.SuperController;
import edu.icet.senuka.fxhotel_manager.service.custom.ReportService;
import edu.icet.senuka.fxhotel_manager.util.ReportGenerationThread;
import edu.icet.senuka.fxhotel_manager.util.SceneHandler;
import edu.icet.senuka.fxhotel_manager.util.types.ReportType;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ProgressIndicator;
import javafx.stage.FileChooser;

import java.io.File;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class ReportFormController extends SuperController {

    @FXML
    private ComboBox<ReportType> comboBoxReports;

    @FXML
    private Button buttonGenerateReport;

    @FXML
    private Button buttonSaveReport;

    @Inject
    private ReportService reportService;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        comboBoxReports.getItems().addAll(ReportType.values());
        comboBoxReports.getSelectionModel().select(0);
    }

    public void buttonGenerateReportOnAction() {
        ProgressIndicator progressIndicator = new ProgressIndicator();
        buttonGenerateReport.setGraphic(progressIndicator);
        buttonGenerateReport.setDisable(true);

        ReportGenerationThread.createReport(reportService, comboBoxReports.getSelectionModel().getSelectedItem(),
                dataCompleted -> Platform.runLater(() -> {
                    buttonGenerateReport.setGraphic(null);
                    buttonGenerateReport.setDisable(false);
                }));


    }

    public void buttonSaveReportOnAction() {

        buttonSaveReport.setGraphic(new ProgressIndicator());
        buttonSaveReport.setDisable(true);

        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF Files", "*.pdf"));
        fileChooser.setTitle("Choose location to save report");
        fileChooser.setInitialFileName(String.format("FXHotel-%s-Report-%s",
                comboBoxReports.getSelectionModel().getSelectedItem().toString(),
                LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
        );

        File file = fileChooser.showSaveDialog(SceneHandler.getStage());

        buttonSaveReport.setGraphic(null);
        buttonSaveReport.setDisable(false);

        if (file == null) return;

        ReportGenerationThread.exportReportToPDF(
                file,
                reportService,
                comboBoxReports.getSelectionModel().getSelectedItem(),
                print -> {}
        );

        showAlert(
                Alert.AlertType.INFORMATION,
                "Success",
                "Save Report",
                "Successfully saved the generated report to your disk!"
        );


    }
}
