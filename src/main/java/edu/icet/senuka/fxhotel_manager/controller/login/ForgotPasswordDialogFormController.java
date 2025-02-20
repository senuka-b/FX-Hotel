package edu.icet.senuka.fxhotel_manager.controller.login;

import com.google.inject.Inject;
import edu.icet.senuka.fxhotel_manager.controller.SuperController;
import edu.icet.senuka.fxhotel_manager.dto.User;
import edu.icet.senuka.fxhotel_manager.service.custom.UserService;
import edu.icet.senuka.fxhotel_manager.util.OTPEmailThread;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.net.URL;
import java.security.SecureRandom;
import java.util.List;
import java.util.ResourceBundle;

public class ForgotPasswordDialogFormController extends SuperController {
    @FXML
    private TextField textFieldConfirmPassword;

    @FXML
    private TextField textFieldEmail;

    @FXML
    private TextField textFieldPassword;

    @FXML
    private TextField textFieldOTP;

    @FXML
    private Button buttonSendOTP;


    @FXML
    private Text labelOTPStatus;

    @Inject
    private UserService service;

    private String otp;
    private User user;

    @FXML
    void buttonResetPasswordOnAction() {
        if (otp == null || user == null) {
            showAlert(
                    Alert.AlertType.ERROR,
                    "Error!",
                    "Invalid OTP",
                    "Please generate and confirm the OTP before changing passwords!"
            );

            return;
        }

        if (textFieldOTP.getText().trim().equals(otp)) {
            if (validatePasswordFields()) {

                user.setPassword(textFieldPassword.getText().trim());
                boolean updateUser = service.updateUser(user);

                if (updateUser) {
                    showAlert(
                        Alert.AlertType.INFORMATION,
                        "Success!",
                        "Reset password successfully!",
                        "The passwords have been reset successfully! You can now return back to Login by dismissing this window."
                    );

                    textFieldEmail.setText("");
                    textFieldPassword.setText("");
                    textFieldConfirmPassword.setText("");
                    textFieldOTP.setText("");

                    getStage().close();

                }

            } else {
                showAlert(
                        Alert.AlertType.ERROR,
                        "Error!",
                        "Password Mismatch",
                        "Your passwords do not match. Make sure the passwords are the same in both the fields!"
                );

            }
        } else {
            showAlert(
                    Alert.AlertType.ERROR,
                    "Error!",
                    "Invalid OTP",
                    "The entered OTP is not valid! Please try again or try re-sending the OTP."
            );
        }
    }



    @FXML
    void buttonSendOTPOnAction() {

        ProgressIndicator progressIndicator = new ProgressIndicator();
        buttonSendOTP.setGraphic(progressIndicator);
        buttonSendOTP.setDisable(true);


        if (textFieldEmail.getText().trim().equals("")) {
            showAlert(
                    Alert.AlertType.ERROR,
                    "Error!",
                    "Unable to generate OTP!",
                    "You haven't entered your email to send an OTP yet!" +
                            " Please make sure that this email is the one registered in the system!"
            );

            return;
        }

        List<User> users = service.getAll();
        for (User currentUser : users) {
            if (currentUser.getEmail().equals(textFieldEmail.getText().trim().toLowerCase())) {
                otp = generateOTP();

                this.user = currentUser;

                String content = "<h1 style='color: purple;'>FX Hotel</h1> \n\n" +
                        "<h3>OTP Verification</h3> \n " +
                        "Please enter the following in FX Hotel to verify if it's really you. \n" +
                        "<h1>" + otp + "</h1>" +
                        "\n\n <img src = 'https://img.itch.zone/aW1hZ2UvMjM3OTU0Mi8xNDA4OTg4My5naWY=/original/pR18FB.gif'/>";

                OTPEmailThread.sendEmail(content, textFieldEmail.getText().trim(), data -> Platform.runLater(() -> {
                    showAlert(
                            Alert.AlertType.INFORMATION,
                            "OTP Verification",
                            "Sent OTP",
                            "An OTP has been sent successfully to the email " + textFieldEmail.getText() + ". Please enter the OTP shown to reset your password"
                    );

                    buttonSendOTP.setGraphic(null);
                    buttonSendOTP.setDisable(false);
                }));

                return;
            }
        }

        showAlert(
                Alert.AlertType.ERROR,
                "OTP Verification",
                "Invalid Email",
                "The email you entered does not belong to any user!" +
                        " Please contact the administrators if you believe this is a mistake."
        );

        buttonSendOTP.setGraphic(null);
        buttonSendOTP.setDisable(false);


    }

    private String generateOTP() {
        SecureRandom secureRandom = new SecureRandom();
        return (100000 + secureRandom.nextInt(900000)) + "";
    }

    private boolean validatePasswordFields() {
        String password = textFieldPassword.getText().trim();
        String confirmPassword = textFieldConfirmPassword.getText().trim();

        return (!password.equals("") && !confirmPassword.equals("")) &&
                password.equals(confirmPassword);
    }

    @FXML
    void textFieldOTPKeyOnKeyReleased() {

        if (textFieldOTP.getText().trim().equals(otp)) {
            labelOTPStatus.setFill(Color.LIGHTGREEN);
            labelOTPStatus.setText("✅");

            textFieldPassword.setDisable(false);
            textFieldConfirmPassword.setDisable(false);
        } else {
            labelOTPStatus.setText("❌");
            labelOTPStatus.setFill(Color.RED);

            textFieldPassword.setDisable(true);
            textFieldConfirmPassword.setDisable(true);
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        /*
        * Dummy initialize
        * */
    }
}
