package edu.icet.senuka.fxhotel_manager.service.custom;

import edu.icet.senuka.fxhotel_manager.service.SuperService;
import edu.icet.senuka.fxhotel_manager.util.types.ReportType;
import net.sf.jasperreports.engine.JasperPrint;

public interface ReportService extends SuperService {
    public JasperPrint createCustomerReport();
    public JasperPrint createReservationReport();
    public JasperPrint createPaymentReport();
    public JasperPrint createRoomReport();
    public JasperPrint createUserReport();
    public JasperPrint createReport(ReportType reportType);
}
