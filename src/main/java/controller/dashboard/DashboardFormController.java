package controller.dashboard;


import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;


import javafx.scene.control.Label;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.ResourceBundle;

public class DashboardFormController implements Initializable {
    @FXML
    private AnchorPane dashRoot;

    @FXML
    private AnchorPane dashRoot1;

    @FXML
    private Label lblDate;

    @FXML
    private Label lblTime;

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        localTime();
    }

    private void localTime(){
         Date date =new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        lblDate.setText(simpleDateFormat.format(date));

        Timeline timeline = new Timeline(new KeyFrame(Duration.ZERO, actionEvent -> {
            LocalTime now = LocalTime.now();
            lblTime.setText(now.getHour() + ":" + now.getMinute() + ":" + now.getSecond());
        }),
                new KeyFrame(Duration.seconds(1))
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

}
