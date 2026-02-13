package service.custom.impl;

import model.dto.Order;
import repository.RepositoryFactory;
import repository.custom.OrderRepository;
import service.custom.OrderService;
import util.RepositoryType;

import java.util.List;

public class OrderServiceImpl implements OrderService {
    OrderRepository repository= RepositoryFactory.getInstance().getRepositoryType(RepositoryType.ORDER);
    @Override
    public List<Order> getAllOrders() {
        return repository.getAll();
    }

    @Override
    public Boolean updateOrder(Order order) {
        return repository.update(order);
    }

    @Override
    public Boolean deleteOrder(String orderId) {
        return repository.deleteById(orderId);
    }
}
