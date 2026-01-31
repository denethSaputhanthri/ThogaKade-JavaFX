package controller.order;

import javafx.collections.ObservableList;
import javafx.scene.control.DatePicker;
import model.dto.OrderDTO;

import java.time.LocalDate;


public interface OrderService {
    ObservableList<OrderDTO> getAllOrders();
    void updateOrder(String orderId, LocalDate dpOrderDate, String customerId);
}
