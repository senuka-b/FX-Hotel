package edu.icet.senuka.FXHotelManager.controller.room;

import com.google.inject.Inject;
import edu.icet.senuka.FXHotelManager.controller.SuperController;
import edu.icet.senuka.FXHotelManager.dto.Room;
import edu.icet.senuka.FXHotelManager.service.custom.RoomService;
import edu.icet.senuka.FXHotelManager.util.types.Availability;
import edu.icet.senuka.FXHotelManager.util.types.RoomType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.TilePane;

import java.net.URL;
import java.util.ResourceBundle;

public class RoomFormController extends SuperController {

    @FXML
    private ComboBox<Availability> comboBoxAvailability;

    @FXML
    private ComboBox<RoomType> comboBoxRoomType;

    @FXML
    private TilePane mainTilePane;

    @FXML
    private TextField textFieldPricePerStay;

    @FXML
    private TextField textFieldRoomNumber;

    @Inject
    private RoomService service;

    private Room selectedRoom;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        restoreInputFields();
        loadRooms();

    }

    private void restoreInputFields() {
        textFieldRoomNumber.setText("");
        textFieldPricePerStay.setText("");

        comboBoxRoomType.getItems().addAll(RoomType.values());
        comboBoxRoomType.getSelectionModel().select(0);

        comboBoxAvailability.getItems().addAll(Availability.values());
        comboBoxAvailability.getSelectionModel().select(0);
    }

    private void loadRooms() {
        mainTilePane.getChildren().clear();

        for (Room room : service.getAll()) {

            Button button = new Button(room.getId()+"");
            button.setStyle(
                    "-fx-background-color: " +
                            (room.getAvailability().equals(Availability.Occupied) ? "#b0933c" : "#10c95a")
            );

            button.getStyleClass().add("success");
            button.setPrefSize(60, 50);
            button.setUserData(room);

            button.setOnAction(actionEvent -> {
                populateTextFields((Room) button.getUserData());
            });

            mainTilePane.getChildren().add(button);
        }

    }

    private void populateTextFields(Room room) {
        this.selectedRoom = room;

        textFieldRoomNumber.setText(room.getId()+"");
        textFieldPricePerStay.setText(room.getPricePerNight()+"");

        comboBoxAvailability.getSelectionModel().select(room.getAvailability());
        comboBoxRoomType.getSelectionModel().select(room.getRoomType());

    }

    public void buttonAddOnAction(ActionEvent actionEvent) {

        if (textFieldPricePerStay.getText().trim().equals("")) {
            showAlert(
                    Alert.AlertType.ERROR,
                    "Error!",
                    "Unable to add room!",
                    "Please make sure you add a price per stay!"
            );

            return;
        }

        boolean addRoom = service.addRoom(
                Room.builder()
                        .roomType(comboBoxRoomType.getSelectionModel().getSelectedItem())
                        .availability(comboBoxAvailability.getSelectionModel().getSelectedItem())
                        .pricePerNight(Double.parseDouble(textFieldPricePerStay.getText().trim()))
                        .build()
        );

        if (addRoom) {
            showAlert(
                    Alert.AlertType.INFORMATION,
                    "Success!",
                    "Add room",
                    "Room has been added successfully !"
            );

            loadRooms();
        }
    }

    public void buttonDeleteOnAction(ActionEvent actionEvent) {
        if (selectedRoom == null) {
            showAlert(
                    Alert.AlertType.ERROR,
                    "Error!",
                    "Unable to update room!",
                    "Please select a room with a valid ID first!"
            );

            return;
        }

        boolean deleteRoom = service.deleteRoom(selectedRoom);

        if (deleteRoom) {
            showAlert(
                    Alert.AlertType.INFORMATION,
                    "Success!",
                    "Deleted room successfully",
                    "The room with ID " + selectedRoom.getId() + " has been deleted successfully!"
            );

            loadRooms();
        }
    }

    public void buttonUpdateOnAction(ActionEvent actionEvent) {
        if (selectedRoom == null) {
            showAlert(
                    Alert.AlertType.ERROR,
                    "Error!",
                    "Unable to update room!",
                    "Please select a room with a valid ID first!"
            );

            return;
        }

        selectedRoom.setRoomType(comboBoxRoomType.getSelectionModel().getSelectedItem());
        selectedRoom.setAvailability(comboBoxAvailability.getSelectionModel().getSelectedItem());
        selectedRoom.setPricePerNight(Double.parseDouble(textFieldPricePerStay.getText().trim()));

        boolean updateRoom = service.updateRoom(selectedRoom);

        if (updateRoom) {
            showAlert(
                    Alert.AlertType.INFORMATION,
                    "Success!",
                    "Updated room successfully",
                    "The room with ID " + selectedRoom.getId() + " has been updated successfully!"
            );

            loadRooms();
        }

    }
}
