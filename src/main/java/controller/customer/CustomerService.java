package controller.customer;

import javafx.collections.ObservableList;
import model.dto.CustomerDTO;

public interface CustomerService {
    public ObservableList<CustomerDTO>getAll();
}
