package controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;

public class DashboardFormController {
    @FXML
    private AnchorPane dashRoot;

    Stage stage =new Stage();

    @FXML
    void btnCustomerOnAction(ActionEvent event) {
        try {
            URL resource = this.getClass().getResource("/view/customer_form.fxml");
            assert resource!= null ;

            Parent parent = FXMLLoader.load(resource);
            dashRoot.getChildren().clear();
            dashRoot.getChildren().add(parent);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnItemsOnAction(ActionEvent event) {
        try {
            URL resource = this.getClass().getResource("/view/item_form.fxml");
            assert resource!= null ;

            Parent parent = FXMLLoader.load(resource);
            dashRoot.getChildren().clear();
            dashRoot.getChildren().add(parent);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnOrderDetailsOnAction(ActionEvent event) {
        try {
            URL resource = this.getClass().getResource("/view/order_details_form.fxml");
            assert resource!= null ;

            Parent parent = FXMLLoader.load(resource);
            dashRoot.getChildren().clear();
            dashRoot.getChildren().add(parent);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnOrderOnAction(ActionEvent event) {
        try {
            URL resource = this.getClass().getResource("/view/order_form.fxml");
            assert resource!= null ;

            Parent parent = FXMLLoader.load(resource);
            dashRoot.getChildren().clear();
            dashRoot.getChildren().add(parent);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
