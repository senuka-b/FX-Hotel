package edu.icet.senuka.FXHotelManager.service.custom.impl;

import edu.icet.senuka.FXHotelManager.service.custom.ReportService;
import edu.icet.senuka.FXHotelManager.util.types.ReportType;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;

public class ReportServiceImpl implements ReportService {


    @Override
    public JasperPrint createCustomerReport() {

        JasperPrint jasperPrint = createReport(ReportType.Customer);
        JasperViewer.viewReport(jasperPrint, false);

        return jasperPrint;

    }

    @Override
    public JasperPrint createReservationReport() {
        JasperPrint jasperPrint = createReport(ReportType.Reservation);
        JasperViewer.viewReport(jasperPrint, false);

        return jasperPrint;
    }

    @Override
    public JasperPrint createPaymentReport() {
        JasperPrint jasperPrint = createReport(ReportType.Payment);
        JasperViewer.viewReport(jasperPrint, false);

        return jasperPrint;
    }

    @Override
    public JasperPrint createRoomReport() {
        JasperPrint jasperPrint = createReport(ReportType.Room);
        JasperViewer.viewReport(jasperPrint, false);

        return jasperPrint;
    }

    @Override
    public JasperPrint createUserReport() {
        JasperPrint jasperPrint = createReport(ReportType.User);
        JasperViewer.viewReport(jasperPrint, false);

        return jasperPrint;
    }

    @Override
    public JasperPrint createReport(ReportType reportType) {
        try {
            JasperReport jasperReport = JasperCompileManager.compileReport(
                    getClass().getResource(String.format("/reports/report_%s.jrxml", reportType.name().toLowerCase())).openStream()
            );

            String imagePath =  reportType.equals(ReportType.Customer) ?
                            new File(getClass().getResource(
                            "/assets/report-customer.png"
                    ).toURI()).getAbsolutePath() : "";

            HashMap<String, Object> properties = new HashMap<>();
            properties.put("ImagePath", imagePath);


            JasperPrint jasperPrint = JasperFillManager.fillReport(
                    jasperReport,
                    properties,
                    DriverManager.getConnection("jdbc:mysql://localhost:3306/fxhotel", "root", "1234")

            );


            return jasperPrint;


        } catch (JRException e) {
            throw new RuntimeException(e);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
