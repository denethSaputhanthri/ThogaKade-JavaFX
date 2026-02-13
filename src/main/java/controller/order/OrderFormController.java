package controller.order;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.dto.Order;

import java.net.URL;
import java.time.LocalDate;

import java.util.ResourceBundle;

public class OrderFormController implements Initializable {
    OrderInfoController orderInfoController=new OrderInfoController();
    ObservableList<Order>orderDTOS= FXCollections.observableArrayList();

    @FXML
    private TableColumn<?, ?> colCustomerId;

    @FXML
    private TableColumn<?, ?> colOrderDate;

    @FXML
    private TableColumn<?, ?> colOrderId;

    @FXML
    private DatePicker dpOrderDate;

    @FXML
    private TableView<Order> tblOrderInfo;

    @FXML
    private JFXTextField txtCustomerId;

    @FXML
    private JFXTextField txtOrderId;

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String orderId = txtOrderId.getText();
        LocalDate orderDate = dpOrderDate.getValue();
        String customerId = txtCustomerId.getText();

        orderInfoController.deleteOrder(orderId);
        autoLoad();
    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {
        String orderId = txtOrderId.getText();
        String customerId = txtCustomerId.getText();
        LocalDate localDate = dpOrderDate.getValue();

        orderInfoController.getAllOrders().forEach(orderDTO -> {
            if(orderDTO.getOrderId().equals(orderId) || orderDTO.getOrderDate().equals(localDate) || orderDTO.getCustomerId().equals(customerId) ){
                tblOrderInfo.getSelectionModel().select(orderDTO);
                tblOrderInfo.scrollTo(orderDTO);
                Order order = orderDTO;
                setTextValue(order);
            }
        });
        autoLoad();
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String orderId = txtOrderId.getText();
        LocalDate orderDate = dpOrderDate.getValue();
        String customerId = txtCustomerId.getText();

        orderInfoController.updateOrder(orderId, dpOrderDate.getValue(),customerId);
        autoLoad();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colOrderId.setCellValueFactory(new PropertyValueFactory<>("orderId"));
        colOrderDate.setCellValueFactory(new PropertyValueFactory<>("orderDate"));
        colCustomerId.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        tblOrderInfo.setItems(orderDTOS);

        tblOrderInfo.getSelectionModel().selectedItemProperty().addListener((observableValue, orderDTO, newValue) -> {
            if(newValue!=null){
                txtOrderId.setText(newValue.getOrderId());
                dpOrderDate.setValue(newValue.getOrderDate());
                txtCustomerId.setText(newValue.getCustomerId());
            }
        });
        autoLoad();
    }
    private void autoLoad(){
        tblOrderInfo.setItems(orderInfoController.getAllOrders());
        orderDTOS.clear();
        clearFields();
    }
    private void setTextValue(Order order){
        txtCustomerId.setText(order.getCustomerId());
        txtOrderId.setText(order.getOrderId());
        dpOrderDate.setValue(order.getOrderDate());
    }
    private void clearFields(){
        txtOrderId.clear();
        txtCustomerId.clear();
    }
}
