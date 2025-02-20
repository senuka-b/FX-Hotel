package edu.icet.senuka.fxhotel_manager.controller.insights;

import com.google.inject.Inject;
import edu.icet.senuka.fxhotel_manager.controller.SuperController;
import edu.icet.senuka.fxhotel_manager.dto.ReservationChartData;
import edu.icet.senuka.fxhotel_manager.dto.Room;
import edu.icet.senuka.fxhotel_manager.service.custom.CustomerService;
import edu.icet.senuka.fxhotel_manager.service.custom.ReservationService;
import edu.icet.senuka.fxhotel_manager.service.custom.RoomService;
import edu.icet.senuka.fxhotel_manager.util.SceneHandler;
import edu.icet.senuka.fxhotel_manager.util.types.AvailabilityType;
import javafx.fxml.FXML;
import javafx.scene.chart.*;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class InsightFormController extends SuperController {

    @FXML
    private BarChart<String, Number> chartRevenue;

    @FXML
    private PieChart chartRoomStatus;

    @FXML
    private Text labelCustomerCount;

    @FXML
    private Text labelReservationCount;

    @FXML
    private Text labelUsername;

    @Inject
    private CustomerService customerService;

    @Inject
    private ReservationService reservationService;

    @Inject
    private RoomService roomService;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        labelCustomerCount.setText(
                customerService.getAll().size() + " customers"
        );

        labelReservationCount.setText(
                reservationService.getAll().size() + " reservations"
        );

        labelUsername.setText(
                String.format("%s [%s] ",
                        SceneHandler.getUser().getUsername(),
                        SceneHandler.getUser().getRole())
        );

        loadBarChart();
        loadPieChart();

    }

    private void loadBarChart() {

        List<ReservationChartData> dataList = reservationService.getReservationChartData();

        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Revenue");

        for (ReservationChartData data : dataList) {
            series.getData().add(new XYChart.Data<>(String.valueOf(data.getId()), data.getValue()));
        }

        chartRevenue.getData().add(series);

    }

    private void loadPieChart() {
        List<Room> rooms = roomService.getAll();

        Map<AvailabilityType, Long> roomCountMap = rooms.stream()
                .collect(Collectors.groupingBy(Room::getAvailability, Collectors.counting()));

        for (Map.Entry<AvailabilityType, Long> entry : roomCountMap.entrySet()) {
            chartRoomStatus.getData().add(new PieChart.Data(
                    String.format("%s (%d)", entry.getKey(), entry.getValue()),
                    entry.getValue()
            ));
        }
    }
}
