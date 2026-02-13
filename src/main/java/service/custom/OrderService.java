package service.custom;

import model.dto.Order;
import service.SuperService;

import java.util.List;

public interface OrderService extends SuperService {
    List<Order> getAllOrders();
    Boolean updateOrder(Order order);
    Boolean deleteOrder(String orderId);

}
