package controller.order;

import javafx.collections.ObservableList;
import model.dto.OrderDTO;


public interface OrderService {
    ObservableList<OrderDTO> getAllOrders();
}
