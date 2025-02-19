package edu.icet.senuka.FXHotelManager.controller.checkinout;

import atlantafx.base.theme.Styles;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class ClockWidget extends VBox{

    static final DateTimeFormatter DATE_FORMATTER =
            DateTimeFormatter.ofPattern("EEEE, LLLL dd, yyyy");
    static final DateTimeFormatter TIME_FORMATTER =
            DateTimeFormatter.ofPattern("h:mm:ss a");

    public ClockWidget() {


        Label labelClock = new Label(TIME_FORMATTER.format(
                LocalTime.now(ZoneId.systemDefault()))
        );

        labelClock.getStyleClass().add(Styles.TITLE_2);

        Label labelDate = new Label(DATE_FORMATTER.format(
                LocalDate.now(ZoneId.systemDefault()))
        );

        setSpacing(10);
        getChildren().setAll(labelClock, labelDate);

        Timeline timeline = new Timeline(new KeyFrame(
                Duration.seconds(1),
                e -> {
                    LocalTime time = LocalTime.now(ZoneId.systemDefault());
                    labelClock.setText(TIME_FORMATTER.format(time));
                }
        ));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.playFromStart();
    }
}
