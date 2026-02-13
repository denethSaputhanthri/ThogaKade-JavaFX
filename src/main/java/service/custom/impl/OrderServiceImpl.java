package service.custom.impl;

import model.dto.Order;
import service.custom.OrderService;

import java.util.List;

public class OrderServiceImpl implements OrderService {

    @Override
    public List<Order> getAllOrders() {
        return List.of();
    }

    @Override
    public Boolean updateOrder(Order order) {
        return null;
    }

    @Override
    public Boolean deleteOrder(String orderId) {
        return null;
    }
}
