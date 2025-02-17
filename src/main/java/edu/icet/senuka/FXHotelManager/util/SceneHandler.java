package edu.icet.senuka.FXHotelManager.util;

import atlantafx.base.util.Animations;
import com.google.inject.Guice;
import com.google.inject.Injector;
import edu.icet.senuka.FXHotelManager.controller.SuperController;
import edu.icet.senuka.FXHotelManager.controller.dashboard.DashboardFormController;
import edu.icet.senuka.FXHotelManager.util.types.SceneType;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import jdk.jshell.spi.ExecutionControl;
import lombok.Getter;

import java.io.IOException;
import java.util.function.Consumer;

@Getter
public class SceneHandler {
    private static Stage stage;
    private static SuperController controller;
    private static Scene scene;


    public static void changeScene(SceneType type) throws IOException {

        FXMLLoader loader = new FXMLLoader(SceneHandler.class.getResource(type.getPath()));

        Injector injector = Guice.createInjector(new AppModule());
        loader.setControllerFactory(injector::getInstance);

        AnchorPane root = loader.load();

        if (type == SceneType.LOGIN || type == SceneType.DASHBOARD) {
            System.out.println("Yes");

            controller = loader.getController();
            controller.setStage(stage);

            scene = new Scene(root);

            stage.setScene(scene);
            stage.show();

            if (type == SceneType.DASHBOARD) {
                changeScene(SceneType.INSIGHTS);
            }

        } else { // switching scenes in dashboard

            DashboardFormController formController = (DashboardFormController) controller;
            formController.switchScene(root);

            ((SuperController) loader.getController()).setStage(stage);
        }

    }

    public static void setStage(Stage primaryStage) {
        stage = primaryStage;
    }



    }
