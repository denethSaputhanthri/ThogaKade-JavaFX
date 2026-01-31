package controller.customer;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.dto.CustomerDTO;

import java.net.URL;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.ResourceBundle;

public class CustomerFormController implements Initializable {
    CustomerInfoController customerInfoController=new CustomerInfoController();
    ObservableList<CustomerDTO>customerDTOS=FXCollections.observableArrayList();

    @FXML
    private ComboBox<String> cmbCustomerTitle;

    @FXML
    private ComboBox<String> cmbProvince;

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colCity;

    @FXML
    private TableColumn<?, ?> colCustomerId;

    @FXML
    private TableColumn<?, ?> colCustomerName;

    @FXML
    private TableColumn<?, ?> colCustomerTitle;

    @FXML
    private TableColumn<?, ?> colDOB;

    @FXML
    private TableColumn<?, ?> colPostalCode;

    @FXML
    private TableColumn<?, ?> colProvince;

    @FXML
    private TableColumn<?, ?> colSalary;

    @FXML
    private DatePicker dpDateOfBirth;

    @FXML
    private TableView<CustomerDTO> tblCustomer;

    @FXML
    private JFXTextField txtAddress;

    @FXML
    private JFXTextField txtCity;

    @FXML
    private JFXTextField txtCustomerId;

    @FXML
    private JFXTextField txtCustomerName;

    @FXML
    private JFXTextField txtPostalCode;

    @FXML
    private JFXTextField txtSalary;

    @FXML
    void btnAddOnAction(ActionEvent event) {
        String id= txtCustomerId.getText();
        String customerTitle = cmbCustomerTitle.getValue();
        String customerName = txtCustomerName.getText();
        LocalDate dateOfBirth = dpDateOfBirth.getValue();
        Double salary = Double.valueOf(txtSalary.getText());
        String address = txtAddress.getText();
        String city = txtCity.getText();
        String province = cmbProvince.getValue();
        String postalCode = txtPostalCode.getText();

        customerInfoController.addCustomer(id,customerTitle,customerName,dateOfBirth,salary,address,city,province,postalCode);
        automatically();
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String id = txtCustomerId.getText();

        customerInfoController.deletCustomer(id);
        automatically();
    }

    @FXML
    void btnReloadOnAction(ActionEvent event) {
        automatically();
    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {
        String id = txtCustomerId.getText();
        customerInfoController.getAll().forEach(customerDTO -> {
            if (customerDTO.getCustomerId().equals(id)){
                CustomerDTO customer= customerDTO;
                tblCustomer.getSelectionModel().select(customerDTO);
                tblCustomer.scrollTo(customerDTO);
                setTextValue(customer);
            }
        });
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String id= txtCustomerId.getText();
        String customerTitle = cmbCustomerTitle.getValue();
        String customerName = txtCustomerName.getText();
        LocalDate dateOfBirth = dpDateOfBirth.getValue();
        Double salary = Double.valueOf(txtSalary.getText());
        String address = txtAddress.getText();
        String city = txtCity.getText();
        String province = cmbProvince.getValue();
        String postalCode = txtPostalCode.getText();

        customerInfoController.updateCustomer(id,customerTitle,customerName,dateOfBirth,salary,address,city,province,postalCode);
        automatically();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cmbProvince.setItems(FXCollections.observableArrayList(Arrays.asList("Western"," Southern","Sabaragamuwa","Wayamba","Central","Eastern","Northern","North Western")));
        cmbCustomerTitle.setItems(FXCollections.observableArrayList(Arrays.asList("Mr","Ms","Miss")));

        colCustomerId.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        colCustomerTitle.setCellValueFactory(new PropertyValueFactory<>("customerTitle"));
        colCustomerName.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        colDOB.setCellValueFactory(new PropertyValueFactory<>("dob"));
        colSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colCity.setCellValueFactory(new PropertyValueFactory<>("city"));
        colProvince.setCellValueFactory(new PropertyValueFactory<>("province"));
        colPostalCode.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
        tblCustomer.setItems(customerDTOS);

        tblCustomer.getSelectionModel().selectedItemProperty().addListener((observableValue, customerDTO, newValue) -> {
            if(newValue!=null){
                txtCustomerId.setText(newValue.getCustomerId());
                cmbCustomerTitle.setValue(newValue.getCustomerTitle());
                txtCustomerName.setText(newValue.getCustomerName());
                dpDateOfBirth.setValue(newValue.getDob());
                txtSalary.setText(String.valueOf(newValue.getSalary()));
                txtAddress.setText(newValue.getAddress());
                txtCity.setText(newValue.getCity());
                cmbProvince.setValue(newValue.getProvince());
                txtPostalCode.setText(newValue.getPostalCode());
            }
        });
        automatically();
    }
    private void setTextValue(CustomerDTO customer){
        txtCustomerId.setText(customer.getCustomerId());
        cmbCustomerTitle.setValue(customer.getCustomerTitle());
        txtCustomerName.setText(customer.getCustomerName());
        dpDateOfBirth.setValue(customer.getDob());
        txtSalary.setText(String.valueOf(customer.getSalary()));
        txtAddress.setText(customer.getAddress());
        txtCity.setText(customer.getCity());
        cmbProvince.setValue(cmbProvince.getValue());
        txtPostalCode.setText(customer.getPostalCode());
    }
    private void automatically(){
        tblCustomer.setItems(customerInfoController.getAll());
        customerDTOS.clear();
    }

}
