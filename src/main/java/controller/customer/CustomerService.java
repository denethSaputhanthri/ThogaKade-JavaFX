package controller.customer;

import javafx.collections.ObservableList;
import model.dto.CustomerDTO;

import java.time.LocalDate;

public interface CustomerService {
    public ObservableList<CustomerDTO>getAll();
    public void addCustomer(String id, String customerTitle, String customerName, LocalDate dateOfBirth, Double salary, String address, String city, String province, String postalCode);
    public void updateCustomer(String id, String customerTitle, String customerName, LocalDate dateOfBirth, Double salary, String address, String city, String province, String postalCode);
    public void deletCustomer(String id);

}
