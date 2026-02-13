package repository.custom.impl;

import model.dto.Order;
import repository.custom.OrderRepository;

import java.util.List;

public class OrderRepositoryImpl implements OrderRepository {
    @Override
    public Boolean create(Order order) {
        return null;
    }

    @Override
    public Boolean update(Order order) {
        return null;
    }

    @Override
    public Boolean deleteById(String s) {
        return null;
    }

    @Override
    public Order searchById(String s) {
        return null;
    }

    @Override
    public List<Order> getAll() {
        return List.of();
    }
}
