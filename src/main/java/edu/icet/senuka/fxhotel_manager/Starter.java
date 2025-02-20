package edu.icet.senuka.fxhotel_manager;


import atlantafx.base.theme.Dracula;
import edu.icet.senuka.fxhotel_manager.util.SceneHandler;
import edu.icet.senuka.fxhotel_manager.util.types.SceneType;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Starter extends Application {
    private double xOffset = 0;
    private double yOffset = 0;
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Application.setUserAgentStylesheet(new Dracula().getUserAgentStylesheet());
        stage.initStyle(StageStyle.TRANSPARENT);

        SceneHandler.setStage(stage);
        SceneHandler.changeScene(SceneType.LOGIN);
    }
}
