package controller.item;

import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.dto.Item;
import service.ServiceFactory;
import service.custom.ItemService;
import util.ServiceType;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ItemController implements Initializable {
    ItemService service=ServiceFactory.getInstance().getServiceType(ServiceType.ITEM);

    @FXML
    private TableColumn<?, ?> colDescription;

    @FXML
    private TableColumn<?, ?> colItemCode;

    @FXML
    private TableColumn<?, ?> colPackSize;

    @FXML
    private TableColumn<?, ?> colQtyOnHand;

    @FXML
    private TableColumn<?, ?> colUnitPrice;

    @FXML
    private TableView<Item> tblItem;

    @FXML
    private JFXTextArea txtDescription;

    @FXML
    private JFXTextField txtItemCode;

    @FXML
    private JFXTextField txtPackSize;

    @FXML
    private JFXTextField txtQty;

    @FXML
    private JFXTextField txtUnitPrice;

    @FXML
    void btnAddOnAction(ActionEvent event) {
        String itemCode = txtItemCode.getText();
        String description = txtDescription.getText();
        String packSize = txtPackSize.getText();
        Double unitPrice = Double.valueOf(txtUnitPrice.getText());
        Integer qty = Integer.valueOf(txtQty.getText());

        service.addItem(new Item(itemCode,description,packSize,unitPrice,qty));
        autoload();
        clearFields();
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String itemCode = txtItemCode.getText();

        service.deleteItem(itemCode);
        autoload();
        clearFields();
    }

    @FXML
    void btnReloadOnAction(ActionEvent event) {
        autoload();
    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {
        String itemCode = txtItemCode.getText();
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String itemCode = txtItemCode.getText();
        String description = txtDescription.getText();
        String packSize = txtPackSize.getText();
        Double unitPrice = Double.valueOf(txtUnitPrice.getText());
        Integer qty = Integer.valueOf(txtQty.getText());

        service.updateItem(new Item(itemCode,description,packSize,unitPrice,qty));
        autoload();
        clearFields();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colItemCode.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colPackSize.setCellValueFactory(new PropertyValueFactory<>("packSize"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colQtyOnHand.setCellValueFactory(new PropertyValueFactory<>("qtyOnHand"));


        tblItem.getSelectionModel().selectedItemProperty().addListener((observableValue, itemDTO, newValue) ->{
            if(newValue!=null){
                txtItemCode.setText(newValue.getItemCode());
                txtDescription.setText(newValue.getDescription());
                txtPackSize.setText(newValue.getPackSize());
                txtQty.setText(String.valueOf(newValue.getQtyOnHand()));
                txtUnitPrice.setText(String.valueOf(newValue.getUnitPrice()));
            }
        } );
        autoload();
    }
    private void setTextValue(Item item){
        txtItemCode.setText(item.getItemCode());
        txtDescription.setText(item.getDescription());
        txtPackSize.setText(item.getPackSize());
        txtQty.setText(String.valueOf(item.getQtyOnHand()));
        txtUnitPrice.setText(String.valueOf(item.getUnitPrice()));
    }
    private void autoload(){
        try {
            List<Item> all = service.getAll();
            ObservableList<Item>itemObservableList=FXCollections.observableArrayList();
            all.forEach(item -> {
                itemObservableList.add(new Item(
                        item.getItemCode(),
                        item.getDescription(),
                        item.getPackSize(),
                        item.getUnitPrice(),
                        item.getQtyOnHand()
                ));
            });
            tblItem.setItems(itemObservableList);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    private void clearFields(){
        txtItemCode.clear();
        txtDescription.clear();
        txtQty.clear();
        txtPackSize.clear();
        txtUnitPrice.clear();
    }
}
