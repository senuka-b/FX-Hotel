package edu.icet.senuka.fxhotel_manager.util;

import com.google.inject.Guice;
import com.google.inject.Injector;
import edu.icet.senuka.fxhotel_manager.controller.SuperController;
import edu.icet.senuka.fxhotel_manager.controller.dashboard.DashboardFormController;
import edu.icet.senuka.fxhotel_manager.dto.User;
import edu.icet.senuka.fxhotel_manager.util.types.RoleType;
import edu.icet.senuka.fxhotel_manager.util.types.SceneType;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lombok.Getter;

import java.io.IOException;
import java.util.*;

@Getter
public class SceneHandler {
    private static Stage stage;
    private static SuperController controller;
    private static Scene scene;

    private static User user;

    private static Map<RoleType, List<SceneType>> permissions = new HashMap<>();

    static {
        // Admin Permissions
        // ALL permissions
        List<SceneType> adminPermissions = new ArrayList<>();
        adminPermissions.addAll(List.of(SceneType.values()));

        // Manager Permissions
        // All permissions excluding Reports and Users
        List<SceneType> managerPermissions = new ArrayList<>();
        managerPermissions.addAll(Arrays.stream(SceneType.values())
                .filter(sceneType -> !sceneType.equals(SceneType.USERS) && !sceneType.equals(SceneType.REPORTS))
                .toList());

        // Staff Permissions
        List<SceneType> staffPermissions = new ArrayList<>();
        staffPermissions.addAll(List.of(
                SceneType.LOGIN,
                SceneType.DASHBOARD,
                SceneType.INSIGHTS,
                SceneType.RESERVATION,
                SceneType.CHECKINCHECKOUT,
                SceneType.BILLING,
                SceneType.VIEWPAYMENTDIALOG
        ));

        permissions.put(RoleType.Admin, adminPermissions);
        permissions.put(RoleType.Manager, managerPermissions);
        permissions.put(RoleType.Staff, staffPermissions);

    }

    private static String resolvePath(SceneType type) {
        if (user == null) return type.getPath();

        if (permissions.get(user.getRole()).contains(type)) {
            return type.getPath();
        }

        return SceneType.NOPERMISSION.getPath();
    }

    public static void changeScene(SceneType type) throws IOException {

        FXMLLoader loader = new FXMLLoader(SceneHandler.class.getResource(resolvePath(type)));

        Injector injector = Guice.createInjector(new AppModule());
        loader.setControllerFactory(injector::getInstance);

        AnchorPane root = loader.load();

        if (type == SceneType.LOGIN || type == SceneType.DASHBOARD) {

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

    public static Stage createDialog(SceneType sceneType) throws IOException {
        FXMLLoader loader = new FXMLLoader(SceneHandler.class.getResource(sceneType.getPath()));

        Injector injector = Guice.createInjector(new AppModule());
        loader.setControllerFactory(injector::getInstance);

        Stage dialogStage = new Stage();
        dialogStage.initStyle(StageStyle.TRANSPARENT);
        dialogStage.setScene(new Scene(loader.load()));

        dialogStage.initOwner(stage);
        dialogStage.initModality(Modality.APPLICATION_MODAL);

        SuperController dialogController = loader.getController();
        dialogController.setStage(dialogStage);

        return dialogStage;

    }

    public static void setStage(Stage primaryStage) {
        stage = primaryStage;
    }

    public static Stage getStage() {
        return stage;
    }

    public static void setUser(User currentUser) {
        user = currentUser;
    }

    public static User getUser() { return user; }


    }
