package edu.icet.senuka.FXHotelManager.util.types;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum SceneType {
    LOGIN("/view/login.fxml"),
    DASHBOARD("/view/dashboard.fxml"),
    INSIGHTS("/view/insights.fxml"),
    CUSTOMER("/view/customer.fxml"),
    ROOM("/view/room.fxml"),
    RESERVATION("/view/reservation.fxml"),
    CHECKINCHECKOUT("/view/checkinout.fxml"),
    BILLING("/view/billing.fxml"),
    REPORTS("/view/reports.fxml"),
    USERS("/view/users.fxml"),
    VIEWPAYMENTDIALOG("/view/dialogs/view_payment.fxml"),
    NOPERMISSION("/view/no_permission.fxml");

    private final String path;

    public String getPath() {
        return path;
    }
}
