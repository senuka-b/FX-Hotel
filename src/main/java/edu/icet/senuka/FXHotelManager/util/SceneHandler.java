package edu.icet.senuka.FXHotelManager.util;

import com.google.inject.Guice;
import com.google.inject.Injector;
import edu.icet.senuka.FXHotelManager.controller.SuperController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;

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

        controller = loader.getController();
        controller.setStage(stage);
        scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }

    public static void setStage(Stage primaryStage) {
        stage = primaryStage;
    }
}
