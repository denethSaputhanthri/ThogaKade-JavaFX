package controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginFormController {
    Stage stage =new Stage();

    @FXML
    private JFXPasswordField txtPassword;

    @FXML
    private JFXTextField txtUserName;

    @FXML
    void btnReloadOnAction(ActionEvent event) {
        if ("Addmin".equals(txtUserName.getText())&& "1234".equals(txtPassword.getText())){
            try {
                stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/dashboard_form.fxml"))));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            stage.setTitle("Dashboard.lk");
            stage.show();
            clearFlieds();
            new Alert(Alert.AlertType.CONFIRMATION,"Login Success").show();
        }else {
            new Alert(Alert.AlertType.ERROR,"Login Error").show();
           clearFlieds();
        }
    }
    void clearFlieds(){
        txtPassword.clear();
        txtUserName.clear();
    }
}
