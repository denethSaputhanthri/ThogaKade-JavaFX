package controller.order_details;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import model.dto.Item;
import model.entity.Customer;
import service.ServiceFactory;
import service.custom.CustomerService;
import service.custom.ItemService;
import util.ServiceType;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class OrderDetailsFormController implements Initializable {
    CustomerService customerService =ServiceFactory.getInstance().getServiceType(ServiceType.CUSTOMER);
    ItemService itemService = ServiceFactory.getInstance().getServiceType(ServiceType.ITEM);

    @FXML
    private JFXComboBox cmbItemCode;

    @FXML
    private JFXComboBox cmbCustomerId;

    @FXML
    private TableColumn<?, ?> colDescription;

    @FXML
    private TableColumn<?, ?> colDiscount;

    @FXML
    private TableColumn<?, ?> colItemCode;

    @FXML
    private TableColumn<?, ?> colQuantity;

    @FXML
    private TableColumn<?, ?> colTotal;

    @FXML
    private TableColumn<?, ?> colUnitPrice;

    @FXML
    private Label lblNetTotal;

    @FXML
    private TableView<?> tblPlaceOrder;

    @FXML
    private TextField txtCity;

    @FXML
    private TextField txtCustomerId;

    @FXML
    private TextField txtCustomerName;

    @FXML
    private TextField txtDescription;

    @FXML
    private TextField txtDiscount;

    @FXML
    private TextField txtItemCode;

    @FXML
    private TextField txtQuantity;

    @FXML
    private TextField txtUnitPrice;

    @FXML
    void btnAddToCartOnAction(ActionEvent event) {
    }

    @FXML
    void btnPlaceOrderOnAction(ActionEvent event) {

    }

    @FXML
    void txtCustomerIdOnAction(ActionEvent event) {

    }

    @FXML
    void txtCustomerIdOnKeyPressed(KeyEvent event) {

    }

    @FXML
    void txtItemCodeOnAction(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadCustomerIDs();
        loadItemCodes();
        cmbCustomerId.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
          assert newValue != null;
          setCustomerDataTable((String) newValue);
        });
        cmbItemCode.getSelectionModel().selectedItemProperty().addListener((observableValue, old, newValue) -> {
            setItemDataTable((String) newValue);
        });

    }

    private void setItemDataTable(String itemCode) {
        Item item = itemService.searchById(itemCode);
        System.out.println(item);
    }

    private void loadItemCodes() {
        List<String> allItemCode = itemService.getAllItemCode();
        cmbItemCode.setItems(FXCollections.observableArrayList(allItemCode));
    }

    private void setCustomerDataTable(String id) {
        Customer customer = customerService.searchById(id);
        txtCustomerName.setText(customer.getCustomerName());
        txtCity.setText(customer.getCity());
    }

    private void loadCustomerIDs(){
        List<String> customerIDs = customerService.getAllCustomerIDs();
        cmbCustomerId.setItems(FXCollections.observableArrayList(customerIDs));
    }

}
