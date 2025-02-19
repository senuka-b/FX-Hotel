package edu.icet.senuka.FXHotelManager.service.custom;

import edu.icet.senuka.FXHotelManager.service.SuperService;
import edu.icet.senuka.FXHotelManager.util.types.ReportType;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

public interface ReportService extends SuperService {
    public JasperPrint createCustomerReport();
    public JasperPrint createReservationReport();
    public JasperPrint createPaymentReport();
    public JasperPrint createRoomReport();
    public JasperPrint createUserReport();
    public JasperPrint createReport(ReportType reportType);
}
