package edu.icet.senuka.fxhotel_manager.util;

import edu.icet.senuka.fxhotel_manager.service.custom.ReportService;
import edu.icet.senuka.fxhotel_manager.util.types.ReportType;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;

import java.io.File;

public class ReportGenerationThread {

    public static void createReport(ReportService reportService, ReportType type,  DataCallback callback) {
        new Thread(() -> {

                JasperPrint print = null;

                switch (type) {
                    case Customer -> print = reportService.createCustomerReport();
                    case Room -> print = reportService.createRoomReport();
                    case User -> print = reportService.createUserReport();
                    case Payment -> print = reportService.createPaymentReport();
                    case Reservation -> print = reportService.createReservationReport();
                }

            try {
                callback.onDataReady(print);
            } catch (JRException e) {
                throw new RuntimeException(e);
            }

        }).start();
    }

    public static void exportReportToPDF(File file, ReportService reportService, ReportType type, DataCallback callback) {
        new Thread(() -> {

            JasperPrint print = reportService.createReport(type);

            try {
                JasperExportManager.exportReportToPdfFile(print, file.getAbsolutePath());

                callback.onDataReady(print);

            } catch (JRException e) {
                throw new RuntimeException(e);
            }

        }).start();
    }

    public interface DataCallback {
        void onDataReady(JasperPrint data) throws JRException;
    }
}
